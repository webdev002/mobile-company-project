package pdp.uz.mobilecompanyspringbootproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pdp.uz.mobilecompanyspringbootproject.entity.Customer;
import pdp.uz.mobilecompanyspringbootproject.entity.Definition;
import pdp.uz.mobilecompanyspringbootproject.entity.SimCard;
import pdp.uz.mobilecompanyspringbootproject.payload.ApiResponse;
import pdp.uz.mobilecompanyspringbootproject.payload.CustomerDto;
import pdp.uz.mobilecompanyspringbootproject.payload.DefinitionDto;
import pdp.uz.mobilecompanyspringbootproject.payload.SimCardDto;
import pdp.uz.mobilecompanyspringbootproject.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public ApiResponse addCustomer(CustomerDto customerDto) {
        //CUSTOMER SAQLANDI
        Customer customer =new Customer();
        customer.setName(customerDto.getName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());

        //SIMCARD SAQLANDI
        List<SimCard> simCardList = new ArrayList<>();
        for (SimCardDto simCardDto : customerDto.getSimCardDtoList()) {
            SimCard simCard = new SimCard(
                    simCardDto.getNumber(),
                    simCardDto.getBalance(),
                    simCardDto.isActive(),
                    customer
            );
            simCardList.add(simCard);
            //DEFINITION SAQLANDI
            List<Definition> definitionList = new ArrayList<>();
            for (DefinitionDto definitionDto : simCardDto.getDefinition()) {
                Definition definition = new Definition(
                        definitionDto.getName(),
                        definitionDto.getPrice(),
                        simCard
                );
                definitionList.add(definition);
            }
            simCard.setDefinition(definitionList);
        }
        customer.setSimCard(simCardList);
        customerRepository.save(customer);
        return new ApiResponse("Customer saqlandi",true);
    }
}
