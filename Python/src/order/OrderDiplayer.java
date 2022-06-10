package order;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import item.mobileItem.ingredient.Ingredient;
import item.staticItem.abandoningItem.PickupWindow;
import model.Sprite;
import model.SpriteShape;

public class OrderDiplayer extends Sprite{
    
    protected PickupWindow pickupWindow;
    private int x, y;
    String text;
    Image image = null;
    Image bg ;
    ArrayList<Image> imgs = new ArrayList<>();
    JPanel panel;
    //Color color;
    protected final SpriteShape shape;


    public OrderDiplayer(int x, int y, PickupWindow puw,JPanel ot) {
        shape = new SpriteShape(new Dimension(146, 176), new Dimension(40, 38), new Dimension(66, 105));

        panel = ot;
        this.x = x;
        this.y = y;
        try{
        bg = ImageIO.read(new File("assets/orderlistbg.png"));}catch(Exception e){
            System.out.println(e);
        }
        //super("assets/recipe/1.png", x, y);
        pickupWindow = puw;
        
    }

    @Override
    public void update() {
        text=(getOrderString());
    }
    
    private String getOrderString(){
        String s = "Order: ";
        imgs.clear();
        try{
        for (var v:pickupWindow.getPendingOrders().pendingOrders){
            s+=v.getOrderContent().getClass().getSimpleName();
            s+=", ";
            var test =v.getOrderContent();
            if(test instanceof Ingredient){
                image = ((Ingredient) test).getImageStates().get(0).getImage();
                imgs.add(image);
            }
        }}catch(Exception e){
            
        }
        return s;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        //g.drawImage(bg,0,720,900,120, panel);
        for (int i = 0;i<imgs.size();i++){
            g.drawImage(imgs.get(i), x-8+106*i, y+10,80,80, panel);

        }
        //g.drawString("Order:", x, y+23);

        
        
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
