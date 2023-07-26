package object;

import MyGame.GamePanel;
import characters.Entity;


public class OBJ_Key extends Entity {
    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/objects/key",gp.tileSize,gp.tileSize);
    }
}
