package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 처음 스프링을 시작할 때 스프링 컨테이너(통)가 생기는데
// 컨트롤러 어노테이션이 있으면 이것을 보고
// 스프링이 MemberController 객체를 생성 및 관리함
// = 스프링 컨테이너에서 스프링 빈이 관리된다.
// @Component 가 @Controller 에 포함되어 있음
@Controller
public class MemberController {

    // MemberController 가 MemberService 를 가져다 써야 함
    // new 로 생성해서 사용할 수도 있음
    /*  스프링이 관리를 하게 되면, 다 스프링 컨테이너가 등록을 하고 컨테이너에서 받아서 사용하도록 바꿔야 함
        new 로 생성해서 사용하게 되면, MemberController 뿐만 아니라 다른 컨트롤러들도 MemberService 를 가져다 쓸 수 있음
        하지만, MemberService 는 별 기능이 없어서 여러 번 생성할 필요가 없이 하나만 만들어서 공용으로 사용하면 됨
    */
    //private final MemberService memberService = new MemberService();
    // => 스프링 컨테이너에 등록해서 사용 : 1개만 등록됨
    private final MemberService memberService;

    // MemberController 가 생성될 때 생성자 호출하는데
    // 생성자에 @Autowired 가 있으면
    // memberService 에 스프링이 스프링 컨테이너에 있는 MemberService 를 가져다가 연결을 시켜줌
    // = Dependency Injection (DI)
    // MemberService 는 순수 자바 코드여서 MemberController 와는 달리 스프링이 실행되도 아무 일도 일어나지 않음
    // => MemberService 코드에 @Service 명시해주어야 함
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}
