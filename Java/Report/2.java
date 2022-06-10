public class  SomeIngredientRecipeOneWants extends ConcreteRecipe {
    public ApplepieRecipe(SpriteShape productShape) {
        super(productShape, "ingredientNeeded1", "ingredientNeeded2", ... );
    }
    protected Ingredient getResult() {      
        return new SomeIngredientOneWants(new Point(0, 0), productShape);
    }
}
