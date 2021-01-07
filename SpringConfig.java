package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;



// 스프링이 뜰 때, @Configuration 을 읽고
@Configuration
public class SpringConfig {

/*  private DataSource dataSource;
    /*  스프링이 application.properties 에 세팅해놓은 연결 정보를 보고
        데이터소스(접속정보)를 만들어주고 이렇게 주입 해줌     */
/*  @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
*/

/*    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    // 이걸 스프링 빈에 등록하라는 뜻인 것을 인식하고
    @Bean
    public MemberService memberService(){ // 이 로직을 호출해서 스프링 빈에 등록해줌
        return new MemberService(memberRepository());
    }
    // new MemberService(); 의 생성자에 memberRepository 를 넣어야 하므로


    @Bean
    public MemberRepository memberRepository(){ // 인터페이스
    //  return new MemoryMemberRepository(); // 구현체를 인스턴스

        //데이터 저장소를 JdbcMemberRepository 로 Configuration 해주기
        //return new JdbcMemberRepository(dataSource); // 필요한 데이터소스는 스프링이 제공해줌

        //return new JdbcTemplateMemberRepository(dataSource);

        return new JpaMemberRepository(em); // EntityManager 필요함 -> DI 해서 받아오기
    }


*/


    // 스프링 데이터 JPA 가 만들어놓은 구현체가 등록됨
    private final MemberRepository memberRepository;
    // 인젝션 받기
    @Autowired //생성자가 하나인 경우 생략가능
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // MemberService 에 의존 관계 만들어주기
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    /*
        인터페이스만 만들고 그 인터페이스가
        org.springframework.data.jpa.repository.JpaRepository 를 가지고 있으면
        스프링 데이터 JPA 가 해당 인터페이스의 구현체를 자동으로 만들어주고 스프링 빈에 등록해줌
    */

}