package item.staticItem.abandoningItem;

import fsm.ImageRenderer;
import fsm.WaitingPerFrame;
import item.Idle;
import item.ItemImageRenderer;
import item.mobileItem.MobileItem;
import item.staticItem.PlaceItemOn;
import item.staticItem.StaticItem;
import model.SpriteShape;
import order.OrderList;
import scoring.ScoreComputer;
import scoring.ScoreBoard;
import java.awt.*;
import java.util.ArrayList;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class PickupWindow extends StaticItem implements PlaceItemOn {

    private OrderList pendingOrders;

    private ScoreBoard scoreboard;

    private ScoreComputer scoreComputer;

    private ArrayList<MobileItem> items;

    private long lastTime;

    private long period;

    public PickupWindow(Point location, SpriteShape shape, ScoreBoard scoreboard, ScoreComputer scoreComputer) {
        super(location, shape);
        items = new ArrayList<>();
        this.pendingOrders = new OrderList();
        this.scoreboard = scoreboard;
        this.scoreComputer = scoreComputer;

        ImageRenderer imageRenderer = new ItemImageRenderer(this);
        idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/item/pickupwindow", imageRenderer)));
        this.lastTime = System.currentTimeMillis() / 1000;
        this.period = 10;
    }

    @Override
    public Point itemPlaceLocation(MobileItem item) {
        return this.getLocation();
    }

    public OrderList getPendingOrders() {
        return pendingOrders;
    }


    @Override
    public void tryAcquireItem(MobileItem item) {
        items.add(item);
        item.setLocation(itemPlaceLocation(item));
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }

    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }

    public void clearItems(){
        for(MobileItem item : items){
            item.getWorld().removeSprite(item);
            //item.setWorld(null);
        }
        items.clear();
    }

    @Override
    public void update() {
        if (System.currentTimeMillis() / 1000 - lastTime > period) {
            lastTime = System.currentTimeMillis() / 1000;
            pendingOrders.produceOrder(1, 1);
        }
        for (MobileItem item : items) {
            if (pendingOrders.contain(item)) {
                pendingOrders.completeOrder(item);
                scoreboard.increaseScore(scoreComputer.computeScore(item));
            }else{
                scoreboard.setScore(Math.max(0, scoreboard.getScore()-10));
            }
        }
        clearItems();
    }

    @Override
    public boolean hasSpace() {
        return true;
    }

    @Override
    public boolean hasItem() {
        return false;
    }

    @Override
    public boolean canPickUpItem() {
        return false;
    }

    @Override
    public MobileItem popItem() {
        return null;
    }

}
