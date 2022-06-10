package fsm;

import java.awt.*;

public interface StateMachine extends State {

    @Override
    default void render(Graphics g) {
        currentState().render(g);
    }

    void trigger(Object event);

    State currentState();

    void reset();
}
