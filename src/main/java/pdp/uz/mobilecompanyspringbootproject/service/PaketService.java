package pdp.uz.mobilecompanyspringbootproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.mobilecompanyspringbootproject.entity.Definition;
import pdp.uz.mobilecompanyspringbootproject.entity.Paket;
import pdp.uz.mobilecompanyspringbootproject.payload.ApiResponse;
import pdp.uz.mobilecompanyspringbootproject.payload.DefinitionDto;
import pdp.uz.mobilecompanyspringbootproject.payload.PaketDto;
import pdp.uz.mobilecompanyspringbootproject.repository.PaketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaketService {
    @Autowired
    PaketRepository paketRepository;

    public List<Paket> getPaket() {
        List<Paket> pakets = paketRepository.findAll();
        return pakets;
    }

    public ApiResponse addPaket(PaketDto paketDto) {
        //PAKET SAQLANDI
        Paket paket = new Paket();
        paket.setTypes(paketDto.getTypes());
        paket.setPrice(paketDto.getPrice());
        paket.setAmalQilishMuddati(paketDto.getAmalQilishMuddati());
        paket.setActive(paketDto.isActive());

        //DEFINITION SAQLANDI
        List<Definition> definitionList = new ArrayList<>();
        for (DefinitionDto definitionDto : paketDto.getDefinitionDtoList()) {
            Definition definition = new Definition(
                    definitionDto.getName(),
                    definitionDto.getPrice(),
                    paket
            );
            definitionList.add(definition);
        }
        paket.setDefinition(definitionList);
        paketRepository.save(paket);
        return new ApiResponse("Saqlandi",true);
    }

    public ApiResponse editPaket(Integer id,PaketDto paketDto) {
        boolean exists = paketRepository.existsByAmalQilishMuddatiAndIdNot(paketDto.getAmalQilishMuddati(),id);
        if (exists){
            return new ApiResponse("Bunday idlik paket mavjud emas",false);
        }
        Optional<Paket> optionalPaket = paketRepository.findById(id);
        if (optionalPaket.isPresent()){
            Paket paket = optionalPaket.get();
            paket.setTypes(paketDto.getTypes());
            paket.setPrice(paketDto.getPrice());
            paket.setAmalQilishMuddati(paketDto.getAmalQilishMuddati());
            paket.setActive(paketDto.isActive());

            //DEFINITION SAQLANDI
            List<Definition> definitionList = new ArrayList<>();
            for (DefinitionDto definitionDto : paketDto.getDefinitionDtoList()) {
                Definition definition = new Definition(
                        definitionDto.getName(),
                        definitionDto.getPrice(),
                        paket
                );
                definitionList.add(definition);
            }
            paket.setDefinition(definitionList);
            paketRepository.save(paket);
            return new ApiResponse("Paket tahrirlandi",true);
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse deletePaket(Integer id) {
        paketRepository.deleteById(id);
        return new ApiResponse("Paket Ochirildi",true);
    }
}
