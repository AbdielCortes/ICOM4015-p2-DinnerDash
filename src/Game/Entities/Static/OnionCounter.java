package Game.Entities.Static;

import Main.Handler;
import Resources.Images;

public class OnionCounter extends BaseCounter {
    public OnionCounter(int xPos, int yPos, Handler handler) {
        super(Images.kitchenCounter[8], xPos, yPos,96,104,handler);
        item = Item.onion;
    }
}
