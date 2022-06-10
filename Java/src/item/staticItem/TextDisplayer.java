package item.staticItem;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;

import model.Sprite;
import model.SpriteShape;

public class TextDisplayer extends Sprite {

    private int x, y;
    private int fontSize = 30;
    String text;
    Image image;
    Color color;

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    protected final SpriteShape shape;

    public TextDisplayer(int x, int y) {
        shape = new SpriteShape(new Dimension(146, 176), new Dimension(40, 38), new Dimension(66, 105));
        this.x = x;
        this.y = y;
        this.image = null;
    }
    
    public TextDisplayer(String pathName, int x, int y) {
        shape = new SpriteShape(new Dimension(146, 176), new Dimension(40, 38), new Dimension(66, 105));
        this.x = x;
        this.y = y;
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
        g.setColor(color);
        g.setFont(new Font("Marker Felt", Font.PLAIN, fontSize));
        g.drawString(text, x, y);
        if (image != null)
            g.drawImage(image, x, y + 50, 300, 300, null);
    }
    
}
