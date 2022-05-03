package hello.aop.pointcut;

import hello.aop.order.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {
    /**
     * AspectJExpressionPointcut 포인트컷 표현식을 처리해주는 클래스다.
     * 여기에 포인트컷 표현식을 지정하면 된다.
     */
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;


    @BeforeEach
    public void init() throws NoSuchMethodException {
        // hello 에 매개변수 타입이 String 이라서 String.class 를 던진다.
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        // execution(* ..packget..Class.)
        log.info("helloMethod={}", helloMethod);
    }
}
