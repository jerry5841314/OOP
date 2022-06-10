package item.staticItem;

import fsm.ImageRenderer;
import fsm.WaitingPerFrame;
import item.Idle;
import item.ItemImageRenderer;
import item.mobileItem.MobileItem;
import model.SpriteShape;
import java.awt.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class Barrel extends Table {

    public Barrel(Point location, SpriteShape shape) {
        super(location, shape);
        ImageRenderer imageRenderer = new ItemImageRenderer(this);
        idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/item/barrel", imageRenderer)));
    }

    @Override
    public Point itemPlaceLocation(MobileItem item) {
        int x = this.getX();
        int y = this.getY();
        int w = this.getRange().width;
        int h = this.getRange().height;
        int itemWidth = item.getRange().width;
        int itemHeight = item.getRange().height;
        int wOffset = (w - itemWidth) / 2;
        int hOffset = (h - itemHeight) / 2;
        return new Point(x + wOffset, y + hOffset);
    }
    
}
