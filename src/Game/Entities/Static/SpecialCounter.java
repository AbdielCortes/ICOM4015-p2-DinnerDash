package Game.Entities.Static;

import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.sun.glass.events.KeyEvent;

import Game.Entities.Dynamic.Client;

public class SpecialCounter extends BaseCounter {
	public static BufferedImage emptyCounter = Images.kitchenCounter[3];
	public int tickCounter = 0;
	public int specialTicks = new Random().nextInt(3000);
	public int colorTicks = 120;
	public boolean interactable = false;
    public SpecialCounter(int xPos, int yPos, Handler handler) {
        super(emptyCounter, xPos, yPos,96,102,handler);

    }

    @Override
    public void tick(){
    	tickCounter++;
    	//System.out.println(tickCounter + "            "	+ colorTicks);
    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_T)) {
    		System.out.println(specialTicks);
    	}
    	
    	if((tickCounter == specialTicks && colorTicks >= 120) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
    		interactable = true;
    		colorTicks = 0;
    	}
    	colorTicks++;
    	if(colorTicks == 120) {
    		interactable = false;
    		tickCounter = 0;
    		specialTicks = new Random().nextInt(3000);
    	}
    }
    
    @Override
    public void interact(){
        if(interactable) {
        	for(Client clients : handler.getWorld().clients) {
				clients.patience = clients.OGpatience;
				System.out.println("did the thing");
			}
        }
    }
    
    @Override
    public void render(Graphics g) {
    	if(interactable) {
    		g.drawImage(Images.kitchenCounter[9],xPos,yPos,width,height,null);
    		g.setColor(Color.RED);
    		g.setFont(new Font("ComicSans", Font.ITALIC, 22));
    		g.drawString("MAX PATIENCE",xPos + width/2 - 80,yPos -20);
    	}
        else {
        	g.drawImage(emptyCounter,xPos,yPos,width,height,null);
        }
    }

}
