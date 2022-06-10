package fsm;

import java.awt.*;

public class WaitingPerFrame implements State {
    private final State state;
    private final long waitingLoopPerFrame;
    private long remaining;

    public WaitingPerFrame(long waitingLoopPerFrame, State state) {
        this.remaining = this.waitingLoopPerFrame = waitingLoopPerFrame;
        this.state = state;
    }

    public State getCurrentState(){
        return state;
    }

    @Override
    public void update() {
        if (--remaining <= 0) {
            remaining = waitingLoopPerFrame;
            state.update();
        }
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
