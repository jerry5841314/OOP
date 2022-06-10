package crafting.recipe;

import java.awt.Point;
import item.mobileItem.ingredient.FruitSalad;
import item.mobileItem.ingredient.Ingredient;
import model.SpriteShape;

public class FruitSaladRecipe extends ConcreteRecipe {

    public FruitSaladRecipe(SpriteShape productShape) {
        super(productShape, "orange", "banana", "spinach");
    }

    protected Ingredient getResult() {      
        return new FruitSalad(new Point(0, 0), productShape);
    }
}
