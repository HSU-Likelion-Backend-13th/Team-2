package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.domain.Member;
import hello.core.repository.MemberRepository;
import hello.core.repository.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    //등급마다 가겨이 다름을 생각
    // MemberRepository에 접근하여 등급 조회

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    //OrderServiceImpl에 DiscountPolicy구현체를 주입해줘야함.
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);

        /*
        OrderService는 할인을 몰라도 DiscountPolicy가 할인금액을 알려준다.
        SRP(단일 책임 원칙)준수
         */

        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName ,itemPrice, discountPrice);
    }
}
