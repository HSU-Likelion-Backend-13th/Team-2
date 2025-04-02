package hello.core.order;

public interface OrderService {
    // 최종 주문 결과를 변(원래는 DB에 저장해야함 -> 복잡해지므로 생략)

    Order createOrder(Long memberId, String itemName, int itemPrice);


}
