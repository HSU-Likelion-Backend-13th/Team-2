package hello.hello_spring.controller;


import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // html이 /members/new에 post 요청하면 여기로 데이터가 꽂힌다.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        // member 객체에 form 값을 집어넣어주는 장면이다.

        memberService.join(member);
        return "redirect:/";
    }
}
