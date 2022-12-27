package it.unitn.migotto.sde.authservice.JWTcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin
@RestController
public class HomeController {


    @GetMapping
    public String home(Principal principal){
        return "Hello, " + principal.getName();
    }






}
