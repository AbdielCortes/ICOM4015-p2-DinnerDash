package Game.Entities.Static;

import Resources.Images;

import java.awt.image.BufferedImage;

public class Item {

    public BufferedImage sprite;
    private String name;
    public Item(BufferedImage sprite, String name){
        this.sprite = sprite;
        this.name = name;
    }

    public static Item cheese = new Item(Images.ingredients[2], "cheese");
    public static Item lettuce = new Item(Images.ingredients[4], "lettuce");
    public static Item tomato = new Item(Images.ingredients[3], "tomato");
    public static Item burger = new Item(Images.tint(Images.ingredients[1],0.5f,0.5f,0.5f), "patty");
    public static Item botBread = new Item(Images.ingredients[5], "bottomBun");
    public static Item topBread = new Item(Images.ingredients[0], "topBun");
    public static Item onion = new Item(Images.ingredients[7], "onion");

    public boolean equals(Item i) {
        return i.name.equals(name);
    }
}



