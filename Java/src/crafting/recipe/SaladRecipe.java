package crafting.recipe;

import java.awt.Point;
import item.mobileItem.ingredient.Ingredient;
import item.mobileItem.ingredient.Salad;
import model.SpriteShape;

public class SaladRecipe extends ConcreteRecipe {

    public SaladRecipe(SpriteShape productShape) {
        super(productShape, "tomato", "spinach");
    }

    protected Ingredient getResult() {      
        return new Salad(new Point(0, 0), productShape);
    }
}
