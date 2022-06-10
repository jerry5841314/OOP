package item.staticItem;

import fsm.State;
import item.Item;
import model.SpriteShape;
import java.awt.*;

public abstract class StaticItem extends Item {

    protected State idle;

    public StaticItem(Point location, SpriteShape shape) {
        super(location, shape);
    }

    public void update() {
        //fsm.update();
    }

    @Override
    public void render(Graphics g) {
        //super.render(g);
        idle.render(g);
    }

    @Override
    public Point getLocation() {
        return location;
    }


}
