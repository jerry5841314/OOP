package item;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import java.awt.*;
import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Freeze extends Sequence {

    private final StateMachine stateMachine;

    public Freeze(Item item, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        stateMachine.reset();
    }
}

