package rltw.todo.controller;


import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;
import rltw.todo.dto.SignupPayload;
import rltw.todo.service.AuthService;

import javax.validation.Valid;

@Controller("/api/auth")
@Secured(value = SecurityRule.IS_ANONYMOUS)
public class AuthController {
    @Inject
    private AuthService authService;

    @Post("/signup")
    public String signup(@Body @Valid SignupPayload payload) throws Exception{
        authService.createUser(payload);
        return String.format("Successfully created user with username \"%s\"",payload.getUsername());
    }


}
