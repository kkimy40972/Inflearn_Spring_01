package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class JpaMemberRepository implements MemberRepository{

    // JPA 는 EntityManager 로 모든 것이 동작함
    // data-jpa 라이브러리를 받으면, 스프링 부츠가 자동으로
    // 현재 데이터베이스와도 연결을 다 시켜서 EntityManager 를 생성해줌
    // 우리는 만들어진 것을 injection 받으면 됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    } // 이렇게 하면 JPA 가 insert 쿼리 다 만들어서 DB 에 다 집어넣고, setId()까지 다 해줌


    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회할 타입 과 식별자 PK 값 넣어주면 데이터 조회가 됨
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {

        // PK 기반이 아닌 다른 것들은 다 JPQL 객체지향 쿼리 사용해야함
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
     /*
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
     */
        // JPQL 객체지향 쿼리 사용해야함
        // (보통 테이블을 대상으로 쿼리를 보내는데,) 객체 대상으로 쿼리를 보냄
        // 객체 (Member 엔티티) 자체를 select 해서 조회 ( m : 엘리어스)
        // 이미 다른 모른 맵핑이 되어 있음
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


}
