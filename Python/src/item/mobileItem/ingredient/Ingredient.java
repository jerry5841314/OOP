package item.mobileItem.ingredient;

import fsm.ImageRenderer;
import fsm.ImageState;
import fsm.State;
import fsm.WaitingPerFrame;
import model.SpriteShape;
import java.awt.*;
import java.util.List;
import item.*;
import item.mobileItem.MobileItem;
import static fsm.FiniteStateMachine.Transition.from;
import static fsm.Event.*;
import static utils.ImageStateUtils.imageStatesFromFolder;

public class Ingredient extends MobileItem {

    protected final String ingredientName;

    private List<ImageState> imageStates;

    public Ingredient(Point location, SpriteShape shape, String name) {
        super(location, shape);
        
        ingredientName = name;
        ImageRenderer imageRenderer = new ItemImageRenderer(this);
        imageStates =imageStatesFromFolder("assets/item/" + ingredientName, imageRenderer);



        State idle = new WaitingPerFrame(4, new Idle(imageStatesFromFolder("assets/item/" + ingredientName, imageRenderer)));
        State moving = new WaitingPerFrame(2,
                new Moving(this, imageStatesFromFolder("assets/item/" + ingredientName, imageRenderer)));
        State freeze = new WaitingPerFrame(0,
                new Freeze(this, fsm, imageStatesFromFolder("assets/item/" + ingredientName, imageRenderer)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(MOVE).to(moving));
        fsm.addTransition(from(moving).when(STOP).to(idle));
        fsm.addTransition(from(idle).when(FREEZE).to(freeze));
        fsm.addTransition(from(moving).when(FREEZE).to(freeze));
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

    public String getIngredientName() {
        return ingredientName;
    }
    public List<ImageState> getImageStates() {
        return imageStates;
    }
}
