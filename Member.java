package hello.hellospring.domain;

import javax.persistence.*;

// JPA 가 관리하는 엔티티 라는 것
// mapping 을 미리 다 해놓고, JPA 가 이 entity 를 보고 알아서 쿼리문 생성해줌
@Entity
public class Member {

    // @Id : PK Mapping : 원래는 DB 에서 설정해줌
    // @GeneratedValue(Strategy = GenerationType.IDENTITY)
    //      IDENTITY : DB 가 자동으로 시퀀스를 붙여주는 것
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고객이 정한 아이디가 아닌, 데이터를 구분하기 위한 시스템이 저장하는 아이디
    // 만약 DB 의 컬럼명이 username 이라면
    //@Column(name = "username")
    private String name; // 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
