package game;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
public class Ball extends RectangleGenerator{
   int posX;
   int posY;
   int width;
   int height;
   int tempX;
   
   boolean horizontalMove;
   
   PlayerPanel player;
   
   public Ball(boolean horizontalMove,int currentPosPanel, int posY, int width, int height){
      super (currentPosPanel, posY, width, height);
      this.posY = posY;
      this.width = 35;
      this.height = 35;
      this.horizontalMove = horizontalMove;
      player = new PlayerPanel(0,0,0,0);
   }
   public void init(int currentPosPanel){
      if (currentPosPanel == 0){
         posX = 515;
      }
   }   
   public void moveUp(){
      this.posY-=20;
   }
   public int getXPos(){
      return posX;
   }
   public int getYPos(){
      if (this.posY == -10){
         this.posY = 880;
      }
      else{
         ;
      }
      return this.posY;
   }
   public void resetPos(int currentPosPanel){
      posX = currentPosPanel+55;
      //posX = tempX;
      System.out.println(posX);
      this.posY = 880;
   }
   public int getWidth(){
      return this.width;
   }
   public int getHeight(){
      return this.height;
   }
      
   public void moveRight(){
      player.moveRight();
      posX = player.getXPos()+55;
   }
   
   public void moveLeft(){
      player.moveLeft();
      System.out.println(player.getXPos());
      posX = player.getXPos()+55;
   }
   public void getUpdatePos(boolean isMoveRight,boolean isMoveLeft ,int currentPosPanel){
      if (isMoveRight == false && isMoveLeft == true){
         player.moveLeft();
      }
      else if (isMoveRight == true && isMoveLeft ==false){
         player.moveRight();
      }
      else{
         ;
      }
      tempX = player.getXPos()+55;
      System.out.println("currentPosPanel inside ball class "+tempX);
  } 
   
   public void checkCollision(ArrayList<RectangleGenerator>brickContainer){
      for (int i = 0; i<brickContainer.size() ;i++){
         if (new Rectangle(getXPos(), getYPos(), getWidth(), getHeight()).intersects(brickContainer.get(i).getRect())){
            brickContainer.remove(i);
         }
      }
   }    
}
