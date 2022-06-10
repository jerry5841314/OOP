package views;

import controller.Game;
import model.Direction;
import model.World;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import menu.Menu;
import menu.Endpage;
/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class GameView extends JFrame {

    private final String wds[] = {"Hi There","Quick","Fresh","Homemade","DoaDaoDao","Menu","YaYa"};
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    public static final int P1 = 1;
    public static final int P2 = 2;
    private final Canvas canvas = new Canvas();
    private Game game;
    private Menu menu;
    public Canvas getCanvas() {
        return canvas;
    }

    public GameView(Game game) throws HeadlessException {
        this.game = game;
        game.setView(canvas);
    }
    
    public void launchMenu(){
        setTitle("Make It on Time: "+wds[(new Random().nextInt(7))]);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH - 300, HEIGHT + 100);
        Menu menu = new Menu(game);
        this.menu = menu;
        setContentPane(menu);
        setVisible(true);
        menu.render();
        while(game.isStart()!=true){
            try {
                Thread.sleep(10);
                //menu.render();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public void launchEndPage(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH - 300, HEIGHT + 100);
        Endpage endpage = new Endpage(game.getWorld().getScore());
        if(game.getWorld().getScore()>=200)setTitle("Make It on Time: Well Done");
        else if(game.getWorld().getScore()==0)setTitle("Make It on Time: Sloths");
        else setTitle("Make It on Time: Time's up");
        setContentPane(endpage);
        setVisible(true);

        while(endpage.isEnd() == false){
            try {
                Thread.sleep(10);
                menu.render();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }



    public Menu getMenu(){
        return menu;
    }

    public void launch() {
        // GUI Stuff
        setTitle("Make It on Time");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvas);
        setSize(WIDTH-150, HEIGHT+100);
        setContentPane(canvas);
        setVisible(true);

        // Keyboard listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game.moveCharacter(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        game.moveCharacter(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        game.moveCharacter(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.moveCharacter(P1, Direction.RIGHT);
                        break;
                    
                    // case KeyEvent.VK_Q:
                    //    game.pickUpItem(P1);
                    //    break;
                    // case KeyEvent.VK_E:
                    //     game.releaseItem(P1);
                    //     break;
                    
                    case KeyEvent.VK_I:
                        game.moveCharacter(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        game.moveCharacter(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        game.moveCharacter(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        game.moveCharacter(P2, Direction.RIGHT);
                        break;

                    case KeyEvent.VK_U:
                        game.pickUpItem(P2);
                        break;
                    case KeyEvent.VK_O:
                        game.releaseItem(P2);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game.stopCharacter(P1, Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        game.stopCharacter(P1, Direction.DOWN);
                        break;
                    case KeyEvent.VK_A:
                        game.stopCharacter(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.stopCharacter(P1, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_I:
                        game.stopCharacter(P2, Direction.UP);
                        break;
                    case KeyEvent.VK_K:
                        game.stopCharacter(P2, Direction.DOWN);
                        break;
                    case KeyEvent.VK_J:
                        game.stopCharacter(P2, Direction.LEFT);
                        break;
                    case KeyEvent.VK_L:
                        game.stopCharacter(P2, Direction.RIGHT);
                        break;
                    case KeyEvent.VK_Q:
                        game.pickUpItem(P1);
                        break;
                    case KeyEvent.VK_E:
                        game.releaseItem(P1);
                        break;
                }
            }
            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
    }

    public static class Canvas extends JPanel implements View {
        private World world;
        // private Image image = null;
        
        
        @Override
        public void render(World world) {
            this.world = world;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            /*
            if(image == null){
                try{
                image = ImageIO.read(new File("assets/Floor.jpeg"));}
                catch(Exception e){}
            }*/
            // Now, let's paint
            g.setColor(Color.decode(world.colorOfWorld())); // paint background with all white
            //g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);
            g.fillRect(0, 0, 2000, 1000);
            //if(image !=null)g.drawImage(image,0,0,this);
            //g.drawImage(img, x, y, observer)

            world.render(g); // ask the world to paint itself and paint the sprites on the canvas
        }
    }
}
