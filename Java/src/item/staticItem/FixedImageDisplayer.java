package item.staticItem;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Sprite;
import model.SpriteShape;

public class FixedImageDisplayer extends Sprite {

    private int x, y, width, height;
    Image image;
    private JPanel jpanel;
    
    protected final SpriteShape shape;
    
    public FixedImageDisplayer(String pathName, int x, int y, int width, int height, JPanel jpanel) {
        
        shape = new SpriteShape(new Dimension(146, 176), new Dimension(40, 38), new Dimension(66, 105));
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.jpanel = jpanel;
        BufferedImage bufferImage = null;
        try {
            bufferImage = (BufferedImage) ImageIO.read(new File(pathName));
        }
        catch (Exception e) {
        }
        this.image = bufferImage;
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

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, jpanel);
    }
    
}
