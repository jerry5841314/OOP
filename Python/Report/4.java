public class StaticItemOneWants extends StaticItem {

    public StaticItemOneWants(Point location, SpriteShape shape) {
        super(location, shape);
        ImageRenderer imageRenderer = new ItemImageRenderer(this);
        idle = new WaitingPerFrame(4, new Idle(imageStatesFromFolder("assets/item/staticItemOneWants", imageRenderer)));
    }
}