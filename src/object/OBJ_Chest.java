package object;

import MyGame.GamePanel;
import characters.Entity;


public class OBJ_Chest extends Entity {
    public OBJ_Chest(GamePanel gp){
        super(gp);
        name = "Chest";
        down1 = setup("/objects/chest",gp.tileSize,gp.tileSize);
    }
}
