package it.unitn.migotto.sde.authservice.JWTcontroller;

import it.unitn.migotto.sde.authservice.JWTservice.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class AuthController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private /*final*/ TokenService tokenService;


    /*public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }*/


    @PostMapping("/token")
    public String token(Authentication authentication) throws JSONException {
        //System.out.println(authentication.getAuthorities());
        //LOGGER.debug("Token requested from user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        //LOGGER.debug("Token granted {}", token);
        JSONObject response = new JSONObject();
        response.put("token", token);
        response.put("role", authentication.getAuthorities());
        return response.toString();
    }


    @PostMapping("/valid")
    public Boolean isValidToken(@RequestHeader("Authorization") String token){
        //System.out.println(token);
        var result = tokenService.validateToken(token.replace("Bearer ", ""));
        //System.out.println(token.replace("Bearer ", ""));
        //System.out.println(result);
        return result != null;
    }



}
