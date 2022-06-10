package model;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;
import scoring.ScoreBoard;
import timer.Timer;
import item.staticItem.TextDisplayer;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class World {

    private final int gameTime = 180;

    private final CollisionHandler collisionHandler;

    protected final int worldWidth;

    protected final int worldHeight;

    private final List<Sprite> sprites = new CopyOnWriteArrayList<>();



    protected ScoreBoard scoreboard;

    protected Timer timer;

    protected TextDisplayer timerDisplayer;



    public World(CollisionHandler collisionHandler, int width, int height, List<Sprite> sprites,
            JPanel panel) {
        
        this.worldWidth = width;

        this.worldHeight = height;
    
        this.collisionHandler = collisionHandler;

        for(Sprite sprite: sprites){
            addSprite(sprite);
        }

        // The following part should be custumized in your world example
        /*
        var recipePicture = new FixedImageDisplayer("assets/recipe2.png", 20 + 900, 720 - 180 * 2043 / 915, 180, 180 * 2043 / 915, panel);
        var timerBackground = new FixedImageDisplayer("assets/newtimer.png", 20 + 900, 0, 180, 138, panel);
        var scoreboardBackground = new FixedImageDisplayer("assets/scoreboard.png",20 + 900,140,180,180,panel);
        var orderListBackground = new FixedImageDisplayer("assets/orderlistbg.png", 0, 600, 900, 120, panel);
        
        addSprite(recipePicture);
        addSprite(timerBackground);
        addSprite(scoreboardBackground);
        addSprite(orderListBackground);


        this.scoreboard = new ScoreBoard(20 + 0,970, 210);
        setScoreboard(scoreboard);
        
        this.timer = new Timer();
        this.timerDisplayer = new TextDisplayer(20 + 935, 90);
        this.timerDisplayer.setText("Timer");
        this.timerDisplayer.setFontSize(25);
        addSprite(timerDisplayer);
        */
    }

    public void update() {
        if(!timer.started()){
            timer.startTimer(gameTime);
        }
        if(timer.getRemainTime()<=10){
            timerDisplayer.setColor(Color.RED);
        }
        timerDisplayer.setText(timer.getCountString());
        for (Sprite sprite : sprites) {
            sprite.update();
        }
    }

    public void addSprites(Sprite... sprites) {
        stream(sprites).forEach(this::addSprite);
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.setWorld(this);
    }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
        sprite.setWorld(null);
    }

    public void move(Sprite from, Dimension offset) {
        Point originalLocation = new Point(from.getLocation());
        from.getLocation().translate(offset.width, offset.height);

        Rectangle body = from.getBody();
        // collision detection
        for (Sprite to : sprites) {
            if (to != from && body.intersects(to.getBody())) {
                collisionHandler.handle(originalLocation, from, to);
            }
        }
    }

    public Collection<Sprite> getSprites(Rectangle area) {
        return sprites.stream()
                .filter(s -> area.intersects(s.getBody()))
                .collect(toSet());
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public int getScore(){
        return this.scoreboard.getScore();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setScoreboard(ScoreBoard scoreboard){
        this.scoreboard = scoreboard;
        addSprite(scoreboard);
    }

    // Actually, directly couple your model with the class "java.awt.Graphics" is not a good design
    // If you want to decouple them, create an interface that encapsulates the variation of the Graphics.
    public void render(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }

    public abstract Point defaultPlayer1Location();

    public abstract Point defaultPlayer2Location();

    public abstract SpriteShape getCharacterShape();

    public abstract String colorOfWorld();

}
