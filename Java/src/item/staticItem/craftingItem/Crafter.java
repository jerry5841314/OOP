package item.staticItem.craftingItem;

import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import item.Idle;
import item.ItemImageRenderer;
import item.mobileItem.MobileItem;
import item.staticItem.PlaceItemOn;
import item.staticItem.StaticItem;
import model.SpriteShape;
import java.awt.*;
import java.util.ArrayList;
import crafting.Recipe;

import static utils.ImageStateUtils.imageStatesFromFolder;

public abstract class Crafter extends StaticItem implements PlaceItemOn, Maker {

    protected long cookTime = 10 * 1000;

    protected int maxItemNumber = 2;


    protected long readyTime = Long.MAX_VALUE;

    protected ArrayList<MobileItem> items;

    protected Recipe currentRecipe;

    protected ArrayList<Recipe> recipes;


    private State cookingState;

    protected boolean isCooking = false;

    protected MobileItem pendingItem = null;


    protected SpriteShape productShape;

    public Crafter(Point location, SpriteShape shape, SpriteShape productShape, String name) {
        super(location, shape);

        this.productShape  = productShape;

        items = new ArrayList<>();

        recipes = new ArrayList<>();

    

        ImageRenderer imageRenderer = new ItemImageRenderer(this);
        idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/item/" + name + "/idle", imageRenderer)));
        cookingState = new WaitingPerFrame(4,
        new Idle(imageStatesFromFolder("assets/item/" + name + "/cooking", imageRenderer)));
    }

    @Override
    public Point itemPlaceLocation(MobileItem item) {
        int x = this.getX();
        int y = this.getY();
        int w = this.getRange().width;

        int index = items.indexOf(item);
        int itemWidth = item.getRange().width;
        int itemHeight = item.getRange().height;

        int wOffset = (w - itemWidth * 2) / 3;
        int hOffset = - (itemHeight) / 2;
        return new Point(x + wOffset + index * (wOffset + itemWidth), y + hOffset); 
    }


    @Override
    public void checkRecipes(){
        if(isCooking) {
            return;
        }
        for(Recipe recipe : recipes){
            if(recipe.craftAble(items)){
                currentRecipe = recipe;
                readyTime = System.currentTimeMillis() + cookTime;
                isCooking = true;
                pendingItem = currentRecipe.craft(items);
                for(var item : items){
                    item.setLocation(itemPlaceLocation(item));
                }
                System.out.println(readyTime);
            }
        }
    }

    @Override
    public void evokeCurrentRecipe() {
        if(isCooking && pendingItem != null && System.currentTimeMillis() >= readyTime){
            readyTime = Long.MAX_VALUE;
            isCooking = false;

            pendingItem.setWorld(this.getWorld());
            this.getWorld().addSprite(pendingItem);
            
            items.add(pendingItem);
            pendingItem.setLocation(itemPlaceLocation(pendingItem));
            
            pendingItem = null;
        }
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
    public void render(Graphics g) {
        idle.render(g); 
        if(isCooking){
            cookingState.render(g);
        }
        
    } 

    @Override  
    public void update() {
        checkRecipes();
        evokeCurrentRecipe();
        currentRecipe = null;
    }

    @Override
    public boolean hasSpace() {
        return !isCooking && items.size() < maxItemNumber;
    }

    @Override
    public boolean hasItem() {
        return !items.isEmpty();
    }

    @Override
    public boolean canPickUpItem() {
        return !isCooking;
    }

    @Override
    public MobileItem popItem() {
        if(hasItem()){
            MobileItem pop = items.get(items.size() - 1);
            items.remove(items.size() - 1);
            return pop;
        }
        return null;
    }

}
