package rltw.todo.config;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import rltw.todo.error.AuthException;
import rltw.todo.model.User;
import rltw.todo.service.AuthService;
import rltw.todo.util.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class AppAuthenticationProvider implements AuthenticationProvider {
    @Inject
    AuthService authService;

    @Inject
    PasswordEncoder passwordEncoder;

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        return Flowable.create(emitter->{
            User user = authService.getUserByUsername((String) authenticationRequest.getIdentity());

            if(passwordEncoder.check((String) authenticationRequest.getSecret(),user.getPassword())){
                Map<String,Object> userAttributes = new HashMap<>();
                userAttributes.put("userId",user.getId());
                emitter.onNext(AuthenticationResponse.success(user.getUsername(),userAttributes));
            }
            else{
                emitter.onError(new AuthException("Password didn't match"));
            }

            emitter.onComplete();
        }, BackpressureStrategy.ERROR);
    }
}
