package com.spring.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author luyanan
 * @since 2020/7/24
 * <p></p>
 **/
public class TestBCrypt {

    @Test
    public void test() {
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw);
        //校验原始密码和BCrypt 是否一致
        System.out.println(BCrypt.checkpw("123", hashpw));
    }

}
