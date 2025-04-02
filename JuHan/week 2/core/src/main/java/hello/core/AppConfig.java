package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// Configuration이 붙은 AppConfig를 구성 정보로 사용하게 됨
// @Bean 어노테이션을 붙이게되면 Spring Container가 Bean이라고 적힌 메소드들을 모두 호출해서 반환된 객체를 Spring Container에 등록함
// -> 스프링 컨테이너에 등록된 객체를 스프링 빈이라 함
@Configuration
public class AppConfig {
    @Bean
    public MemberServiceImpl memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    private DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
