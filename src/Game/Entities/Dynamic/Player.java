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
	//<<<<<<< HEAD
	//    Item item;
	//    float money;
	public static int servedCustomers = 0;
	//    int speed = 5;
	//    private Burger burger;
	//    private String direction = "right";
	//    private int interactionCounter = 0;
	//    private Animation playerAnim;
	//    public Player(BufferedImage sprite, int xPos, int yPos, Handler handler) {
	//        super(sprite, xPos, yPos,82,112, handler);
	//        createBurger();
	//        playerAnim = new Animation(120,Images.chef);
	//    }
	//=======
	public boolean bonus = false;
	public float cookBonus = 0;
	public int clientsLeft = 0;
	Item item;
	float money;
	int speed = 4;
	private Burger burger;
	private String direction = "right";
	private int interactionCounter = 0;
	private Animation playerAnim;
	public Player(BufferedImage sprite, int xPos, int yPos, Handler handler) {
		super(sprite, xPos, yPos,82,112, handler);
		createBurger();
		playerAnim = new Animation(120,Images.chef);
	}
	//>>>>>>> branch 'master' of https://github.com/uprm-ciic4010-f19/pa-2-dinner-dash-exceptionalfood.git

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

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			State.setState(handler.getGame().pauseState);
		}

		if(money >= 50 || handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
			State.setState(handler.getGame().winState);
		}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) {

			if(handler.getWorld().clients.size()>=1) {

				for(BaseCounter counter: handler.getWorld().Counters) {
					if (counter instanceof PlateCounter && counter.isInteractable()) {
						ringCustomer(1);
					}
				}
			}
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)) {

			if(handler.getWorld().clients.size()>=2) {

				for(BaseCounter counter: handler.getWorld().Counters) {
					if (counter instanceof PlateCounter && counter.isInteractable()) {
						ringCustomer(2);
					}
				}
			}
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)) {

			if(handler.getWorld().clients.size()>=3) {

				for(BaseCounter counter: handler.getWorld().Counters) {
					if (counter instanceof PlateCounter && counter.isInteractable()) {
						ringCustomer(3);
					}
				}
			}
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)) {

			if(handler.getWorld().clients.size()>=4) {

				for(BaseCounter counter: handler.getWorld().Counters) {
					if (counter instanceof PlateCounter && counter.isInteractable()) {
						ringCustomer(4);
					}
				}
			}
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)) {

			if(handler.getWorld().clients.size()>=5) {

				for(BaseCounter counter: handler.getWorld().Counters) {
					if (counter instanceof PlateCounter && counter.isInteractable()) {
						ringCustomer(5);
					}
				}
			}
		}        
	}
	/*
	//
	//<<<<<< HEAD
	//        } else if(xPos <= 0){
	//            direction = "right";
	//        }
	//        if (direction.equals("right")){
	//            xPos+=speed;
	//        } else{
	//            xPos-=speed;
	//        }
	//        if (interactionCounter > 15 && handler.getKeyManager().attbut){
	//            interact();
	//            interactionCounter = 0;
	//        } else {
	//            interactionCounter++;
	//        }
	//        if(handler.getKeyManager().fattbut){
	//            for(BaseCounter counter: handler.getWorld().Counters){
	//                if (counter instanceof PlateCounter && counter.isInteractable()){
	//                    createBurger();
	//                }
	//            }
	//        }
	//        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)){
	//            for(BaseCounter counter: handler.getWorld().Counters) {
	//                if (counter instanceof PlateCounter && counter.isInteractable()) {
	//                    ringCustomer();
	//                }
	//            }
	//        }
	//        
	//        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
	//        	State.setState(handler.getGame().pauseState);
	//        }
	//        
	//        if(money >= 50 || handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
	//        	State.setState(handler.getGame().winState);
	//        }
	//    }

	//=======
	//>>>>>>> branch 'master' of https://github.com/uprm-ciic4010-f19/pa-2-dinner-dash-exceptionalfood.git
	//
	//
	//<<<<<<< HEAD
	//        for(Client client: handler.getWorld().clients){
	//            boolean matched = ((Burger)client.order.food).equals(handler.getCurrentBurger());
	//            if(matched){
	//                money+=client.order.value;
	//                //System.out.println(money);
	//                if(Client.getPatience() > (Client.getOGpatience()/2)) {
	//                	money *= 1.15;
	//                }
	//                handler.getWorld().clients.remove(client);
	//                handler.getPlayer().createBurger();
	//                //System.out.println("Total money earned is: " + String.valueOf(money));
	//                servedCustomers++;
	//                return;
	//            }
	//=======
	//		if(this.clientsLeft>=10) {
	//			State.setState(handler.getGame().loseState);
	//		}
	//>>>>>>> branch 'master' of https://github.com/uprm-ciic4010-f19/pa-2-dinner-dash-exceptionalfood.git
	//
	//		playerAnim.tick();
	//		if(xPos + width >= handler.getWidth()){
	//			direction = "left";
	//
	//		} else if(xPos <= 0){
	//			direction = "right";
	//		}
	//		if (direction.equals("right")){
	//			xPos+=speed;
	//		} else{
	//			xPos-=speed;
	//		}
	//		if (interactionCounter > 15 && handler.getKeyManager().attbut){
	//			interact();
	//			interactionCounter = 0;
	//		} else {
	//			interactionCounter++;
	//		}
	//		if(handler.getKeyManager().fattbut){
	//			for(BaseCounter counter: handler.getWorld().Counters){
	//				if (counter instanceof PlateCounter && counter.isInteractable()){
	//					createBurger();
	//				}
	//			}
	//		}
	//
	//<<<<<<< HEAD
	//        }
	//        g.setColor(Color.green);
	//        burger.render(g);
	//        g.setColor(Color.green);
	//        g.fillRect(handler.getWidth()/2 -210, 3, 320, 32);;
	//        g.setColor(Color.yellow);
	//        g.setFont(new Font("ComicSans", Font.BOLD, 32));
	//        String moneyFormated = String.format("%.2f", money);
	//        g.drawString("Money Earned: " + moneyFormated, handler.getWidth()/2 -200, 30);
	//    }
	//=======
	//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
	//			State.setState(handler.getGame().pauseState);
	//		}
	//>>>>>>> branch 'master' of https://github.com/uprm-ciic4010-f19/pa-2-dinner-dash-exceptionalfood.git
	//
	//<<<<<<< HEAD
	//    public void interact(){
	//        for(BaseCounter counter: handler.getWorld().Counters){
	//            if (counter.isInteractable()){
	//                counter.interact();
	//            }
	//        }
	//    }
	//    public Burger getBurger(){
	//        return this.burger;
	//    }
	//    
	//    public static int getServedCustomers() {
	//    	return servedCustomers;
	//    }
	//    public static void setServedCustomers(int srvCustomers) {
	//    	servedCustomers = srvCustomers;
	//    }
	//=======
	//
	//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) {
	//
	//			if(handler.getWorld().clients.size()>=1) {
	//
	//				for(BaseCounter counter: handler.getWorld().Counters) {
	//					if (counter instanceof PlateCounter && counter.isInteractable()) {
	//						ringCustomer(1);
	//					}
	//				}
	//			}
	//		}
	//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)) {
	//
	//			if(handler.getWorld().clients.size()>=2) {
	//
	//				for(BaseCounter counter: handler.getWorld().Counters) {
	//					if (counter instanceof PlateCounter && counter.isInteractable()) {
	//						ringCustomer(2);
	//					}
	//				}
	//			}
	//		}
	//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)) {
	//
	//			if(handler.getWorld().clients.size()>=3) {
	//
	//				for(BaseCounter counter: handler.getWorld().Counters) {
	//					if (counter instanceof PlateCounter && counter.isInteractable()) {
	//						ringCustomer(3);
	//					}
	//				}
	//			}
	//		}
	//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)) {
	//
	//			if(handler.getWorld().clients.size()>=4) {
	//
	//				for(BaseCounter counter: handler.getWorld().Counters) {
	//					if (counter instanceof PlateCounter && counter.isInteractable()) {
	//						ringCustomer(4);
	//					}
	//				}
	//			}
	//		}
	//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)) {
	//
	//			if(handler.getWorld().clients.size()>=5) {
	//
	//				for(BaseCounter counter: handler.getWorld().Counters) {
	//					if (counter instanceof PlateCounter && counter.isInteractable()) {
	//						ringCustomer(5);
	//					}
	//				}
	//			}
	//		}        
	//	}
	//*/
	private void ringCustomer(int x) {

		Client client = handler.getWorld().clients.get(x-1);
		boolean matched = ((Burger)client.order.food).equals(handler.getCurrentBurger());

		if(matched){
			servedCustomers++;
			
			for(Client clients : handler.getWorld().clients) {
				clients.patience+=clients.OGpatience*0.25;
			}

			if(bonus) cookBonus = (float) (client.order.value * 0.12);
			System.out.println(cookBonus);
			money+=client.order.value + cookBonus;
			System.out.println(money);
			if(Client.getPatience() > (Client.getOGpatience()/2)) {
				money *= 1.15;
			}
			handler.getWorld().clients.remove(client);
			handler.getPlayer().createBurger();
			System.out.println("Total money earned is: " + String.valueOf(money));
			return;

		} 

	}

	public void render(Graphics g) {
		if(direction=="right") {
			g.drawImage(playerAnim.getCurrentFrame(), xPos, yPos, width, height, null);
		}else{
			g.drawImage(playerAnim.getCurrentFrame(), xPos+width, yPos, -width, height, null);

		}
		g.setColor(Color.green);
		burger.render(g);
		g.setColor(Color.green);
		g.fillRect(handler.getWidth()/2 -210, 3, 320, 32);;
		g.setColor(Color.yellow);
		g.setFont(new Font("ComicSans", Font.BOLD, 32));
		g.drawString("Money Earned: " + money, handler.getWidth()/2 -200, 30);
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
	//>>>>>>> branch 'master' of https://github.com/uprm-ciic4010-f19/pa-2-dinner-dash-exceptionalfood.git
}
