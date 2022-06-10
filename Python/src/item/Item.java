package item;

import model.Sprite;
import model.SpriteShape;
import java.awt.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class Item extends Sprite {

    protected final SpriteShape shape;

    public Item(Point location, SpriteShape shape) {
        this.location = location;
        this.shape = shape;
    }
    
}
