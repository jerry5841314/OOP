public class IngredientFactoryOneWants extends Factory {

    public IngredientFactoryOneWants(Point location, SpriteShape shape, SpriteShape productShape) {
        super(location, shape, productShape, "eggbasket");
    }

    @Override
    public MobileItem produceItem() {
        RawEgg newItem = new IngredientOneWants(new Point(0, 0), productShape);
        this.world.addSprite(newItem);
        return newItem;
    }
}