package hello.core.Order;

import hello.core.member.Member;
import hello.core.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    /*
     * 사용자의 등급을 조회하여 다른 할인 정책을 적용해야 함.
     * >> MemberRepository에 접근하여 사용자 등급을 조회하여야 함.
     */



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        MemberRepository memberRepository = new MemoryMemberRepository();
        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        Member member = memberRepository.findById(memberId); // user grade check
        /*
         *  현재 OrderService 입장에선 할인율은 몰라도 DiscountPolicy 인터페이스가 할인을 적용해주는 중.
         *  -> SRP (단일 책임 원칙) 준수하는 중
         */
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
