package Game.Entities.Static;

import Main.Handler;
import Resources.Images;

import java.awt.*;

public class StoveCounter extends BaseCounter {

    int cookTime = 85;
    int burntTime = 6*60;
    int timeInStove = 0;
    boolean cooking = false;
    boolean burnt = false;
    float tint = 1;
    public StoveCounter(int xPos, int yPos, Handler handler) {
        super(Images.kitchenCounter[0], xPos, yPos,96,102,handler);
        item = Item.burger;
    }

    @Override
    public void tick() {
        if(cooking){
            timeInStove++;
            tint-=.002;
            if(tint<=0){
                tint=0;
            }
        }
        if(timeInStove>burntTime){
            burnt=true;
        }
    }

    @Override
    public void interact() {
        if(!cooking){
            cooking=true;
            item.sprite = Images.ingredients[1];
        }else{
            if(timeInStove<burntTime && timeInStove>cookTime){
                item.sprite = Images.tint(item.sprite,tint,tint,tint);
                handler.getPlayer().getBurger().addIngredient(item);
                if(tint>0.48 && tint<0.53) {
                	handler.getPlayer().bonus=true;
                } else {
                	handler.getPlayer().bonus=false;
                }
                //System.out.println(tint);
                
                cooking=false;
                burnt=false;
                timeInStove=0;
                tint = 1;
            }
            else if(timeInStove>burntTime){
                cooking=false;
                burnt=false;
                timeInStove=0;
                tint = 1;
            }
        }

    }

    public void render(Graphics g){
        g.drawImage(sprite,xPos,yPos,width,height,null);
        if(cooking){
            g.drawImage(Images.tint(item.sprite,tint,tint,tint),xPos + width/2 - 25,yPos +height/2+25,50,30,null);
        }
        if(isInteractable() && item != null){
            g.drawImage(item.sprite,xPos + width/2 - 25,yPos -30,50,30,null);
        }
    }
}
