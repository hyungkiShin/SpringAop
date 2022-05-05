package hello.aop.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
public class EnumTest {

    @Autowired
    ExamService examService;

    @Autowired
    TestService testService;

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("client request i ={}", i);
            examService.request("data" + i);
        }
    }

    @Test
    void test1() {
        for (int i = 0; i < 5; i++) {
            log.info("client request i ={}", i);
            testService.request("data" + i);
        }
    }
}
