package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스는 다중 상속 가능
// 스프링 데이터 JPA 가  JpaRepository 를 가지고 있으면
// 자동으로 구현체를 만들어주고 스프링 빈에 등록해줌
// → 이걸 그냥 가져다 사용하면 됨
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
    //Optional<Member> findByNameAndId(String name, Long id);
}

// 따로 구현체 만들 필요 없음. 인터페이스 만들면 끝난 것

