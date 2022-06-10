package character;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import item.mobileItem.MobileItem;
import item.staticItem.PlaceItemOn;
import item.staticItem.factoryItem.Factory;
import model.Sprite;
import model.World;
import java.awt.*;
import java.util.List;

public class Picking extends Sequence {

    private final Character character;
    
    private final StateMachine stateMachine;

    public Picking(Character character, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.character = character;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        
        //g.setColor(Color.BLUE);
        //g.drawRect(pickArea.x, pickArea.y, pickArea.width, pickArea.height);
    }

    private void effectPickUp() {
        World world = character.getWorld();
        Rectangle pickArea = pickArea();
        var sprites = world.getSprites(pickArea);

        
        for (Sprite sprite : sprites) {
            if (character != sprite && sprite instanceof PlaceItemOn) {
                
                PlaceItemOn place = (PlaceItemOn) sprite;
                if(place.canPickUpItem() && place.hasItem()){
                    MobileItem pickedUpItem = place.popItem();
                    pickedUpItem.setLocation(character.mobileItemLocation());
                    character.addMobileItem(pickedUpItem);
                    pickedUpItem.setOwner(character);
                    break;
                }
                
            }
            if (character != sprite && sprite instanceof Factory) {
                Factory itemFactory = (Factory) sprite;
                MobileItem newItem = itemFactory.produceItem();
                newItem.setLocation(character.mobileItemLocation());
                character.addMobileItem(newItem);
                newItem.setOwner(character);
                break;
            }
        }
    }

    private Rectangle pickArea() {
        int offsetx = character.getBodyOffset().width;
        int offsety = character.getBodyOffset().height;
        int width = character.getBodySize().width;
        int height = character.getBodySize().height;
        return character.getArea(new Dimension(offsetx + width / 4, offsety + height / 4),
            character.getBodySize());
    }

    @Override
    protected void onSequenceEnd() {
        effectPickUp();
        currentPosition = 0;
        stateMachine.reset();
    }
}
