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
	public Color hitbox = Color.red;
	public boolean isSelected=false;
	public int patience;
	public int OGpatience;
	Order order;
	public boolean isLeaving = false;
	public Client(int xPos, int yPos, Handler handler) {
		super(Images.people[new Random().nextInt(11)], xPos, yPos,64,72, handler);

		patience = new Random().nextInt(120*60)+60*60;
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

	public int getPatience() {
		return patience;
	}

	public void setPatience(int patience) {
		this.patience = patience;
	}

	public int getOGpatience() {
		return OGpatience;
	}

	public void setOGpatience(int OGpatience) {
		this.OGpatience = OGpatience;
	}
}
