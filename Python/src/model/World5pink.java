package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;


import javax.swing.JPanel;
import timer.Timer;

import item.mobileItem.ingredient.*;
import item.staticItem.*;
import item.staticItem.abandoningItem.PickupWindow;
import item.staticItem.abandoningItem.TrashCan;
import item.staticItem.craftingItem.ApplePieStove;
import item.staticItem.craftingItem.FriedEggStove;
import item.staticItem.craftingItem.SaladBowl;
import item.staticItem.craftingItem.SandwichMaker;
import item.staticItem.factoryItem.BreadBasket;
import item.staticItem.factoryItem.CheeseBlock;
import item.staticItem.factoryItem.EggBasket;
import item.staticItem.factoryItem.FruitBasket;
import item.staticItem.factoryItem.PieBox;
import item.staticItem.factoryItem.SpinachGarden;
import item.staticItem.factoryItem.TomatoBasket;
import order.OrderDiplayer;
import scoring.ScoreBoard;
import scoring.ScoreComputer;

public class World5pink extends World {

    private final int gridWidth = 75;

    private final int gridHeight = 75;

    private final SpriteShape staticItemShape 
        = new SpriteShape(new Dimension(75, 75), new Dimension(10, 10), new Dimension(55, 55));

    private final SpriteShape mobileItemShape
        = new SpriteShape(new Dimension(30, 30), new Dimension(0, 0), new Dimension(30, 30));
    
    public World5pink(CollisionHandler collisionHandler, int width, int height, List<Sprite> sprites, JPanel panel) {
        super(collisionHandler, width, height, sprites, panel);

        // setting for outside game panel stuffs
        // including timer, scoreboard, recipe, orderlist
        // if you follow the size setting of world example 3
        // then only modifiy the path of the .png files
        var recipePicture = new FixedImageDisplayer("assets/worldexample5/recipe.png", 20 + 900, 720 - 180 * 1600 / 701 + 20, 180, 180 * 1600 / 701, panel);
        var timerBackground = new FixedImageDisplayer("assets/worldexample5/timer.png", 20 + 900, 0, 180, 138, panel);
        var scoreboardBackground = new FixedImageDisplayer("assets/worldexample5/scoreboard.png",20 + 900, 140, 180, 180,panel);
        var orderListBackground = new FixedImageDisplayer("assets/worldexample5/orderlistbg.png", 0, 600, 900, 120, panel);

        addSprite(recipePicture);
        addSprite(timerBackground);
        addSprite(scoreboardBackground);
        addSprite(orderListBackground);

        this.scoreboard = new ScoreBoard(0, gridWidth * 13, gridHeight * 14 / 5);
        setScoreboard(scoreboard);
        
        this.timer = new Timer();
        this.timerDisplayer = new TextDisplayer(computeXCoordinate(12.7), computeYCoordinate(1.2));
        this.timerDisplayer.setText("Timer");
        this.timerDisplayer.setFontSize(25);
        addSprite(timerDisplayer);
     
        //
        // the part you actually design your map
        //
        for (int i = 4; i <= 6; ++i)
            addSprite(new Plant1(computeCoordinate(i, 0), staticItemShape));
        addSprite(new Plant1(computeCoordinate(2, 1), staticItemShape));
        addSprite(new Plant1(computeCoordinate(8, 1), staticItemShape));

        for (int i = 3; i <= 7; ++i)
            addSprite(new WoodPlatform(computeCoordinate(0, i), staticItemShape));
        for (int i = 3; i <= 7; ++i)
            addSprite(new WoodPlatform(computeCoordinate(10, i), staticItemShape));
        for (int i = 1; i <= 4; ++i)
            addSprite(new WoodPlatform(computeCoordinate(i, 7), staticItemShape));
        for (int i = 6; i <= 9; ++i)
            addSprite(new WoodPlatform(computeCoordinate(i, 7), staticItemShape));
        
        double[] tmp;
        List<double[]> tmpList = new ArrayList<>();
        tmp = new double[] { 3, 1 };
        tmpList.add(tmp);
        tmp = new double[] { 7, 1 };
        tmpList.add(tmp);
        tmp = new double[] { 1, 2 };
        tmpList.add(tmp);
        tmp = new double[] { 4, 2 };
        tmpList.add(tmp);
        tmp = new double[] { 6, 2 };
        tmpList.add(tmp);
        tmp = new double[] { 9, 2 };
        tmpList.add(tmp);
        tmp = new double[] { 2, 3 };
        tmpList.add(tmp);
        tmp = new double[] { 8, 3 };
        tmpList.add(tmp);
        tmp = new double[] { 4, 4 };
        tmpList.add(tmp);
        tmp = new double[] { 6, 4 };
        tmpList.add(tmp);
        tmp = new double[] { 3, 5 };
        tmpList.add(tmp);
        tmp = new double[] { 7, 5 };
        tmpList.add(tmp);
        Collections.shuffle(tmpList);
        addSprite(new EggBasket(computeCoordinate(tmpList.get(0)[0], 
                tmpList.get(0)[1]), staticItemShape, mobileItemShape));
        addSprite(new FruitBasket(computeCoordinate(tmpList.get(1)[0],
                tmpList.get(1)[1]), staticItemShape, mobileItemShape));
        addSprite(new PieBox(computeCoordinate(tmpList.get(2)[0], 
                tmpList.get(2)[1]), staticItemShape, mobileItemShape));
        addSprite(new BreadBasket(computeCoordinate(tmpList.get(3)[0],
                tmpList.get(3)[1]), staticItemShape, mobileItemShape));
        addSprite(new CheeseBlock(computeCoordinate(tmpList.get(4)[0], 
                tmpList.get(4)[1]), staticItemShape, mobileItemShape));
        addSprite(new SpinachGarden(computeCoordinate(tmpList.get(5)[0],
                tmpList.get(5)[1]), staticItemShape, mobileItemShape));
        addSprite(new TomatoBasket(computeCoordinate(tmpList.get(6)[0],
                tmpList.get(6)[1]), staticItemShape, mobileItemShape));
        addSprite(new ApplePieStove(computeCoordinate(tmpList.get(7)[0], 
                tmpList.get(7)[1]), staticItemShape, mobileItemShape));
        addSprite(new SaladBowl(computeCoordinate(tmpList.get(8)[0],
                tmpList.get(8)[1]), staticItemShape, mobileItemShape));
        addSprite(new SandwichMaker(computeCoordinate(tmpList.get(9)[0], 
                tmpList.get(9)[1]), staticItemShape, mobileItemShape));
        addSprite(new FriedEggStove(computeCoordinate(tmpList.get(10)[0],
                tmpList.get(10)[1]), staticItemShape, mobileItemShape));
        addSprite(new TrashCan(computeCoordinate(tmpList.get(11)[0], 
                tmpList.get(11)[1]), staticItemShape));

        ScoreComputer scoreComputer = new ScoreComputer(new ArrayList<>());
        scoreComputer.addScoreConversion(new ApplePie(null, null), 30);
        scoreComputer.addScoreConversion(new FriedEgg(null, null), 10);
        scoreComputer.addScoreConversion(new Salad(null, null), 30);
        scoreComputer.addScoreConversion(new VegetableSandwich(null, null), 30);
        scoreComputer.addScoreConversion(new CheeseEggSandwich(null, null), 50);
        scoreComputer.addScoreConversion(new FruitSalad(null, null), 50);

        PickupWindow window = new PickupWindow(computeCoordinate(5, 6), staticItemShape, scoreboard, scoreComputer);
        addSprite(window); 
        //scoreboard.setX(1050);
        addSprite(new OrderDiplayer(50, 600, window, panel));

        addSprite(new Grass1(computeCoordinate(12,3), staticItemShape));
        
    }

    public int computeXCoordinate(double Xgrid){
        return (int) (Xgrid * gridWidth);
    }

    public int computeYCoordinate(double Ygrid){
        return (int) (Ygrid * gridWidth);
    }

    public Point computeCoordinate(double Xgrid, double Ygrid){
        return new Point((int) (Xgrid * gridWidth), (int) (Ygrid * gridHeight));
    }

    @Override
    public Point defaultPlayer1Location() {
        return computeCoordinate(4, 3);
    }

    @Override
    public Point defaultPlayer2Location() {
        return computeCoordinate(6, 3);
    }
    
    @Override
    public SpriteShape getCharacterShape(){
        return new SpriteShape(new Dimension(146 / 2, 176 / 2),
                new Dimension(40 / 2, 38 / 2), new Dimension(66 / 2, 104 / 2));
    }

    @Override
    public String colorOfWorld() {
        return "#ffe3e3";
    }
}
