package hello.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository // component Scan 이 되게 @Repository 추가
public class OrderRepository {

    public String save(String itemId) {
        log.info("[orderRepository] 실행");

        // 저장 로직
        if (itemId.equals("ex")) { // 예외
            throw new IllegalStateException("예외 발생");
        }
        return "ok";
    }
}
