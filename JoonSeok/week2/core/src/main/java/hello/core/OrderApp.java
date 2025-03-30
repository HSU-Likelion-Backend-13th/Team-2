package hello.core;

import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderService orderService = ac.getBean("orderService", OrderServiceImpl.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "likelion", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "MacBook", 20000);
        System.out.println("Order: " + order);
        System.out.println("last price: " + order.calculatePrice());

    }
}
