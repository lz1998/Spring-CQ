package xin.lz1998.cq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xin.lz1998.SpringCQApplication;

// SpringBootTest不加参数package时会报错
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCQApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringCQApplicationTests {

    @Test
    public void contextLoads() {
    }

}
