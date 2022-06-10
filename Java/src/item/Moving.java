package item;

import fsm.CyclicSequence;
import fsm.ImageState;
import item.mobileItem.MobileItem;
import model.Direction;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Moving extends CyclicSequence {
    public static final String AUDIO_STEP1 = "step1";
    public static final String AUDIO_STEP2 = "step2";
    private final MobileItem mobileItem;

    public Moving(MobileItem mobileItem, List<ImageState> states) {
        super(states);
        this.mobileItem = mobileItem;
    }

    @Override
    public void update() {
        super.update();
        for (Direction direction : mobileItem.getDirections()) {
            mobileItem.getWorld().move(mobileItem, direction.translate());
        }   
    }

    @Override
    public String toString() {
        return "Moving";
    }
}
