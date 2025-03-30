package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
    /*
     * 사용자의 등급을 조회하여 다른 할인 정책을 적용해야 함.
     * >> MemberRepository에 접근하여 사용자 등급을 조회하여야 함.
     */

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // OrderServiceImpl이 아닌, 다른 장소에서 생성되어 의존성 주입해주기
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // user grade check
        /*
         *  현재 OrderService 입장에선 할인율은 몰라도 DiscountPolicy 인터페이스가 할인을 적용해주는 중.
         *  -> SRP (단일 책임 원칙) 준수하는 중
         */
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
