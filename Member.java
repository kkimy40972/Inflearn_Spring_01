package hello.hellospring.domain;

public class Member {

    private Long id; // 고객이 정한 아이디가 아닌, 데이터를 구분하기 위한 시스템이 저장하는 아이디
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
