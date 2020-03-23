package game;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class RectangleGenerator{
   private int width;
   private int height;
   private int xPos;
   private int yPos;
   
   public RectangleGenerator(int xPos, int yPos, int width,int height){
      this.xPos = xPos;
      this.yPos = yPos;
      this.width = width;
      this.height = height;
   }
   
   public Rectangle getRect(){
      return (new Rectangle(this.xPos, this.yPos, this.width, this.height));  
   }
   
   public int getXPos(){
      return this.xPos;
   }
   
   public int getYPos(){
      return this.yPos;
   }
   
   public int getWidth(){
      return this.width;
   }
   
   public int getHeight(){
      return this.height;
   }
   
   public void rectMove(){
      this.yPos+=2;
   }
  
}
   