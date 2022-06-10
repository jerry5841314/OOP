package controller;

import model.*;
import views.GameView;
import views.View;
import java.util.List;
import java.util.ArrayList;
import character.Character;
import media.AudioPlayer;
import character.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class GameLoop {

    protected boolean running;
    public Thread gmp;
    protected GameView gameview;
    public GameView getGameview() {
        return gameview;
    }
    
    protected View view;
    protected boolean gamestart = false;

    public void setView(View view) {
        this.view = view;
    }

    public void gameStart(){
        gamestart = true;
    }

    public Boolean isStart(){
        return gamestart;
    }

    public void start(Game game) {
        AudioPlayer.playSoundsInLoop("menumusic");
        AudioPlayer.setVolume(0.5);
        gameview = new GameView(game);
        gameview.launchMenu();
        
        List<Sprite> Sprites = new ArrayList<>();
        List<Character> players = new ArrayList<>();

        // world selection
        World world;

        int worldWidth = 1080;
        int worldHeight = 720;

        int worldSelection = gameview.getMenu().getWorldnum();
        System.out.println(worldSelection);
        switch (worldSelection) {
            case 1:
                worldWidth = 1080;
                worldHeight = 720;
                world = new World1green(new CharacterCollisionHandler(), worldWidth, worldHeight, Sprites, gameview.getCanvas());
                game.setWorld(world);
                break;
            case 2:
                worldWidth = 1080;
                worldHeight = 720;
                world = new World2blue(new CharacterCollisionHandler(), worldWidth, worldHeight, Sprites, gameview.getCanvas());
                game.setWorld(world);
                break;
            case 3:
                worldWidth = 1080;
                worldHeight = 720;
                world = new World3yellow(new CharacterCollisionHandler(), worldWidth, worldHeight, Sprites, gameview.getCanvas());
                game.setWorld(world);
                break;
            case 4:
                worldWidth = 1080;
                worldHeight = 720;
                world = new World4orange(new CharacterCollisionHandler(), worldWidth, worldHeight, Sprites,
                        gameview.getCanvas());
                game.setWorld(world);
                break;
            case 5:
                worldWidth = 1080;
                worldHeight = 720;
                world = new World5pink(new CharacterCollisionHandler(), worldWidth, worldHeight, Sprites,
                        gameview.getCanvas());
                game.setWorld(world);
                break;
            default:
                worldWidth = 1080;
                worldHeight = 720;
                world = new World1green(new CharacterCollisionHandler(), worldWidth, worldHeight, Sprites, gameview.getCanvas());
                game.setWorld(world);
        }


        if(gameview.getMenu().getPlayernum() == 1){
            Character p1 = new Character(world.defaultPlayer1Location(), world.getCharacterShape());
            players.add(p1);
            //Sprites.add(p1);
            world.addSprite(p1);
        }
        else{
            Character p1 = new Character(world.defaultPlayer1Location(), world.getCharacterShape());
            Character p2 = new Character(world.defaultPlayer2Location(), world.getCharacterShape());
            players.add(p1);
            players.add(p2);
            //Sprites.add(p1);
            //Sprites.add(p2);
            world.addSprite(p1);
            world.addSprite(p2);
        }

        game.setPlayers(players);

        AudioPlayer.stopSounds();
        AudioPlayer.playSoundsInLoop("gamemusic");
        AudioPlayer.setVolume(0.1);
        gmp = new Thread(this::gameLoop);
        gmp.start();
        gameview.launch();
        
        while(world.getTimer().ended() == false){
            delay(15);            
        }
        AudioPlayer.stopSounds();
        AudioPlayer.playSoundsInLoop("endmusic");
        gameview.launchEndPage();
        gameview.dispose();
        AudioPlayer.stopSounds();
        AudioPlayer.setVolume(0.5);
    }

    private void gameLoop() {
        // delay(1000);
        running = true;
        while (running) {
            World world = getWorld();
            world.update();
            view.render(world);
            delay(15);
            if(world.getTimer().ended()){
                System.out.println("Game end");
                // gameview.dispose();
                break;
            }
        }
    }

    protected abstract World getWorld();

    public void stop() {
        running = false;
    }

    private void delay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
