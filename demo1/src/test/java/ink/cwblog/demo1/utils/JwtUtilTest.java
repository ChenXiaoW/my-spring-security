package ink.cwblog.demo1.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void getIdFromToken() {
        String id = jwtUtil.getIdFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsImNyZWF0ZWQiOjE2MjA5MTk4MTg2NjAsImV4cCI6MTYyMTUyNDYxOH0.Cq4QimlZTUey2iDcMsoPdZGE6mQiCEtqsCpJ8UvQL383XadIDu_hliMHSLmPrI95TWZ133BBU2mRGK8Vub0Pug");
        System.out.println(id);
    }
}