package it.unitn.migotto.sde.authservice.JWTcontroller;

import it.unitn.migotto.sde.authservice.JWTservice.TokenService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@AllArgsConstructor
public class AuthController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);


    //@Autowired
    private final TokenService tokenService;


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
