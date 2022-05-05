package hello.aop.internalcall;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * 구조를 변경 분리
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    /**
     가장 나은 대안은 내부 호출이 발생하지 않도록 구조 자체를 변경하는것이다.
     실제 스프링에서도 이 방법을 제일 권장한다.
     */

    private final InternalService internalService;

    public void external() {
        log.info("call external");
        internalService.internal(); //외부 메서드 호출
    }

}
