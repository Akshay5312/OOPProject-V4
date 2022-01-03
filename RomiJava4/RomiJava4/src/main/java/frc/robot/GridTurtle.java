package frc.robot;

import java.lang.*;

/**
 * represents a turtle that moves along a grid 
 */
public abstract class GridTurtle {
    
    Path P;
    float lastTime;

    /**
     * constructs a Grid turtle
     */
    GridTurtle(){
      lastTime = System.currentTimeMillis();
    }

    /**
     * Constructs a grid turtle with starting configuration
     * @param initConfig starting configuration
     */
    GridTurtle(PlanarConfiguration initConfig){
        P = new Path(initConfig);
        lastTime = System.currentTimeMillis();
    }

    /**
     * moves from robot's current configuration to destination configuration along a planar CSpace as a turtle
     * @param destConfig the target configuration
     * @return if move is possible
     */
    public boolean moveTo(PlanarConfiguration destConfig){
        
        int x = destConfig.getX();
        int y = destConfig.getY();

        int diffx = x - P.getX();
        int diffy = y - P.getY();

        int theta = P.getheading();

        int thetaTo = theta;
        if(diffx > 0){ 
          thetaTo = 1;
        }
        if(diffx < 0){
          thetaTo = 3;
        }

        if(thetaTo!=theta){
          turnTo(thetaTo);
          addTurn(thetaTo);
        }

        while((x-P.getX()) != 0){
          if(!isObstacle()){
            move();
            addMove();
          }else{
            return false;
          }
        }

        theta = P.getheading();

        thetaTo = theta;
        if(diffy > 0){
          thetaTo = 0;
        }
        if(diffy < 0){
          thetaTo = 2;
        }

        if(thetaTo!=theta){
          turnTo(thetaTo);
          addTurn(thetaTo);
        }

        while((y-P.getY()) != 0){
          if(!isObstacle()){
            move();
            addMove();
          }else{
            return false;
          }
        }
        return true;
    }


    /**
     * adds a moved configuration to the path of the odometer
     */
    protected void addMove(){
        int pX = P.getX();
        int pY = P.getY();
        int pT = P.getheading();
        switch(pT){
            case 0:
              pY+=1;
            break;
            case 1:
              pX+=1;
            break;
            case 2:
              pY-=1;
            break;
            case 3:
              pX-=1;
            break;
          }
          P.addConfig(new PlanarConfiguration(pX,pY,pT));
    }

    /**
     * adds a turned configuration to the path of the odometer
     * @param destHeading the heading to go to
     */
    protected void addTurn(int destHeading){
        int pX = P.getX();
        int pY = P.getY();
        int pT = (destHeading % 4);
        P.addConfig(new PlanarConfiguration(pX,pY,pT));
    }

    /**
     * gets the configuration of the robot
     * @return configuration of robot
     */
    public PlanarConfiguration getPose(){
      return P.getConfig();
    }

    /**
     * Gets a pointer to the robot's path
     * @return the path of the robot
     */
    public Path getPath(){
      return P;
    }

    /**
     * moves turtle one unit in the direction of the heading
     * @return whether a move is possible 
     */
    abstract protected boolean move();

   
    /**
     * turns turtle to target heading
     * @param heading the heading to turn to
     * @return whether turn is possible 
     */ 
    abstract protected boolean turnTo(int heading);

    /**
     * checks to see if there is an obstacle ahead
     * @return if obstacle exists
     */ 
    abstract protected boolean isObstacle();
    
}
