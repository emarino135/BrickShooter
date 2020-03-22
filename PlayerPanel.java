import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class PlayerPanel extends RectangleGenerator{
   private int posX;
   private int posY;
   private int width;
   private int height;
   
   public PlayerPanel(int posX, int posY, int width, int height){
      super (posX, posY, width, height);
      setLocation();
      setDimension();
   }
   
   public void setLocation(){
      this.posX = 460;
      this.posY = 900;
   }
   
   public void setDimension(){
      this.width = 150;
      this.height= 50;
   }
   
   public int getXPos(){
      return this.posX;
   }
   
   public int getYPos(){
      return this.posY;
   }
   
   public int getWidth(){
      return this.width;
   }
   
   public int getHeight(){
      return this.height;
   }
   
   public Rectangle getRect(){
      return new Rectangle(this.posX, this.posY, this.width, this.height);
   }
   
   public void moveRight(){
      this.posX+=20;
   }
   
   public void moveLeft(){
      this.posX-=20;
   }
}

