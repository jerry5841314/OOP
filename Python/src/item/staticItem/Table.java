package item.staticItem;

import fsm.ImageRenderer;
import fsm.WaitingPerFrame;
import item.Idle;
import item.ItemImageRenderer;
import item.mobileItem.MobileItem;
import model.SpriteShape;
import java.awt.*;
import java.util.ArrayList;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class Table extends StaticItem implements PlaceItemOn {

    private ArrayList<MobileItem> items;

    public Table(Point location, SpriteShape shape) {
        super(location, shape);
        items = new ArrayList<>();

        ImageRenderer imageRenderer = new ItemImageRenderer(this);
        idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/item/table", imageRenderer)));
    }

    @Override
    public Point itemPlaceLocation(MobileItem item) {
        int x = this.getX();
        int y = this.getY();
        int h = this.getRange().height;
        return new Point(x + 25, y - (h / 5));
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

    @Override
    public boolean hasSpace() {
        return items.size() < 1;
    }

    @Override
    public boolean hasItem() {
        return !items.isEmpty();
    }

    @Override
    public boolean canPickUpItem() {
        return true;
    }

    @Override
    public MobileItem popItem() {
        if(hasItem()){
            MobileItem pop = items.get(0);
            items.remove(0);
            return pop;
        }
        return null;
    }


}
