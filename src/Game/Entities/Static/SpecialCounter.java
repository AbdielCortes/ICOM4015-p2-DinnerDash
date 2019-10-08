package Game.Entities.Static;

import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.sun.glass.events.KeyEvent;

import Game.Entities.Dynamic.Client;

public class SpecialCounter extends BaseCounter {
	//public static BufferedImage emptyCounter = Images.kitchenCounter[3];
	public int tickCounter = 0; //counts how many ticks have gone by since game has started
	public int specialTicks = new Random().nextInt(3000); //how many ticks it takes for the counter to change color
	public int colorTicks = 120; //120 ticks == 2 seconds; how long counter is in changed color state
	public boolean interactable = false; //tells me if counter has changed its color
    public SpecialCounter(int xPos, int yPos, Handler handler) {
        super(Images.kitchenCounter[3], xPos, yPos,96,102,handler);

    }

    @Override
    public void tick(){
    	tickCounter++;
    	//System.out.println(tickCounter + "            "	+ colorTicks);
    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_T)) { 
    		//prints out how many ticks have gone by and how many are needed to activate the counter
    		System.out.println(tickCounter + "            " + specialTicks);
    	}
    	
    	//activates counter if it reaches the set amount of ticks
    	if((tickCounter == specialTicks && colorTicks >= 120) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
    		interactable = true; //makes is so that you can interact with it
    		colorTicks = 0; //resets to 0 counter that counts if it has passed two seconds
    	}
    	colorTicks++;
    	if(colorTicks == 120) { //if two seconds have passed
    		interactable = false; //no longer can interact
    		tickCounter = 0; 
    		specialTicks = new Random().nextInt(3000); //sets new special ticks to a random amount
    	}
    }
    
    @Override
    public void interact(){
        if(interactable) { //if player interacts with counter while its changed its color
        	for(Client clients : handler.getWorld().clients) {
				clients.patience = clients.OGpatience; //set all clients back to their original patience
				//System.out.println("Set Patience to original Patience");
			}
        }
    }
    
    @Override
    public void render(Graphics g) {
    	if(interactable) { 
    		//draws changed color counter
    		g.drawImage(Images.kitchenCounter[9],xPos,yPos,width,height,null);
    		g.setColor(Color.RED);
    		g.setFont(new Font("ComicSans", Font.ITALIC, 22));
    		g.drawString("MAX PATIENCE",xPos + width/2 - 80,yPos -20);
    	}
        else {
        	//draws normal counter
        	g.drawImage(Images.kitchenCounter[3],xPos,yPos,width,height,null);
        }
    }

}
