package item.mobileItem;

import fsm.FiniteStateMachine;
import item.Item;
import model.Direction;
import model.SpriteShape;
import character.Character;
import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import static fsm.Event.*;
import static model.Direction.LEFT;

public abstract class MobileItem extends Item {
 
    protected final FiniteStateMachine fsm;

    private final Set<Direction> directions = new CopyOnWriteArraySet<>();
    private Character owner;

    public MobileItem(Point location, SpriteShape shape) {
        super(location, shape);
        fsm = new FiniteStateMachine();
        owner = null;
    }
    public FiniteStateMachine getFsm() {
        return fsm;
    }
    public void move(Direction direction) {
        if (direction == LEFT || direction == Direction.RIGHT) {
            face = direction;
        }
        if(owner.mobileItemLocation() != getLocation()){
            this.setLocation(owner.mobileItemLocation());
        }
        if (!directions.contains(direction)) {
            this.directions.add(direction);
            fsm.trigger(MOVE);
        }
    }

    public void stop(Direction direction) {
        directions.remove(direction);
        if (directions.isEmpty()) {
            fsm.trigger(STOP);
        }
    }

    public void setOwner(Character character){
        owner = character;
    }

    public void beReleased(){
        owner = null;
    }

    public void freeze() {
        fsm.trigger(FREEZE);
    }

    public void update() {
        fsm.update();
    }

    public Set<Direction> getDirections() {
        return directions;
    }

    public boolean hasOwner(){
        return owner != null;
    }

    @Override
    public void render(Graphics g) {
        fsm.render(g);
    }

    @Override
    public Point getLocation() {
        return location;
    }

}
