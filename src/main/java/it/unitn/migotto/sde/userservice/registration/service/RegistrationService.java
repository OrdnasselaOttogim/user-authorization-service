package it.unitn.migotto.sde.userservice.registration.service;

import it.unitn.migotto.sde.userservice.appuser.model.AppUser;
import it.unitn.migotto.sde.userservice.appuser.model.UserRole;
import it.unitn.migotto.sde.userservice.appuser.service.UserService;
import it.unitn.migotto.sde.userservice.registration.EmailValidator;
import it.unitn.migotto.sde.userservice.registration.RegistrationRequest;
//import it.unitn.migotto.sde.userservice.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    //ConfirmationTokenService confirmationTokenService;


    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER,
                        request.getAddress()
                )
        );

    }



}
