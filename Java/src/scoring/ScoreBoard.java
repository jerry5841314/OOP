package scoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;

import model.Sprite;
import model.SpriteShape;

public class ScoreBoard extends Sprite {

    private int score;

    private int x, y;
    public void setX(int x) {
        this.x = x;
    };
    public void setY(int y) {
        this.y = y;
    }
    public void setScore(int score) {
        this.score = score;
    }

    protected final SpriteShape shape;

    public ScoreBoard(int s, int x, int y) {
        shape = new SpriteShape(new Dimension(146, 176), new Dimension(40, 38), new Dimension(66, 105));
        score = s;
        this.x = x;
        this.y = y;
    }

    // public Score getScore() {
    // return score;
    // }

    public int getScore() {
        return score;
    }

    public void increaseScore(int increment) {
        score += increment;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Marker Felt", Font.PLAIN, 30));
        g.drawString(String.format("%03d", getScore()), x+10, y);
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

}
