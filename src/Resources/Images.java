package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage title;
    public static BufferedImage lose;
    public static BufferedImage floor;
    public static BufferedImage welcome;
    public static BufferedImage Pause;
    public static BufferedImage win;
    public static BufferedImage plankton;
    public static BufferedImage instructions;
    public static BufferedImage[] Resume;
    public static BufferedImage[] people;
//    public static BufferedImage[] chef;
    public static BufferedImage[] spongebob;
    public static BufferedImage[] kitchenChairTable;
    public static BufferedImage[] kitchenCounter;
    public static BufferedImage[] ingredients;
    public static BufferedImage[] BTitle;
    public static BufferedImage[] Restart;
    public static ImageIcon icon;
    public static SpriteSheet kitchenSpriteSheet;
    public static SpriteSheet kitchenCounterSpriteSheet;
    public static SpriteSheet burgerSpriteSheet;
//    public static SpriteSheet chefSpriteSheet;
    public static SpriteSheet spongebobSpriteSheet;
    
    
    public Images() {

        butstart = new BufferedImage[3];
        Resume = new BufferedImage[3];
        BTitle = new BufferedImage[3];
        Restart = new BufferedImage[3];
        kitchenChairTable = new BufferedImage[3];
        people = new BufferedImage[11];
        kitchenCounter = new BufferedImage[10];
        ingredients = new BufferedImage[8];
//        chef = new BufferedImage[4];
        spongebob = new BufferedImage[3];
        try {

            kitchenSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/sprite.png")));
            kitchenCounterSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/kitchen_cabinets_by_ayene_chan.png")));
            burgerSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/burger.png")));
//            chefSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/chef.png")));
            spongebobSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/spongebobSprite.png")));


            title = ImageIO.read(getClass().getResourceAsStream("/Sheets/title.png"));
            lose = ImageIO.read(getClass().getResourceAsStream("/Sheets/lose.png"));
            Pause = ImageIO.read(getClass().getResourceAsStream("/Sheets/PauseScreen.png"));
            welcome = ImageIO.read(getClass().getResourceAsStream("/Sheets/Welcome.png"));
            floor = ImageIO.read(getClass().getResourceAsStream("/Sheets/GreenFloor.png"));
            win = ImageIO.read(getClass().getResourceAsStream("/Sheets/WinScreen.png"));
            plankton = ImageIO.read(getClass().getResourceAsStream("/Sheets/PlanktonGameOver.png"));
            instructions = ImageIO.read(getClass().getResourceAsStream("/Sheets/instructions.png"));
            
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormalButtonStart.png"));//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverButtonStart.png"));//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedButtonStart.png"));//clickbut
            
            Resume[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormalButtonResume.png"));//normbut
            Resume[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverButtonResume.png"));//hoverbut
            Resume[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedButtonResume.png"));//clickbut
            
            BTitle[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormalButtonTitle.png"));//normbut
            BTitle[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverButtonTitle.png"));//hoverbut
            BTitle[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedButtonTitle.png"));//clickbut

            Restart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormalButtonRestart.png"));//normbut
            Restart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverButtonRestart.png"));//hoverbut
            Restart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedButtonRestart.png"));//clickbut
            
            kitchenChairTable[0] = kitchenSpriteSheet.crop(21,26,62,54);
            kitchenChairTable[1] = kitchenSpriteSheet.crop(108,14,30,35);
            kitchenChairTable[2] = kitchenSpriteSheet.crop(108,52,30,35);

            people[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Gary.png"));
            people[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Doctor2Female.png"));
            people[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Sandy.png"));
            people[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Hipster.png"));
            people[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Plankton.png")); //plankton
            people[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Mad_Scientist3Female.png"));
            people[6] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Programmer2Male.png"));
            people[7] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Patrick.png"));
            people[8] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Weather_Reporter2Female.png"));
            people[9] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/MrKrabs.png")); //inspector
            people[10] = ImageIO.read(getClass().getResourceAsStream("/Sheets/People/Squidward.png")); //antiV

            kitchenCounter[0] = kitchenCounterSpriteSheet.crop(224,12,32,43);//stoveTop
            kitchenCounter[1] = kitchenCounterSpriteSheet.crop(96,76,32,43);//Vegetables
            kitchenCounter[2] = kitchenCounterSpriteSheet.crop(193,70,32,49);//teaPot
            kitchenCounter[3] = kitchenCounterSpriteSheet.crop(0,245,30,43);//Empty
            kitchenCounter[4] = kitchenCounterSpriteSheet.crop(96,200,32,48);//fruit basket
            kitchenCounter[5] = kitchenCounterSpriteSheet.crop(64,73,32,46);//cheese
            kitchenCounter[6] = kitchenCounterSpriteSheet.crop(0,133,32,50);//plates
            kitchenCounter[7] = kitchenCounterSpriteSheet.crop(0,63,34,56);//buns
            kitchenCounter[8] = kitchenCounterSpriteSheet.crop(32, 203, 34, 44); //onion counter
            kitchenCounter[9] = ImageIO.read(getClass().getResourceAsStream("/Sheets/ColoredCounter.png")); //colored empty counter
            
            ingredients[0] = burgerSpriteSheet.crop(25, 16, 112, 43); // top bun
            ingredients[1] = burgerSpriteSheet.crop(30, 134, 103, 48); // patty
            ingredients[2] = burgerSpriteSheet.crop(169, 213, 102, 39); // cheese
            ingredients[3] = burgerSpriteSheet.crop(169, 158, 110, 41); // tomato
            ingredients[4] = burgerSpriteSheet.crop(161, 62, 117, 34); // lettuce
            ingredients[5] = burgerSpriteSheet.crop(444, 270, 115, 39); // bottom bun
            ingredients[6] = burgerSpriteSheet.crop(575, 263, 131, 51); // plate
            ingredients[7] = burgerSpriteSheet.crop(307, 154, 131, 44); //onion

//            chef[0] = chefSpriteSheet.crop(30,3,66,120);
//            chef[1] = chefSpriteSheet.crop(159,3,66,120);
//            chef[2] = chefSpriteSheet.crop(287,3,67,120);
//            chef[3] = chefSpriteSheet.crop(31,129,66,120);
            
            spongebob[0] = spongebobSpriteSheet.crop(162,3,37,70);
            spongebob[1] = spongebobSpriteSheet.crop(12,6,37,70);
            spongebob[2] = spongebobSpriteSheet.crop(125,8,37,65);

            icon =  new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Sheets/KrustyKrabHat.png")));


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image ( who made that so complicated :< )
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
