package Game.Entities.Dynamic;

import Game.Entities.Static.*;
import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends BaseDynamicEntity {
	public static int servedCustomers = 0;
	public int customerSelected = 0;
	public boolean bonus = false;
	public float cookBonus = 0;
	public int clientsLeft = 0;
	Item item;
	public static float money;
	int speed = 10;
	private Burger burger;
	private String direction = "right";
	private int interactionCounter = 0;
	private Animation playerAnim;
	
	public Player(BufferedImage sprite, int xPos, int yPos, Handler handler) {
		super(sprite, xPos, yPos,82,112, handler);
		createBurger();
		playerAnim = new Animation(120,Images.spongebob);
	}

	public void createBurger(){
		burger = new Burger(handler.getWidth() - 110, 100, 100, 50);

	}

	public void tick(){
		
		playerAnim.tick();
		if(xPos + width >= handler.getWidth()){
			direction = "left";

		} else if(xPos <= 0){
			direction = "right";
		}
		if (direction.equals("right")){
			xPos+=speed;
		} else{
			xPos-=speed;
		}
		if (interactionCounter > 15 && handler.getKeyManager().attbut){
			interact();
			interactionCounter = 0;
			//System.out.println("interacted with counter");
		} else {
			interactionCounter++;
		}
		
		if(handler.getKeyManager().fattbut){
			for(BaseCounter counter: handler.getWorld().Counters){
				if (counter instanceof PlateCounter && counter.isInteractable()){
					createBurger();
				}
			}
		}

		if(this.clientsLeft>=10 || handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)) {
			State.setState(handler.getGame().loseState);
		}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
			State.setState(handler.getGame().planktonState);
		}

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			State.setState(handler.getGame().pauseState);
		}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SHIFT)){
			switch(this.speed) {
			case 10:
				this.speed=5;
				break;
			case 5:
				this.speed=10;
				break;
			}
		}

		if(money >= 50 || handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
			State.setState(handler.getGame().winState);
		}


		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)){
			for(BaseCounter counter: handler.getWorld().Counters) {
				if (counter instanceof PlateCounter && counter.isInteractable()) {
					ringCustomer(customerSelected);
				}
			}
		}
		
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) {
//			ringCustomer(customerSelected);
//		}

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) {
			customerSelected=0;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)) {
			customerSelected=1;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)) {
			customerSelected=2;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)) {			
			customerSelected=3;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)) {
			customerSelected=4;

			if(handler.getWorld().clients.size()>=5) {

				for(BaseCounter counter: handler.getWorld().Counters) {
					if (counter instanceof PlateCounter && counter.isInteractable()) {
						ringCustomer(5);
					}
				}
			}
		}   
			
	}        


	private void ringCustomer(int x) {

		Client client = handler.getWorld().clients.get(customerSelected);
		boolean matched = ((Burger)client.order.food).equals(handler.getCurrentBurger());

		if(matched){
			//if client is Plankton you the secret formula gets stolen
			if(handler.getWorld().clients.get(customerSelected).sprite.equals(Images.people[4])) {
				State.setState(handler.getGame().planktonState);
			}
			servedCustomers++;

			for(Client clients : handler.getWorld().clients) {
				clients.patience+=clients.OGpatience*0.25;
			}
			
			cookBonus=0;
			if(bonus) cookBonus = (float) (client.order.value * 0.12);
			bonus=false;
			
			//System.out.println(cookBonus);
			money+=client.order.value + cookBonus;
			//System.out.println(money);
			if(client.patience > (client.OGpatience/2)) {
				money *= 1.15;
			}
			
			//if served client is an inspector
			if(client.sprite.equals(Images.people[9])) {
				//increases everyones patience by 12%
				for(Client clients : handler.getWorld().clients) {
					clients.patience *= 1.12;
				}
				Client.patienceModifier = 1.10; //future clients have 10% more patience
			}
			handler.getWorld().clients.remove(client);
			handler.getPlayer().createBurger();
			//System.out.println("Total money earned is: " + String.valueOf(money));
			
			//resolves error where customers go behind the counter
			if(x != 0) {
				int i = x-1;
				while(i >= 0) {
					handler.getWorld().clients.get(i).moveBackwards();
					i--;
				}
			}
			
			return;
		} 

	}

	public void render(Graphics g) {
		if(direction=="right") {
			g.drawImage(playerAnim.getCurrentFrame(), xPos, yPos, width, height, null);
		}else{
			g.drawImage(playerAnim.getCurrentFrame(), xPos+width, yPos, -width, height, null);

		}
		burger.render(g);
		g.setColor(Color.white);
		g.setFont(new Font("ComicSans", Font.BOLD, 32));
		String moneyFormated = String.format("%.2f", money);
		g.drawString("Money Earned: " + moneyFormated, handler.getWidth()/2 -160, 30);
		g.setFont(new Font("ComicSans", Font.BOLD, 25));
		g.drawString("Customer Selected: " + (customerSelected+1), handler.getWidth()/2 -140, 50);
	}

	public void interact(){
		for(BaseCounter counter: handler.getWorld().Counters){
			if (counter.isInteractable()){
				counter.interact();
			}
		}
	}
		
	public Burger getBurger(){
		return this.burger;
	}
	
	public static int getServedCustomers() {
		return servedCustomers;
	}
	
	public static void setServedCustomers(int srvCustomers) {
		servedCustomers = srvCustomers;
	}
	
}
