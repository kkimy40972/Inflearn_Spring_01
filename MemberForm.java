package hello.hellospring.controller;


// createMemberForm.html 의 name 과 매칭되면서 값이 들어옴
public class MemberForm {
    private String name;

    // get 으로 값을 꺼냄
    public String getName() {
        return name;
    }

    // set 으로 값을 넣어주고
    public void setName(String name) {
        this.name = name;
    }
}
