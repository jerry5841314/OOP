package item;

import fsm.ImageRenderer;
import model.Direction;

import java.awt.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ItemImageRenderer implements ImageRenderer {
    protected Item character;

    public ItemImageRenderer(Item character) {
        this.character = character;
    }

    @Override
    public void render(Image image, Graphics g) {
        Direction face = character.getFace();
        Rectangle range = character.getRange();
        
        if (face == Direction.LEFT) {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        } else {
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        }
        g.setColor(Color.RED);
        //g.drawRect(body.x, body.y, body.width, body.height);
    }
}
