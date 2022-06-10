package item;

import fsm.CyclicSequence;
import fsm.ImageState;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Idle extends CyclicSequence {
    public List<ImageState> adapter;
    public Idle(List<ImageState> states) {
        super(states);
        adapter = states;
    }

    @Override
    public String toString() {
        return "Idle";
    }
}
