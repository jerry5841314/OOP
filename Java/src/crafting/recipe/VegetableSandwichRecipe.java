package crafting.recipe;

import java.awt.Point;
import item.mobileItem.ingredient.Ingredient;
import item.mobileItem.ingredient.VegetableSandwich;
import model.SpriteShape;

public class VegetableSandwichRecipe extends ConcreteRecipe {

    public VegetableSandwichRecipe(SpriteShape productShape) {
        super(productShape, "bread", "spinach");
    }

    protected Ingredient getResult() {      
        return new VegetableSandwich(new Point(0, 0), productShape);
    }
}
