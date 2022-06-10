package item.staticItem.factoryItem;

import java.awt.Point;
import item.mobileItem.MobileItem;
import model.SpriteShape;

public class VegetableFactory extends Factory {

    protected final Point thisLocation;
    protected int[] history;

    public VegetableFactory(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "box");
        thisLocation = location;
        history = new int[2];
        history[0] = -1;
        history[1] = -1;
    }

    @Override
    public MobileItem produceItem() {
        /*
        int random;
        while (true) {
            random = (int) (Math.random() * 5);
            int flag = 0;
            for (int i : history)
                if (random == i)
                    flag = 1;
            if (flag != 1) {
                history[0] = history[1];
                history[1] = random;
                break;
            }
        }
        Ingredient ingredient;
        switch (random) {
            case 0:
                ingredient = new Onion(thisLocation);
                break;
            case 1:
                ingredient = new Maiz(thisLocation);
                break;
            case 2:
                ingredient = new Lechuga(thisLocation);
                break;
            case 3:
                ingredient = new Pepino(thisLocation);
                break;
            case 4:
                ingredient = new Tomato(thisLocation);
                break;
            default:
                ingredient = null;
        }
        this.world.addSprite(ingredient);
        return ingredient;
        */
        return null;
    }

}
