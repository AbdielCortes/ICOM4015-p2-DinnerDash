package Game.GameStates;


import Main.Handler;
import Resources.Images;
import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PlanktonState extends State {

    private UIManager uiManager;

    public PlanktonState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


        uiManager.addObjects(new UIImageButton(handler.getWidth()/2+120, 190, 186, 66, Images.BTitle, new ClickListlener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().menuState);
            }
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2+120, 290, 186, 66, Images.Restart, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                handler.getGame().reStart();
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.plankton,0,0,handler.getWidth(),handler.getHeight(),null);
        
        uiManager.Render(g);
    }


}
