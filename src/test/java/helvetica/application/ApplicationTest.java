package helvetica.application;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ApplicationTest {

    @Test
    public void saveAdmin(){
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }


}