package Game.Entities.Static;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Burger extends Food{

    public int x, y, width,height;
    public ArrayList<Item> ingredients = new ArrayList<>();
    ArrayList<BufferedImage> sprite = new ArrayList<>();


    public Burger(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }


    public void addIngredient(Item ingredient){
        this.ingredients.add(ingredient);
        this.sprite.add(ingredient.sprite);
        //System.out.println("added ingredient object");
    }
    public void finishBurger(){
        this.sprite.add(null);
    }

    public void render(Graphics g){
        int counter = 1;
        for (BufferedImage ingredient:this.sprite){
            g.drawImage(ingredient,x,y-(counter * 10),width,height,null);
            counter++;
        }
    }

    public boolean equals(Burger b){
        if (b.ingredients.size() != ingredients.size()){
            return false;
        }
        for(int i =0 ;i<ingredients.size();i++){
            if(!ingredients.get(i).equals(b.ingredients.get(i))){
                return false;
            }
        }
        return true;
    }
}
