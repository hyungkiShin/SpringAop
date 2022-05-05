package hello.aop.internalcall;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    /**
     * applicationContext 는 그냥 spring 이 주입 받을 수 있게 해준다.
     * 대신 기능이 너무 많다.
     * 수많은 기능 중에 필요한 기능은 지연해서 CallService2 를 조회 하는것 ...
     */
    /**
    private final ApplicationContext applicationContext;

    public CallServiceV2(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void external() {
        log.info("call external");
        CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
        callServiceV2.internal(); //외부 메서드 호출
    }
    public void internal() {
        log.info("call internal");
    }
    */

    /**
     * ObjectProvider 는 객체를 스프링 컨테이너에서 조회하는 것을 스프링 빈 생성시점이 아니라 실제 객체를 사용하는 시점으로 지연할 수 있다.
     * callServiceProvider.getObject() 를 호출 하는 시점에 스프링 컨테이너에서 빈을 조회한다.
     * 여기서는 자기 자신을 주입 받는 것이 아니기 때문에 순환 사이클이 발생하지 않는다.
     */
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(final ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        log.info("call external");
        CallServiceV2 callServiceV2 = callServiceProvider.getObject();
        callServiceV2.internal(); //외부 메서드 호출
    }
    public void internal() {
        log.info("call internal");
    }
}
