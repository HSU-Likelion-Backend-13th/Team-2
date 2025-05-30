package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//정률 확인제
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        if( member.getGrade() == Grade.VIP){
            return price * discountRate / 100;
        }
        return 0;
    }
}
