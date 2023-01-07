package it.unitn.migotto.sde.userservice.appuser.service;

import it.unitn.migotto.sde.userservice.appuser.model.AppUser;
import it.unitn.migotto.sde.userservice.appuser.repository.UserRepository;
//import it.unitn.migotto.sde.userservice.registration.token.ConfirmationToken;
//import it.unitn.migotto.sde.userservice.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    //private final ConfirmationTokenService confirmationTokenService;


    @Autowired
    private final RestTemplate restTemplate;




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser){

        boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
        if (userExists){
            throw new IllegalStateException("Email aready taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        userRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        /**Send confirmation token*/
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15),
//                appUser
//        );
//        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: send email

        return token;
    }


    public String getJobs(){
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8082/api/v1/job", HttpMethod.GET, null, String.class);
        return response.getBody();
    }

    public String getUsers() {
        return userRepository.findAll().toString();
    }

//    public String getOauthUserInfo() {
//
//        ResponseEntity<String> response =
//                restTemplate.exchange("http://localhost:8081/user", HttpMethod.GET, null, String.class);
//
//        return response.getBody();
//    }




}
