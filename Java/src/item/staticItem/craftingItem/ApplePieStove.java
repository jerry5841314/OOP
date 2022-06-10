package item.staticItem.craftingItem;

import java.awt.Point;

import crafting.recipe.*;
import model.SpriteShape;

public class ApplePieStove extends Crafter {

    public ApplePieStove(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "applepiestove");

        cookTime = 15 * 1000;

        recipes.add(new ApplepieRecipe(productShape));
    }
}
