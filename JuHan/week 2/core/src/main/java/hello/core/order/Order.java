package hello.core.order;

public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice; // 상품 가격
    private int discoutPrice; //할인 가격

    public Order(Long memberId, String itemName, int itemPrice, int discoutPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discoutPrice = discoutPrice;
    }

    @Override
    public String toString() { // 객체를 쉽게 보기 위해서 toString() , OrderApp의 실행 결과
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discoutPrice=" + discoutPrice +
                '}';
    }

    public int calculatePrice() { // 최종 가격
        return itemPrice-discoutPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscoutPrice() {
        return discoutPrice;
    }

    public void setDiscoutPrice(int discoutPrice) {
        this.discoutPrice = discoutPrice;
    }
}
