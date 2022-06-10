package crafting.recipe;

import java.awt.Point;
import item.mobileItem.ingredient.ApplePie;
import item.mobileItem.ingredient.Ingredient;
import model.SpriteShape;

public class ApplepieRecipe extends ConcreteRecipe {

    public ApplepieRecipe(SpriteShape productShape) {
        super(productShape, "apple", "pie");
    }

    protected Ingredient getResult() {      
        return new ApplePie(new Point(0, 0), productShape);
    }
}
