import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class RunningGame extends JPanel implements KeyListener,Runnable {
   public static boolean gameStart;
    
   PlayerPanel player;
   Ball ball;
   
   private boolean canMove;
   private boolean ballMove;
   private boolean horizontalMove;
   private boolean exists;
   private boolean isMoveRight; 
   private boolean isMoveLeft;
   
   private int currentPosPanel;
   private int randomXPos;
   private int randomYPos;
   
   private Thread thread;
    
   ArrayList<RectangleGenerator> brickContainer;
    
   public RunningGame(){
      exists = true;
      gameStart = false;
      canMove = false;
      ballMove = false;
      horizontalMove = true;
      isMoveRight = false;
      isMoveLeft = false;
            
      randomXPos = 0;
      randomYPos = 0;
      currentPosPanel = 0;
      
      player = new PlayerPanel(0,0,0,0);// create object of class PlayerPanel
      ball = new Ball(horizontalMove,515,880,0,0);//create object of class Ball
      ball.init(currentPosPanel);
      player.setLocation();//set inital location of panel
      player.setDimension();// set size of panel (the sill will remain unchanged)
      
      brickContainer  = new ArrayList<RectangleGenerator>();// arrayList to store all objects of class RectangleGenerator
         
      addKeyListener(this);
      setFocusable(true); 
      
      start();//start the game with method start 
   }      
   
   public void start(){
      System.out.println("draw");
      thread = new Thread(this);// create Thread 
      thread.start();// thread starts
   }
   
   public void run(){
      for (;;){
         if (gameStart == true){// if gameStart is true, the game will begin
            addRect();
            for (int i=0 ; i<brickContainer.size();i++){ 
               brickContainer.get(i).rectMove();// rectMove that enables automatically-created rectangles to move vertically toward the panel
            }
            
            if (ball.getYPos()<880){
               ballMove = true;// ballMove must be true if you want the ball to movve
            }
            if (ballMove == true){
               ball.moveUp();// method moveUp allows the ball to go up
            }
               
            ball.checkCollision(brickContainer);// check if the fired ball collides with the falling rectangles
            if (ball.getYPos()!=880){
               horizontalMove = false;// if horizontalMove is false, the ball cannot move horizontally
            }               
            resetBall();
            repaint();
         }
         try{
            Thread.sleep(100);
         }
         catch(InterruptedException e){
         }
      }
   }
   

   public void paintComponent(Graphics g){
      super.paintComponent(g);
      for (int i=0; i<brickContainer.size();i++){
         g.drawRect(brickContainer.get(i).getXPos(), brickContainer.get(i).getYPos(), brickContainer.get(i).getWidth(),brickContainer.get(i).getHeight());
         g.setColor(Color.RED);
         g.fillRect(brickContainer.get(i).getXPos(), brickContainer.get(i).getYPos(), brickContainer.get(i).getWidth(), brickContainer.get(i).getHeight());
      }
      
         g.drawRect(player.getXPos(), player.getYPos(), player.getWidth(), player.getHeight());
         g.setColor(Color.GREEN);
         g.fillRect(player.getXPos(), player.getYPos(), player.getWidth(), player.getHeight());
         
         g.setColor(Color.GREEN);
         g.fillOval(ball.getXPos(), ball.getYPos(), ball.getWidth(), ball.getHeight());  
     }

   public void addRect(){
      randomXPos = (int)(Math.random()*1000);
      int [] posOfBrick = {-50, -60, -70, -80, -90, -100};
      randomYPos = (int)(Math.random()*6);
      brickContainer.add(new RectangleGenerator(randomXPos,posOfBrick[randomYPos],50,50));
   }
   
   public void resetBall(){
      currentPosPanel = player.getXPos();
      if (ball.getYPos() <= -10){
         System.out.println("ball destroyed ");
         ball.resetPos(currentPosPanel);
         System.out.println("currentPos in resetBall() "+ball.getXPos());
         horizontalMove = true;
         ballMove = false;
      }
   }
   
   public void keyPressed(KeyEvent e){
      int move =e.getKeyCode();
      if (move == KeyEvent.VK_LEFT){
         isMoveRight = false;
         isMoveLeft = true;
         player.moveLeft();// update panel's movement
         if (horizontalMove){
            ball.moveLeft();// update ball's movement
         }
      }
      if (move  == KeyEvent.VK_RIGHT){
         isMoveRight = true;
         isMoveLeft = false;
         player.moveRight();
         if (horizontalMove){
            ball.moveRight();
         }
      }
      if (horizontalMove == false){
         ball.getUpdatePos(isMoveRight, isMoveLeft, currentPosPanel);// update the position of the panel
         System.out.println("player.getXPos() when horizontalMove is wrong "+player.getXPos()); 
      }
      if (move == KeyEvent.VK_ENTER){// hit enter to start the game
         gameStart = true;
      }
      if (move == KeyEvent.VK_SPACE){
         ballMove = true;
      }
   }
        
   public void keyTyped(KeyEvent e){}
   
   public void keyReleased(KeyEvent e){
      isMoveRight = false;// 
      isMoveLeft = false;
   }
           
   public static void main(String [] args){
      JFrame f = new JFrame();
      f.add(new RunningGame());
      f.setSize(1000,2000);
      f.setVisible(true);
   }
}
  
        
   
   
   
   
      