package item.staticItem.factoryItem;

import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.Pie;
import model.SpriteShape;
import java.awt.*;

public class PieBox extends Factory {

    public PieBox(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "piebox");
    }

    @Override
    public MobileItem produceItem() {
        Pie newItem = new Pie(new Point(0, 0), productShape);
        this.world.addSprite(newItem);
        return newItem;
    }
    
}
