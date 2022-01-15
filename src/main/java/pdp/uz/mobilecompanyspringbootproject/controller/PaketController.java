package pdp.uz.mobilecompanyspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.mobilecompanyspringbootproject.entity.Paket;
import pdp.uz.mobilecompanyspringbootproject.payload.ApiResponse;
import pdp.uz.mobilecompanyspringbootproject.payload.PaketDto;
import pdp.uz.mobilecompanyspringbootproject.service.PaketService;

import java.util.List;

@RestController
@RequestMapping("/api/paket")
public class PaketController {
    @Autowired
    PaketService paketService;

    @GetMapping
    public HttpEntity<?> getPaket(){
        List<Paket> paket = paketService.getPaket();
        return ResponseEntity.ok(paket);
    }

    @PostMapping
    public HttpEntity<?> addPaket(@RequestBody PaketDto paketDto){
        ApiResponse apiResponse = paketService.addPaket(paketDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editPaket(@PathVariable Integer id,@RequestBody PaketDto paketDto){
        ApiResponse apiResponse = paketService.editPaket(id, paketDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePaket(@PathVariable Integer id){
        ApiResponse apiResponse = paketService.deletePaket(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
