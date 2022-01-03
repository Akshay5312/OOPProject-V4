package frc.robot;

import edu.wpi.first.wpilibj.Timer;

/**
 * An object that stores this project's run functions to clean up Robot.java
 */
public class EncapsulatedProject {

  /**
   * constructor, runs the actual project code
   */
    public EncapsulatedProject(){ 
      GuiTest();
    }

    /**
     * moveTo moves the turtle to a configuration using Gridturtle.moveTo
     * @param Turtle the grid turtle to move
     * @param PC the target configuration
     * @param delaySec the time to wait before moving
     * @return if move is possible
     */
    public boolean moveTo(GridTurtle Turtle, PlanarConfiguration PC, double delaySec){
        Timer.delay(delaySec);
        return Turtle.moveTo(PC);
    }

    /**
     * gets a path and prints the value
     */
    public int[] testPath(int targetY, int targetX, int targetH){
      PlanarConfiguration StartConfig = new PlanarConfiguration(0,0,0);
      PlanarConfiguration Target = new PlanarConfiguration(targetY,targetX,targetH);
      Grid grid = new Grid(5,7);
      for(int i = 0; i < 7; i++){
        grid.setObstacle( new PlanarConfiguration(i,3,0)  );
      }
      DijkstraPlanner how = new DijkstraPlanner(grid);

      Path currPath = new Path();
      currPath.copy( how.getPath(StartConfig, Target) );

      PlanarConfiguration retCon = currPath.getPath().get(1);
      //currPath.printPath();
      int[] retConInt = {retCon.getX(), retCon.getY()};

      return retConInt;



    }


    /**
     * GuiTest runs the actual code that creates a gui
     */
    private void GuiTest(){
      PlanarConfiguration StartConfig = new PlanarConfiguration(0,0,0);
      VirtualBot Turtle = new VirtualBot(StartConfig);
      PlanarConfiguration Target = new PlanarConfiguration(StartConfig);
      PlanarConfiguration ofRobot =Turtle.getPose();
      Grid grid = new Grid(10,15);
  
      
      iPlanner how = new DijkstraPlanner(grid);
      GridGUI UserI = new GridGUI(grid, Target, ofRobot);
      Path currPath = new Path();
  
      UserI.setPath(Turtle.getPath());
      while(true){
        if( !((ofRobot.getX() == Target.getX()) && (ofRobot.getY() == Target.getY())) ){
  
          currPath.copy( how.getPath(ofRobot, Target) );
          if(currPath == null){}
          else{ 
            if( 
              moveTo(Turtle,currPath.getPath().get(
                   currPath.getPath().size()-2
                 ), 0.25
                 )
            ){}else{
              
              VirtualBot Simbot = new VirtualBot(Turtle.getPose());
              Simbot.move();

              grid.setObstacle(Simbot.getPose());
            
            }

          }
        }
        UserI.update();
      }

    }



}
