package item.staticItem.factoryItem;

import java.awt.Point;
import java.util.ArrayList;
import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.*;
import item.mobileItem.ingredient.Ingredient;
import item.staticItem.PlaceItemOn;
import model.SpriteShape;

public class FruitBasket extends Factory implements PlaceItemOn {

    private ArrayList<MobileItem> items;

    protected final Point thisLocation;
    protected int[] history;

    public FruitBasket(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "fruitbasket");
        thisLocation = location;

        items = new ArrayList<>();
    }

    @Override
    public MobileItem produceItem() {
        
        int random = (int) (Math.random() * 3);

        Ingredient ingredient;
        switch (random) {
            case 0:
                ingredient = new Apple(thisLocation, productShape);
                break;
            case 1:
                ingredient = new Orange(thisLocation, productShape);
                break;
            case 2:
                ingredient = new Banana(thisLocation, productShape);
                break;
            default:
                ingredient = null;
        }
        this.world.addSprite(ingredient);
        return ingredient;
        
    }


    public void clearTrashCan(){
        for(MobileItem item : items){
            item.getWorld().removeSprite(item);
            //item.setWorld(null);
        }
        items.clear();
    }

    @Override
    public void update(){
        clearTrashCan();
    }

    @Override
    public boolean hasSpace() {
        return true;
    }

    @Override
    public boolean canPickUpItem() {
        return false;
    }

    @Override
    public MobileItem popItem() {
        return null;
    }

    @Override
    public boolean hasItem() {
        return false;
    }

    @Override
    public Point itemPlaceLocation(MobileItem item) {
        return this.getLocation();
    }


    @Override
    public void tryAcquireItem(MobileItem item) {
        items.add(item);
        item.setLocation(itemPlaceLocation(item));
    }

}
