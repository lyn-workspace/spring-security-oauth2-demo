package com.spring.security.uaa;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author luyanan
 * @since 2020/8/1
 * <p></p>
 **/
public class PasswordEncoderTest {

    @Test
    public void test() {

        String hashpw = BCrypt.hashpw("secret", BCrypt.gensalt());
        System.out.println(hashpw);
    }

}
