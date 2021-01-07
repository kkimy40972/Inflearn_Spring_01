package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
    @SpringBootTest
        : 테스트 실행할 때 스프링 컨테이너도 같이 실행

    @Transactional
        : 테스트 실행할 때 트랜젝션을 먼저 실행하고
          DB 에 데이터를 insert 쿼리를 통해 다 넣은 후
          테스트가 끝나면 롤백을 해줌
          = DB 에 넣었던 데이터가 반영이 안되게 깔끔하게 지워줌
*/

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // 테스트는 가장 편한 방법으로 하면 됨
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    // 스프링에 저장된 구현체를 컨피그레이션 한 것에서 가져올 것임

    @Test
    void 회원가입() {
        /* given */
        Member member = new Member();
        member.setName("hellospring");

        /* when */
        Long saveId = memberService.join(member);

        /* then */
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        /* given */
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); // 일부러 예외 발생시켜서 에러 잡기

        /* when */
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}