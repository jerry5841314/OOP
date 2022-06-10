package crafting.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import crafting.Recipe;
import item.mobileItem.MobileItem;
import item.mobileItem.ingredient.Ingredient;
import model.SpriteShape;

abstract public class ConcreteRecipe implements Recipe {

    public List<String> ingredientNeeded;

    protected SpriteShape productShape;

    public ConcreteRecipe(SpriteShape productShape, String... ingredients) {
        this.productShape = productShape;
        ingredientNeeded = Arrays.asList(ingredients);
    }

    @Override
    public boolean craftAble(ArrayList<MobileItem> itm) {
        for (String s : ingredientNeeded) {
            Optional<MobileItem> ingredient = itm.stream()
                    .filter(Ingredient.class::isInstance)
                    .filter(i -> s == ((Ingredient) i).getIngredientName()).findAny();
            if (ingredient.isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public MobileItem craft(ArrayList<MobileItem> itm) {
        if (!this.craftAble(itm))
            throw new IllegalArgumentException();
        for (String s : ingredientNeeded) {
            Optional<MobileItem> optionalIngredient = itm.stream().filter(Ingredient.class::isInstance)
                    .filter(i -> s == ((Ingredient) i).getIngredientName()).findAny();
            MobileItem ingredient = optionalIngredient.orElseThrow(IllegalArgumentException::new);
            ingredient.getWorld().removeSprite(ingredient);
            itm.remove(ingredient);
        }
        Ingredient newIngredient = getResult();
        System.out.printf("Craft one %s.%n", newIngredient.getIngredientName());
        return newIngredient;
    }

    abstract protected Ingredient getResult();
}
