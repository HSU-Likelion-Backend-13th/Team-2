package hello.core;

import hello.core.domain.Grade;
import hello.core.domain.Member;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "lion", Grade.VIP);
        memberService.join(member);
        
        Member findMemeber = memberService.findMember(1L);
        
        System.out.println("findMember : " + findMemeber.getName());
        System.out.println("findMember : " + member.getName());
    }
}
