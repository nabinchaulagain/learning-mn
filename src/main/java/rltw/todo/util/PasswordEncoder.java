package rltw.todo.util;

import jakarta.inject.Singleton;
import org.mindrot.jbcrypt.BCrypt;

@Singleton
public class PasswordEncoder {
    public String hash(String str) {
        String salt = BCrypt.gensalt(12);

        return BCrypt.hashpw(str, salt);
    }

    public boolean check(String str, String hashedStr){
        return BCrypt.checkpw(str, hashedStr);
    }
}
