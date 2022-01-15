package pdp.uz.mobilecompanyspringbootproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pdp.uz.mobilecompanyspringbootproject.entity.User;
import pdp.uz.mobilecompanyspringbootproject.entity.enums.RoleName;
import pdp.uz.mobilecompanyspringbootproject.payload.ApiResponse;
import pdp.uz.mobilecompanyspringbootproject.payload.LoginDto;
import pdp.uz.mobilecompanyspringbootproject.payload.RegisterDto;
import pdp.uz.mobilecompanyspringbootproject.repository.RoleRepository;
import pdp.uz.mobilecompanyspringbootproject.repository.UserRepository;
import pdp.uz.mobilecompanyspringbootproject.security.JwtProvider;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse registerUser(RegisterDto registerDto) {
        User user = new User();
        user.setFirstname(registerDto.getFirstname());
        user.setLastname(registerDto.getLastname());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findAllByRoleName(RoleName.CUSTOMER)));
        user.setEnabled(true);
        userRepository.save(user);

        return new ApiResponse("User tizimga kirdi,Royxatdan o'tib token orqali boshqa yollarga murojat etishingiz mumkin",true);
    }

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            User user=(User)authenticate.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername(),user.getRoles());
            return new ApiResponse("Token",true,token);

        }catch (Exception e){
            return new ApiResponse("Parol yoki login xato",false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> allByFirstname = userRepository.findAllByFirstname(username);
        if (allByFirstname.isPresent()){
            return allByFirstname.get();
        }
        throw new UsernameNotFoundException(username+" topilmadi");
    }
}
