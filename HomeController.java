package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 처음에 localhost8080 을 들어오면 이것이 호출되면서
    @GetMapping("/")
    public String home(){
        return "home"; // home.html 호출됨
    }
/*
    아무것도 없으면 WelcomePage 로 static/index.html 로 연결됨

    웹 브라우저에서 요청이 오면 먼저 스프링 컨테이너 안에 관련 컨트롤러가 있는지 찾고,
    없으면 그 때  static 파일을 찾게 되어 있음
    => 요청이 왔을 때 먼저 HomeController 에서 찾아봄
       Mapping 된 것이 있으므로 mapping 된 url(home.html) 을 호출하고 끝남
       원래 있었던 static/index.html 은 무시됨
*/

}
