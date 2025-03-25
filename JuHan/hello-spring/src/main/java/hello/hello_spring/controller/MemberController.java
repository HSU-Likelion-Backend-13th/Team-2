package hello.hello_spring.controller;


import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링 컨테이너라는 스프링 통 생성 -> 컨트롤러가 있으 멤버 컨트롤로 객체를 생성 스프링 넣은 후 관리
@Controller
public class MemberController {

    private final MemberService memberService;

    // 생성자에서 이렇게 쓰면 멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록되어 있는 멤버 서비스를 객차를 가져다 넣어줌(DI)
    // 스프링 컨테이너에서 멤버 서비스를 가져옴(연결 시켜줄 때 사용)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
