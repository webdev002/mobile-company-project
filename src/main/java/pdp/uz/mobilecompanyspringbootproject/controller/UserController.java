package pdp.uz.mobilecompanyspringbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.mobilecompanyspringbootproject.payload.ApiResponse;
import pdp.uz.mobilecompanyspringbootproject.payload.LoginDto;
import pdp.uz.mobilecompanyspringbootproject.payload.RegisterDto;
import pdp.uz.mobilecompanyspringbootproject.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto){
        ApiResponse apiResponse = userService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse apiResponse = userService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
}
