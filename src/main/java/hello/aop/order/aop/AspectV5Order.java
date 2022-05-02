package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV5Order {

    /**
     * Bean 의 순서 보장은 @Aspect 단위로 한다.
     * 예컨데, @Order 를 Around 단위로 걸어줘선 안된다
     * Ex ) @Order(1) , @Order(2) <- 이렇게는 컨트롤 할 수 없다는걸 짚고 넘어가자.
     * 따라서 static class 로 해결해야 이 문제를 해결 할 수 있다.
     */
    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("hello.aop.order.aop.PointCuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
            return joinPoint.proceed(); // 실제 타깃이 호출됨.
        }

    }

    @Aspect
    @Order(1)
    public static class TxAspect {
        @Around("hello.aop.order.aop.PointCuts.allService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리스] {}", joinPoint.getSignature());
            }
        }
    }
}
