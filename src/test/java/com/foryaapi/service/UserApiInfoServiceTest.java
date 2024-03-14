package com.foryaapi.service;

import com.foryaapi.MyApplication;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {MyApplication.class})
@RunWith(SpringRunner.class)
public class UserApiInfoServiceTest {
    @Autowired
    private UserApiInfoService userApiInfoService;

    @Test
    public void invokeCount() {
        //System.out.println(userApiInfoService);
        boolean b = userApiInfoService.invokeCount(1L, 1L);
        Assertions.assertTrue(b);
    }
}