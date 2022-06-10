package controller;

import character.Character;
import model.Direction;
import model.World;
import java.util.List;
/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Game extends GameLoop {
    private Character p1;
    private Character p2;

    private World world;

    public Game() {}

    public void setWorld(World world){
        this.world = world;
    }
    
    public void setPlayers(List<Character> players){
        if(players.size()==1){
            this.p1 = players.get(0);
        }else{
            this.p1 = players.get(0);
            this.p2 = players.get(1);
        }
    }

    public void moveCharacter(int playerNumber, Direction direction) {
        Character character = getPlayer(playerNumber);

        character.move(direction);
        //if(character.hasMobileItem()){
        //    character.getMobileItem().move(direction);
        //}
        
    }

    public void stopCharacter(int playerNumber, Direction direction) {
        Character character = getPlayer(playerNumber);

        character.stop(direction);
        //if(character.hasMobileItem()){
        //    character.getMobileItem().stop(direction);
        //}
    }

    public void pickUpItem(int playerNumber) {
        Character character = getPlayer(playerNumber);

        if(! character.hasMobileItem()){
            character.pickUp();
        }
    }

    public void releaseItem(int playerNumber) {
        Character character = getPlayer(playerNumber);

        if(character.hasMobileItem()){
            //character.getMobileItem().freeze();
            character.tryRelease();
        }

    }

    public Character getPlayer(int playerNumber) {
        return playerNumber == 1 ? p1 : p2;
    }

    @Override
    public World getWorld() {
        return world;
    }
}
