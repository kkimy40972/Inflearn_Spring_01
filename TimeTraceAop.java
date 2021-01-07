package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/*
    TimeTraceAop 를 스프링 빈으로 등록해주어야 함
    방법 1. @Component : ComponentScan 됨
    방법 2. SpringConfing 에서 Bean 으로 등록해주기
            -> 더 선호 : 정형화되지 않은 것은 AOP 에 걸어서 쓴다는 것 명시적으로 보여주기
*/
// @Aspect 를 적어주어야 AOP 로 사용가능
@Aspect
@Component
public class TimeTraceAop {

    // 공통 관심 사항을 어디에 적용할지 타겟팅 : 정해진 문법 있음 (매뉴얼 보고 하면 됨)
    // @Around("execution(* 패키지명...클래스명(파라미터타입) 등등 원하는 조건 넣기)")
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    // 메소드 호출할 때마다 중간에 인터셉트가 걸리는 것
    // 호출이 될 때마다 joinPoint 에 파라미터를 이용해서 필요한 조건들 조작할 수 있음

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
          /*Object result = joinPoint.proceed();
            return result;*/
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }

}