package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    MemberService memberService;
    // MemberService 에서 인스턴스 해서 만든 MemoryMemberRepository 와
    // MemberServiceTest 에서 인스턴스 해서 만든 MemoryMemberRepository 는 다른 인스턴스, 리포지토리
    //MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    // 동작하기 전에 넣어주기
    @BeforeEach
    public void beforeEach(){
        // => 각 Test 를 실행할 때마다 각각 생성해줌
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    } // => 생성된 리포지토리를 MemberService 에 넣음 -> 같은 MemoryMemberRepository 가 사용됨

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        /* given : 이 주어진 상황에서 : 이 데이터를 기반으로 하는구나 */
        Member member = new Member();
        member.setName("hello");

        /* when : 이걸 실행했을 때 : 이것을 검증하는구나 */
        // member service 의 join을 검증하는 것
        // 리턴 : 저장한 id
        Long saveId = memberService.join(member);

        /* then : 이런 결과가 나와야 해 : 검증부 */
        // 우리가 저장한 게 리포지토리에 있는 게 맞아?
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
        // () -> memberService.join(member2) : 이 로직을 실행했을 때
        // IllegalStateException :  이 예외가 발생해야 함
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {
            memberService.join(member2);
            // 여기까지 오면 예외가 발생해야하는데 발생하지 않은 것이므로
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        /* then*/

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}