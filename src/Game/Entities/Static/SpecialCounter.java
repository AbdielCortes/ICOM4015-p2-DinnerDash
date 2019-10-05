package Game.Entities.Static;

import Main.Handler;
import Resources.Images;

import java.awt.*;

public class SpecialCounter extends BaseCounter {

    public SpecialCounter(int xPos, int yPos, Handler handler) {
        super(Images.kitchenCounter[3], xPos, yPos,96,102,handler);

    }

}
