package order;

import java.util.ArrayList;

import item.mobileItem.MobileItem;

public class OrderList extends Consumer {
    
    public OrderList() {
        super();
        pendingOrders = new ArrayList<>();
        produceOrder(1, 2);
    }

    public void removeOrder(Order order){
        pendingOrders.remove(order);
        if(pendingOrders.isEmpty())
            produceOrder(1, 1);
    }

    public boolean contain(MobileItem item){
        for(Order order : pendingOrders){
            if(order.compatibleWithOrder(item)){
                return true;
            }
        }
        return false;
    }

    public void completeOrder(MobileItem item){
        for(Order order : pendingOrders){
            if(order.compatibleWithOrder(item)){
                removeOrder(order);
                break;
            }
        }
    }
}
