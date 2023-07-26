package MyGame;


import characters.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica , purisaB;
    BufferedImage heart_full , heart_half, heart_blank;
    public boolean messageOn = false;
    public  String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;


    public UI(GamePanel gp){
        this.gp = gp;
        try{
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT , is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT , is);
        }catch (Exception e){
            e.printStackTrace();
        }

        //create heart object
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2){
        this.g2=g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        //titleState
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //playState
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        //pauseState
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //dialogueState
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawPlayerLife(){
//        gp.player.life = 5;

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //drawMaxLife
        while(i<gp.player.maxLife/2){
            g2.drawImage(heart_blank , x, y, null);
            i++;
            x+= gp.tileSize;
        }
        //reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //drawCurrentLife
        while(i<gp.player.life){
            g2.drawImage(heart_half , x, y, null);
            i++;
            if(i<gp.player.life){
                g2.drawImage(heart_full , x, y, null);
            }
            i++;
            x+= gp.tileSize;
        }

    }
    public void drawTitleScreen(){
        //background color
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0, gp.screenWidth , gp.screenHeight);

        //titleScreen text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text = "Blue Boy Adventure";
        int x = getXforCentredText(text);
        int y = gp.tileSize*3;

        //titleScreen Text Shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text , x+5 , y+5);

        //titleScreen Text Color
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        //Image
        x = gp.screenWidth /2 - (gp.tileSize*2)/2;
        y += gp.tileSize *2;
        g2.drawImage(gp.player.down1 , x, y , gp.tileSize*2 , gp.tileSize*2 , null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "NEW GAME";
        x = getXforCentredText(text);
        y += gp.tileSize*3.5;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">" , x-gp.tileSize , y);
        }

        text = "LOAD GAME";
        x = getXforCentredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">" , x-gp.tileSize , y);
        }

        text = "QUIT";
        x = getXforCentredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">" , x-gp.tileSize , y);
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN , 80F));
        String text = "PAUSED";
        int x = getXforCentredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text , x , y);
    }
    public void drawDialogueScreen(){
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32f));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=40;
        }
    }
    public void drawSubWindow(int x , int y , int width , int height){
        Color c = new Color(0,0,0,220);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
    public int getXforCentredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
