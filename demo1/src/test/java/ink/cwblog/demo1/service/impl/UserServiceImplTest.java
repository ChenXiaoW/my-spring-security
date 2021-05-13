package ink.cwblog.demo1.service.impl;

import ink.cwblog.demo1.http.req.RegisterUserReq;
import ink.cwblog.demo1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void registerUser() {
        RegisterUserReq registerUserReq = new RegisterUserReq();
        registerUserReq.setName("cw");
        registerUserReq.setTel("123213");
        registerUserReq.setPassword("123456");
        userService.registerUser(registerUserReq);
    }

    @Test
    void login() {
    }

    @Test
    void queryUserList() {
    }
}