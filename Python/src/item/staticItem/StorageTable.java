package item.staticItem;

import fsm.ImageRenderer;
import fsm.WaitingPerFrame;
import item.Idle;
import item.ItemImageRenderer;
import model.SpriteShape;
import java.awt.*;
import static utils.ImageStateUtils.imageStatesFromFolder;

public class StorageTable extends Table{

    public StorageTable(Point location, SpriteShape shape) {
        super(location, shape);
        ImageRenderer imageRenderer = new ItemImageRenderer(this);
        idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/item/storagetable", imageRenderer)));
    }
}
