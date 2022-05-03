package hello.aop.member.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // 실제 실행할때까지 이 에너테이션이 살아있다.
public @interface ClassAop {
}
