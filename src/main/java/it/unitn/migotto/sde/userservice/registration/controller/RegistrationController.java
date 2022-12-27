package it.unitn.migotto.sde.userservice.registration.controller;

import it.unitn.migotto.sde.userservice.registration.RegistrationRequest;
import it.unitn.migotto.sde.userservice.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor

public class RegistrationController {

    private RegistrationService registrationService;

    @GetMapping
    public String hello(){
        return "hello";
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }



}
