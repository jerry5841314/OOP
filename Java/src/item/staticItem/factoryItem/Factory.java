package item.staticItem.factoryItem;

import fsm.ImageRenderer;
import fsm.WaitingPerFrame;
import item.Idle;
import item.ItemImageRenderer;
import item.mobileItem.MobileItem;
import item.staticItem.StaticItem;
import model.SpriteShape;
import java.awt.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public abstract class Factory extends StaticItem {

    protected SpriteShape productShape;

    public Factory(Point location, SpriteShape shape, SpriteShape productShape, String factoryName) {
        super(location, shape);

        this.productShape = productShape;

        ImageRenderer imageRenderer = new ItemImageRenderer(this);
        idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/item/" + factoryName, imageRenderer)));
    }


    public abstract MobileItem produceItem();

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



}
