package pdp.uz.mobilecompanyspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.mobilecompanyspringbootproject.entity.Customer;
import pdp.uz.mobilecompanyspringbootproject.payload.ApiResponse;
import pdp.uz.mobilecompanyspringbootproject.payload.CustomerDto;
import pdp.uz.mobilecompanyspringbootproject.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public HttpEntity<?> getCustomer(){
        List<Customer> customer = customerService.getCustomer();
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public HttpEntity<?> addCustomer(@RequestBody CustomerDto customerDto){
        ApiResponse apiResponse = customerService.addCustomer(customerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
