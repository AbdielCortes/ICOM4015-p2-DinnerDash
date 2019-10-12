package Game.Entities.Dynamic;

import Game.Entities.Static.Burger;
import Game.Entities.Static.Item;
import Game.Entities.Static.Order;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Client extends BaseDynamicEntity {
	public double patience;
	public double OGpatience;
	public static double patienceModifier = 1;
	public static double eightPercent = 1000000;
	public int eightPercentCounter = 0;
	public int antiVIndex;
	public Color hitbox = Color.red;
	public boolean isSelected=false;
	Order order;
	public boolean isLeaving = false;
	public Client(int xPos, int yPos, Handler handler) {
		super(Images.people[new Random().nextInt(11)], xPos, yPos,64,72, handler);

		patience = (new Random().nextInt(120*60)+60*60) * patienceModifier;
		if(this.sprite.equals(Images.people[4])) {
			patience = 60*15;
		}
		OGpatience = patience;

		int numOfIngredients = new Random().nextInt(4)+1;
		order = new Order();
		order.food = new Burger(xPos +72,yPos,52,22);
		((Burger) order.food).addIngredient(Item.botBread);
		((Burger) order.food).addIngredient(Item.burger);
		order.value += 1.0;
		for(int i = 0;i<numOfIngredients;i++){
			int ingredients = new Random().nextInt(5)+1;
			order.value += 0.5;
			switch (ingredients){
			case 1:
				((Burger) order.food).addIngredient(Item.lettuce);

				break;
			case 2:
				((Burger) order.food).addIngredient(Item.tomato);

				break;

			case 3:
				((Burger) order.food).addIngredient(Item.cheese);

				break;

			case 4:
				((Burger) order.food).addIngredient(Item.onion);

				break;
			}

		}
		((Burger) order.food).addIngredient(Item.topBread);

	}

	public void tick(){
		patience--;
		if(patience<=0){
			isLeaving=true;
		}
		
//		if(isInspector()) {
//			System.out.println("I am Mr Krabs");
//		}
//		
		isAntiV();
		eightPercentCounter++;
		if(eightPercentCounter >= eightPercent) {
			//System.out.println("im in");
			eightPercentCounter = 0;
			int index = new Random().nextInt(2); //generates random number, either 0 or 1
			if(handler.getWorld().clients.size() > 1) { //if antiV is not alone in restaurant
				if(antiVIndex != 0 && index == 0) { //if client is not at the back of the line
					handler.getWorld().clients.get(antiVIndex-1).patience *= 0.96;
					//System.out.println("Lowered patience client behind");
				}
				else if(antiVIndex != 4 && index == 1) { //if antiV is not at the front of the line
					handler.getWorld().clients.get(antiVIndex+1).patience *= 0.96;
					//System.out.println("Lowered patience client in front");
				}
				else if(antiVIndex == 0) { //if antiV is at the back of the line
					handler.getWorld().clients.get(1).patience *= 0.96;
					//System.out.println("Lowered patience client behind, no rand");
				}
				else if(antiVIndex == 4) { //if antiV is at the front of the line
					handler.getWorld().clients.get(3).patience *= 0.96;
					//System.out.println("Lowered patience client in front, no rand");
				}
			}
		}
	}
	public void render(Graphics g){
		
		
		if(isSelected) {
			g.setColor(hitbox);
			g.drawRect(xPos, yPos, this.width, this.height);
		}

		if(!isLeaving){
			g.drawImage(Images.tint(sprite,1.0f,((float)patience/(float)OGpatience),((float)patience/(float)OGpatience)),xPos,yPos,width,height,null);

			((Burger) order.food).render(g);
		}
	}

	public void move(){
		yPos+=102;
		((Burger) order.food).y+=102;

	}
	
	public void moveBackwards(){
		yPos-=102;
		((Burger) order.food).y-=102;

	}
	
	//method used to check if client is an inspector
	public boolean isInspector() {
		boolean result = false;
		for(Client clients : handler.getWorld().clients) {
			if(clients.sprite.equals(Images.people[9])) {
				result = true;
			}
		}
		return result;
	}
	
	//method used to check if client is an antiV
	public void isAntiV() {
		int counter = 0; //counts how many times the for loop runs
		for(Client clients : handler.getWorld().clients) {
			if(clients.sprite.equals(Images.people[10])) { //uses sprite to check if a client is squidward sprite
				eightPercent = clients.OGpatience * 0.8; //gets how many tick is 8% of the patience 
				antiVIndex = counter; //antiV index is equal to how many times the for loop has run
			}
			counter++;
		}
	}

}
