package cn.tms;

import cn.tms.service.TmsMenuService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class Test {
    
    @Autowired
    TmsMenuService tmsMenuService;
    
//    @org.junit.Test
//    public void test(){
//        HashMap<String, List> stringListHashMap = tmsMenuService.selectMenu();
//    }
}
