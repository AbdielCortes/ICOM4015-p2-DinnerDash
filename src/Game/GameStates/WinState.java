package Game.GameStates;

import Main.Handler;
import Resources.Images;
import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Game.Entities.Dynamic.Client;
import Game.Entities.Dynamic.Player;
import Game.World.Restaurant_1;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class WinState extends State {

    private UIManager uiManager;

    public WinState(Handler handler) {
    	super(handler);
    	uiManager = new UIManager(handler);
    	handler.getMouseManager().setUimanager(uiManager);
    	

    	//restart button
    	uiManager.addObjects(new UIImageButton(80, 270+(66+30), 186, 66, Images.Restart, new ClickListlener() {
    		@Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                handler.getGame().reStart(); //restarts game
                State.setState(handler.getGame().gameState);
            }
    	}));
    	
    	//return to title screen button
    	uiManager.addObjects(new UIImageButton(80, 270, 186, 66, Images.BTitle, () -> {
    		handler.getMouseManager().setUimanager(null);
    		State.setState(handler.getGame().menuState);
    	}));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.win,0,0,864,810,null);
    	g.setFont(new Font("TimesNewRoman", Font.PLAIN, 30));
        g.setColor(Color.BLACK);     	
    	g.drawString(""+Player.getServedCustomers(), 800, 150);
    	g.drawString(""+Restaurant_1.getLeftClients(), 800, 224);
    	g.setColor(Color.WHITE);
    	g.fillRect(handler.getWidth()/2 -230, handler.getHeight()-80, 456, 40);
    	g.setColor(Color.BLACK);
    	g.drawString("In Memory of Stephen Hillenburg", handler.getWidth()/2 -220, handler.getHeight()-50);

    	uiManager.Render(g);
    }
}
