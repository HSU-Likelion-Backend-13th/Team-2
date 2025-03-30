package hello.core;

import hello.core.domain.Grade;
import hello.core.domain.Member;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.service.MemberService;
import hello.core.service.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();
//
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        //ApplicationContext -> 스프링 컨테이너
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        OrderService orderService = ac.getBean("orderService",OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "EunMin", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "MacBook", 10000);
        System.out.println("Order : " + order);

        System.out.println("Price = " + order.calculatePrice());
    }
}
