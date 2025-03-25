package hello.hello_spring.controller;


import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import net.bytebuddy.description.ByteCodeElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    // 기본적으로 url창에다 엔터 치는 것은 getmapping -> 주로 조회할 때 사용
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    // Post는 데이터를 폼같은 데 넣어서 전달할 때 사용
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        // 처음 화면으로 리턴
        return "redirect:/";
    }
   


