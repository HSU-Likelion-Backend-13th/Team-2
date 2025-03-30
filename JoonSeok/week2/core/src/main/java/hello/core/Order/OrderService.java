package hello.core.Order;

public interface OrderService {
    // 최종 주문 결과를 반환만 함 (DB에 저장은 생략)
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
