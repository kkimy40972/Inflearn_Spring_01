package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// Test 는 순서 보장 안됨
// -> 순서 상관없이 메소드 별로 다 따로 돌도록 의존 관계 없이 설계해야 함
// -> 하나의 Test 가 끝날 때마다 저장소나 공용 데이터들을 다시 깔끔하게 지워주어야 함(clear)
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끝날 때마다 레파지토리를 깔끔하게 지워주는 코드
    // AfterEach : 메소드 실행이 끝날 때마다 어떤 동작을 실행 : callback method
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional 에서 값을 꺼낼 때 get 으로 꺼낼 수 있음
        // 좋은 방법은 아니지만 테스트 코드에서는 사용해도 무관
        Member result = repository.findById(member.getId()).get();

        // 결과 확인하기
        //System.out.println("result = " + (result == member));

        //import org.junit.jupiter.api.Assertions;
        //Assertions.assertEquals(member, result); // 콘솔에 결과가 뜨는 건 없지만 오류가 없기 때문에 잘 된 것

        //import org.assertj.core.api.Assertions;
        assertThat(member).isEqualTo(result);
    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
