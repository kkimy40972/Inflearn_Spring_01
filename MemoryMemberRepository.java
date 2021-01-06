package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 메모리에 어딘가에 저장하기 위해 Map 사용
    // key : 회원 id -> Long , value : Member
    // 실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수일 때는 ConcurrentHashMap 사용해야 함
    private static Map<Long, Member> store = new HashMap<>();
    // 실무에서는 long 을 사용하는 것보단 동시성 문제를 고려해서 AtomicLong 등을 사용
    private static long sequence = 0L; // 0, 1, 2 ... key 값 생성

    @Override
    public Member save(Member member) { // 시스템이 정한 id 와 고객이 넣은 name 이 넘어온 상태
        member.setId(++sequence); // setId 할 때 시퀀스 값 추가해서 id 세팅
        store.put(member.getId(), member); // Map(store) 에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store 에서 꺼내오기
        // null 이 반환될 경우 대비 : Optional.ofNullable()
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // Java 8 Lambda(람다)식
        return store.values().stream() // 루프를 돌리면서
                // member 에서 getName() 과 파라미터로 넘어온 name 이 같은지 확인
                // 같은 경우에만 필터링이 됨
                .filter(member -> member.getName().equals(name))
                // 그 중에서 찾으면 반환 : Optional 로 반환이 됨
                .findAny(); // findAny() : 순서와 관계없이 먼저 찾아지는 객체를 리턴
                            // findFisrt() : 스트림의 순서상 가장 첫번째 있는 것을 리턴
    } // Map 에서 루프를 다 돌면서 하나 찾아지면 그것을 반환
      // 만약 다 돌았는데도 없으면 Optional 에 null 이 포함되어 있어서 null 이 반환됨

    @Override
    public List<Member> findAll() { // 자바에서 실무할 때는 List 많이 사용 : 루프 돌리기 편함
        return new ArrayList<>(store.values()); // store 에 있는 member 들을 쭉 반환
    }


    public void clearStore(){
        store.clear();
    }
}
