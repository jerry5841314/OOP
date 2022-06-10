package order;

import item.mobileItem.MobileItem;

public class Order {
    
    private MobileItem orderContent;

    public Order(MobileItem item){
        this.orderContent = item;
    }

    public boolean compatibleWithOrder(MobileItem item){
        return item.getClass().equals(orderContent.getClass());
    }
    public MobileItem getOrderContent() {
        return orderContent;
    }

}
