package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import controller.Game;

public class Menu extends JPanel {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    private int playernum;
    private int worldnum;
    private Game game;
    private JLabel players, world;
    private JButton playerNum, worldNum;
    private Image im2;
    

    public Menu(Game game) {
        this.game = game;
        playernum = 1;
        worldnum = 1;

        setLayout(null);

        players = new JLabel("Players");
        add(players);
        players.setFont(new Font("Marker Felt", Font.BOLD, 50));
        players.setBounds(50, 450, 200, 80);

        playerNum = new JButton("1");
        playerNum.setFocusable(false);
        add(playerNum);
        playerNum.setFont(new Font("Marker Felt", Font.BOLD, 50));
        playerNum.setBounds(250, 470, 200, 50);
        playerNum.setOpaque(false);
        playerNum.setFocusPainted(false);
        playerNum.setContentAreaFilled(false);
        playerNum.setBorderPainted(false);

        world = new JLabel("World");
        add(world);
        world.setFont(new Font("Marker Felt", Font.BOLD, 50));
        world.setBounds(550, 450, 200, 80);

        worldNum = new JButton("1");
        worldNum.setFocusable(false);
        add(worldNum);

        worldNum.setBounds(400, 250, 200, 50);
        worldNum.setFont(new Font("Marker Felt", Font.BOLD, 50));
        worldNum.setBounds(750, 465, 200, 50);
        worldNum.setOpaque(false);
        worldNum.setFocusPainted(false);
        worldNum.setContentAreaFilled(false);
        worldNum.setBorderPainted(false);

        ImageIcon start = new ImageIcon("assets/menu/start.png");
        Image im = start.getImage().getScaledInstance(630, 110, java.awt.Image.SCALE_SMOOTH);
        start = new ImageIcon(im);
        JButton startB = new JButton("");
        startB.setFocusable(false);
        add(startB);
        startB.setBounds(214, 639, 630, 110);
        startB.setOpaque(false);
        playerNum.setFocusPainted(false);
        startB.setContentAreaFilled(false);
        startB.setBorderPainted(false);

        ActionListener ButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String cmd = ae.getActionCommand();
                switch (cmd) {
                    case "P":
                        playernum = playernum % 2 + 1;
                        playerNum.setText(String.format("%d", playernum));
                        break;
                    case "W":
                        worldnum = worldnum % 5 + 1;
                        worldNum.setText(String.format("%d", worldnum));
                        break;
                    case "Start":
                        game.gameStart();
                        break;
                }
            }
        };

        playerNum.setActionCommand("P");
        playerNum.addActionListener(ButtonListener);
        worldNum.setActionCommand("W");
        worldNum.addActionListener(ButtonListener);
        startB.setActionCommand("Start");
        startB.addActionListener(ButtonListener);

        try {
            im2 = ImageIO.read(new File("assets/menu/menubackground.png"));
            
        } catch (Exception e) {
        }
    }

    public void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g /* paintbrush */) {
        super.paintComponent(g);
        // Now, let's paint
        g.setColor(Color.WHITE); // paint background with all white
        g.fillRect(0, 0, WIDTH, HEIGHT+100);
        g.drawImage(im2, 0, 0, WIDTH - 300, HEIGHT + 100, null);
        //g.drawImage(ims, 100, 600, 800, 110, null);
 
    }

    public int getPlayernum() {
        return playernum;
    }

    public int getWorldnum() {
        return worldnum;
    }

    public Game getGame() {
        return game;
    }

}
