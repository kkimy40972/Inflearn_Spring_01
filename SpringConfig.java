package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링이 뜰 때, @Configuration 을 읽고
@Configuration
public class SpringConfig {

    // 이걸 스프링 빈에 등록하라는 뜻인 것을 인식하고
    @Bean
    public MemberService memberService(){ // 이 로직을 호출해서 스프링 빈에 등록해줌
        return new MemberService(memberRepository());
    }
    // new MemberService(); 의 생성자에 memberRepository 를 넣어야 하므로
    @Bean
    public MemberRepository memberRepository(){ // 인터페이스
        return new MemoryMemberRepository(); // 구현체를 인스턴스
    }
}
