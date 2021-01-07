package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Component 가 @Service 에 포함되어 있음
//@Service
/* JPA 쓸 때 주의할 점 : 항상 트렌젝션이 있어야 함
/  데이터를 저장하거나 변경할 때는 항상 트렌젝션이 있어야 함 */
@Transactional
public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // MemberRepository 를 직접 new 해서 생성하는 것이 아니라
    // memberRepository 를 외부에서 넣어줌 : Dependency Injection (DI)
    // 스프링이 스프링 컨테이너에 있는 MemberRepository 를 가져다가 연결을 시켜줌
    // 현재는 MemoryMemberRepository 가 구현체로 있기 때문에, MemoryMemberRepository 를 가져와서 연결시킴
   public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    /* 회원가입할 때만 필요하니까 여기에만 트렌젝션을 써주어도 됨 */
    //@Transactional
    // JPA 는 모든 join 들어올 때 데이터 변경이 다 transaction 안에서 실행됨
    public Long join(Member member){

        // Method 으로 뽑아내기
        validateDuplicateMember(member); // 중복 회원 검증 하고
        memberRepository.save(member); // 통과하면 바로 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원 X

        // 파라미터로 넘어온 member.getName() 값을
        // memberRepository 에서 findByName 으로 찾아보고
        //Optional<Member> result = memberRepository.findByName(member.getName());
        // 만약 있으면 : null 이 아니라 만약 값이 있으면
        //result.ifPresent(m -> { // 이 로직의 동작을 하는 것 : Optional 이기 때문에 할 수 있음
        //    throw new IllegalStateException("이미 존재하는 회원입니다.");
        //});

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        /*
            직접 값을 꺼내고 싶을 땐 : 권장하지는 않음
            Member member1 = result.get();

            result.get(); : 값이 있으면 꺼내고,
            result.orElseGet(); : 없으면 ( )를 실행
        */
    }



    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
