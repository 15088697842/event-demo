package com.success.bigevent;

import com.success.bigevent.model.EventBusinessDO;
import com.success.bigevent.service.cate.EventBusinessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class BigEventApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private EventBusinessService eventBusinessService;

    @Test
    public void test(){

    }

}
