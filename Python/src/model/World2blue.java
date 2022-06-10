package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
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

public class World2blue extends World {

    private final int gridWidth = 75;

    private final int gridHeight = 75;

    private final SpriteShape staticItemShape = new SpriteShape(new Dimension(75, 75), new Dimension(10, 10),
            new Dimension(55, 55));

    private final SpriteShape mobileItemShape = new SpriteShape(new Dimension(30, 30), new Dimension(0, 0),
            new Dimension(30, 30));

    public World2blue(CollisionHandler collisionHandler, int width, int height, List<Sprite> sprites, JPanel panel) {
        super(collisionHandler, width, height, sprites, panel);

        // setting for outside game panel stuffs
        // including timer, scoreboard, recipe, orderlist
        // if you follow the size setting of world example 3
        // then only modifiy the path of the .png files
        var recipePicture = new FixedImageDisplayer("assets/worldexample2/recipe.png", 20 + 900,
                720 - 180 * 1600 / 701 + 20, 180, 180 * 1600 / 701, panel);
        var timerBackground = new FixedImageDisplayer("assets/worldexample2/timer.png", 20 + 900, 0, 180, 138, panel);
        var scoreboardBackground = new FixedImageDisplayer("assets/worldexample2/scoreboard.png", 20 + 900, 140, 180,
                180, panel);
        var orderListBackground = new FixedImageDisplayer("assets/worldexample2/orderlistbg.png", 0, 600, 900, 120,
                panel);

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

        for (int i = 2; i <= 9; ++i)
            addSprite(new WoodPlatform(computeCoordinate(i, 0), staticItemShape));

        addSprite(new EggBasket(computeCoordinate(1, 1), staticItemShape, mobileItemShape));
        addSprite(new WoodPlatform(computeCoordinate(5, 1), staticItemShape));
        addSprite(new SpinachGarden(computeCoordinate(10, 1), staticItemShape, mobileItemShape));

        addSprite(new TrashCan(computeCoordinate(0, 2), staticItemShape));
        addSprite(new WoodPlatform(computeCoordinate(0, 3), staticItemShape));
        addSprite(new BreadBasket(computeCoordinate(0, 4), staticItemShape, mobileItemShape));
        addSprite(new WoodPlatform(computeCoordinate(0, 5), staticItemShape));
        addSprite(new PieBox(computeCoordinate(0, 6), staticItemShape, mobileItemShape));

        addSprite(new ApplePieStove(computeCoordinate(5, 2), staticItemShape, mobileItemShape));
        addSprite(new SaladBowl(computeCoordinate(5, 3), staticItemShape, mobileItemShape));
        addSprite(new SandwichMaker(computeCoordinate(5, 4), staticItemShape, mobileItemShape));
        addSprite(new FriedEggStove(computeCoordinate(5, 5), staticItemShape, mobileItemShape));
        addSprite(new WoodPlatform(computeCoordinate(5, 6), staticItemShape));

        addSprite(new CheeseBlock(computeCoordinate(11, 2), staticItemShape, mobileItemShape));
        addSprite(new Plant1(computeCoordinate(11, 3), staticItemShape));
        addSprite(new Plant1(computeCoordinate(11, 5), staticItemShape));
        addSprite(new TrashCan(computeCoordinate(11, 6), staticItemShape));

        for (int i = 1; i <= 3; ++i)
            addSprite(new WoodPlatform(computeCoordinate(i, 7), staticItemShape));

        addSprite(new FruitBasket(computeCoordinate(4, 7), staticItemShape, mobileItemShape));
        addSprite(new TomatoBasket(computeCoordinate(6, 7), staticItemShape, mobileItemShape));

        for (int i = 7; i <= 10; ++i)
            addSprite(new WoodPlatform(computeCoordinate(i, 7), staticItemShape));

        ScoreComputer scoreComputer = new ScoreComputer(new ArrayList<>());
        scoreComputer.addScoreConversion(new ApplePie(null, null), 30);
        scoreComputer.addScoreConversion(new FriedEgg(null, null), 10);
        scoreComputer.addScoreConversion(new Salad(null, null), 30);
        scoreComputer.addScoreConversion(new VegetableSandwich(null, null), 30);
        scoreComputer.addScoreConversion(new CheeseEggSandwich(null, null), 50);
        scoreComputer.addScoreConversion(new FruitSalad(null, null), 50);

        PickupWindow window = new PickupWindow(computeCoordinate(11, 4), staticItemShape, scoreboard, scoreComputer);
        addSprite(window);
        // scoreboard.setX(1050);
        addSprite(new OrderDiplayer(50, 600, window, panel));

        //addSprite(new Grass1(computeCoordinate(12, 3), staticItemShape));
    }

    public int computeXCoordinate(double Xgrid) {
        return (int) (Xgrid * gridWidth);
    }

    public int computeYCoordinate(double Ygrid) {
        return (int) (Ygrid * gridWidth);
    }

    public Point computeCoordinate(double Xgrid, double Ygrid) {
        return new Point((int) (Xgrid * gridWidth), (int) (Ygrid * gridHeight));
    }

    @Override
    public Point defaultPlayer1Location() {
        return computeCoordinate(2, 2);
    }

    @Override
    public Point defaultPlayer2Location() {
        return computeCoordinate(9, 5);
    }

    @Override
    public SpriteShape getCharacterShape() {
        return new SpriteShape(new Dimension(146 / 2, 176 / 2), new Dimension(40 / 2, 38 / 2),
                new Dimension(66 / 2, 104 / 2));
    }

    @Override
    public String colorOfWorld() {
        return "#deebf7";
    }
}
