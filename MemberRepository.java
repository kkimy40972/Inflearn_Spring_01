package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // Repository 에 4가지 기능 추가
    Member save(Member member); // 저장소에 저장
    Optional<Member> findById(Long id); // 저장소에서 id 로 찾아오기
    Optional<Member> findByName(String name); // 저장소에서 name 으로 찾아오기
    List<Member> findAll(); // 지금까지 저장된 모든 회원 List 반환
}
