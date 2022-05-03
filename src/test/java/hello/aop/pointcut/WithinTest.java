package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WithinTest {
    /**
     * AspectJExpressionPointcut 포인트컷 표현식을 처리해주는 클래스다.
     * 여기에 포인트컷 표현식을 지정하면 된다.
     */
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;


    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    /**
     * within 은 execution 과 똑같은데 execution 에서 Type 부분 만 가져왔다 생각하면 되겠다.
     * 그리고 표현식에 부모 타입을 지정하면 안된다. 정확하게 타입이 맞아야한다.
     * 이부분에서 execute 와 차이가 있다.
     */
    @Test
    void exactMatch() {
        pointcut.setExpression("within(hello.aop.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withStar() {
        pointcut.setExpression("within(hello.aop.member.*Service*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void withSubPackage() {
        pointcut.setExpression("within(hello.aop..*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

}
