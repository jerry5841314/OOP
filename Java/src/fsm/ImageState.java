package fsm;

import java.awt.*;

public class ImageState implements State {
    private final Image image;
    protected ImageRenderer render;

    public ImageState(Image image, ImageRenderer render) {
        this.image = image;
        this.render = render;
    }
    public Image getImage() {
        return image;
    }

    @Override
    public void update() {
        // No effects: just an image
    }

    @Override
    public void render(Graphics g) {
        render.render(image, g);
    }
}
