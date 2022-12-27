package it.unitn.migotto.sde.userservice.appuser.controller;

import it.unitn.migotto.sde.userservice.appuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin
@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public RedirectView getUsers(){
        return new RedirectView("http://localhost:3000");
    }


    @GetMapping("/jobs")
    public String getJobs(){
        return userService.getJobs();
    }




}
