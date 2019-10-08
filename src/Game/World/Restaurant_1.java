package Game.World;

import Game.Entities.Dynamic.Client;
import Game.Entities.Dynamic.Player;
import Game.Entities.Static.*;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;

public class Restaurant_1 extends BaseWorld {
    private int count=0;
    private int capacity = 5;
    public static int leftClients = 0;
    public Restaurant_1(BaseCounter[] Counters, Handler handler) {
        super(Images.floor,Counters, handler, new Player(null,0,650,handler));
    }

	public void tick(){
		count++;
		if(count >= 5*60 && !isFull()){
			count = 0;
			for(Client client: this.clients){
				client.move();
			}
			this.generateClient();
		}else if(count >= 5*60 && isFull()){
			count=0;
			boolean left=false;
			Client toLeave = null;
			ArrayList<Client> toMove = new ArrayList<>();
			for (Client client : this.clients) {
				if (client.isLeaving && !left) {
					toLeave = client;
					left=true;
					
					//if client that is leaving is an inspector
					if(client.sprite.equals(Images.people[9])) {
						Client.patienceModifier = 0.94; //future clients have 6% less patience
						Player.money /= 2; //player loses half of their money
					}
				}
				else if (left) {
					toMove.add(client);
				}
			}
			if(left){
    			handler.getPlayer().clientsLeft++;
    			leftClients++;
				this.clients.remove(toLeave);
				for (Client client : toMove) {
					client.move();
				}
				this.generateClient();
			}
		}

		for(Client client: this.clients){
			client.tick();
		}
		for(BaseCounter counter: Counters){
			counter.tick();
		}
		handler.getPlayer().tick();
	}

	public boolean isFull(){
		return this.clients.size() >=capacity;
	}
	public void render(Graphics g){
		g.drawImage(Background,0,0,handler.getWidth(), handler.getHeight(),null);
		g.drawImage(Images.welcome,5,90,43,82,null);
		g.drawImage(Images.kitchenChairTable[0],handler.getWidth()/3,90,110,90,null);
		g.drawImage(Images.kitchenChairTable[1],handler.getWidth()/3+96,120,52,52,null);
		g.drawImage(Images.kitchenChairTable[1],handler.getWidth()/3-52,120,52,52,null);


		g.drawImage(Images.kitchenChairTable[0],handler.getWidth()/3+handler.getWidth()/6,190,110,90,null);
		g.drawImage(Images.kitchenChairTable[2],handler.getWidth()/3+handler.getWidth()/6+96,220,52,52,null);
		g.drawImage(Images.kitchenChairTable[2],handler.getWidth()/3+handler.getWidth()/6-52,220,52,52,null);

		g.drawImage(Images.kitchenChairTable[0],handler.getWidth()/3+handler.getWidth()/3,90,110,90,null);
		g.drawImage(Images.kitchenChairTable[1],handler.getWidth()/3+handler.getWidth()/3+96,120,52,52,null);
		g.drawImage(Images.kitchenChairTable[2],handler.getWidth()/3+handler.getWidth()/3-52,120,52,52,null);

		g.drawImage(Images.kitchenChairTable[0],handler.getWidth()/3+handler.getWidth()/3,292,110,90,null);
		g.drawImage(Images.kitchenChairTable[2],handler.getWidth()/3+handler.getWidth()/3+96,312,52,52,null);
		g.drawImage(Images.kitchenChairTable[1],handler.getWidth()/3+handler.getWidth()/3-52,312,52,52,null);

		g.drawImage(Images.kitchenChairTable[0],handler.getWidth()/3-3,292,110,90,null);
		g.drawImage(Images.kitchenChairTable[2],handler.getWidth()/3+96,312,52,52,null);
		g.drawImage(Images.kitchenChairTable[2],handler.getWidth()/3-52,312,52,52,null);

		for(Client client: clients){
			client.render(g);
		}
        for(BaseCounter counter: Counters){
            counter.render(g);
        }
        handler.getPlayer().render(g);
    }
    
    public static int getLeftClients() {
    	return leftClients;
    }
    public static void setLeftClients(int lClients) {
    	leftClients = lClients;
    }
}
