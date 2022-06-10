package menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Endpage extends JPanel {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1280;
    private JLabel jscore;
    private JButton again;
    private Image im;
    private Boolean isend;

    public Endpage(int score) {
        isend = false;
        setLayout(null);

        jscore = new JLabel(String.format("Score : %d", score));
        add(jscore);
        jscore.setFont(new Font("Marker Felt", Font.BOLD, 50));
        jscore.setBounds((WIDTH - 300 - 200) / 2, 450, 400, 80);

        again = new JButton("");
        again.setFocusable(false);
        add(again);
        again.setFont(new Font("", Font.BOLD, 50));
        again.setBounds(214, 639, 630, 110);
        again.setOpaque(false);
        again.setFocusPainted(false);
        again.setContentAreaFilled(false);
        again.setBorderPainted(false);

 

        ActionListener ButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                isend = true;
            }
        };

        again.addActionListener(ButtonListener);

        try {
            im = ImageIO.read(new File("assets/menu/endbackground.png"));
        } catch (Exception e) {
        }
    }
    
    public Boolean isEnd(){
        return isend;
    }

    public void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g /* paintbrush */) {
        super.paintComponent(g);
        // Now, let's paint
        g.setColor(Color.WHITE); // paint background with all white
        g.fillRect(0, 0, WIDTH, HEIGHT + 100);
        g.drawImage(im, 0, 0, WIDTH - 300, HEIGHT + 100, null);
    }

}
