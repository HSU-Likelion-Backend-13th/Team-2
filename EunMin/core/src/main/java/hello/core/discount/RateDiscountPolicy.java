package hello.core.discount;

import hello.core.domain.Grade;
import hello.core.domain.Member;

// 정률 할인 제도
public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountRate = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade()== Grade.VIP){
            return price * discountRate/100;
        }
        return 0;
    }
}
