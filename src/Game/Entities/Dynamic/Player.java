package Game.Entities.Dynamic;

import Game.Entities.Static.*;
import Main.Handler;
import Resources.Animation;
import Resources.Images;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends BaseDynamicEntity {
    Item item;
    float money;
    int speed = 5;
    private Burger burger;
    private String direction = "right";
    private int interactionCounter = 0;
    private Animation playerAnim;
    public Player(BufferedImage sprite, int xPos, int yPos, Handler handler) {
        super(sprite, xPos, yPos,82,112, handler);
        createBurger();
        playerAnim = new Animation(120,Images.chef);
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
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)){
            for(BaseCounter counter: handler.getWorld().Counters) {
                if (counter instanceof PlateCounter && counter.isInteractable()) {
                    ringCustomer();
                }
            }
        }
    }

    private void ringCustomer() {

        for(Client client: handler.getWorld().clients){
            boolean matched = ((Burger)client.order.food).equals(handler.getCurrentBurger());
            if(matched){
                money+=client.order.value;
                handler.getWorld().clients.remove(client);
                handler.getPlayer().createBurger();
                System.out.println("Total money earned is: " + String.valueOf(money));
                return;
            }

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
}
