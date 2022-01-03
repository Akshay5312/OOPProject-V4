package frc.robot;

import java.util.List;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;


/**
 * Manages a GUI as a grid of buttons
 */
public class GridGUI {
    Grid grid;
    Path pathToRepresent;
    ButtonGUI[][] Buttons;
    GridLayout thisGridLayout;

    /**
     * Constructs and initializes a grid GUI
     * @param grid the workspace to represent
     * @param tPointer reference to the target configuration
     * @param ofRobot reference to the robot configuration
     */
    GridGUI(Grid grid, PlanarConfiguration tPointer, PlanarConfiguration ofRobot){
        

        JFrame frame = new JFrame("GridLayoutDemo");
        System.out.println("Made window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(3000, 300);
        int sY = grid.getSize()[0];
        int sX = grid.getSize()[1];

        Buttons = new ButtonGUI[sY][sX];
        thisGridLayout = new GridLayout(sY,sX);

        

        for(int i = 0; i < Buttons.length; i++){
            for(int j = 0; j < Buttons[i].length; j++){
                Buttons[i][j] = new ButtonGUI( tPointer, grid.isFree(new PlanarConfiguration(j,i,4)), ofRobot );
                frame.getContentPane().add(Buttons[i][j]);
                Buttons[i][j].setConfiguration(new PlanarConfiguration(j,i,4));
                //Buttons[i][j].setCTRpress(ctrPressed);
                //System.out.print(Buttons[i][j].isObstacle);
            }
        }
        frame.pack();
        // Make frame visible on the screen
        frame.setVisible(true);
        //Set up the content pane.
        frame.getContentPane().setLayout((LayoutManager) thisGridLayout);
        
    }

    /**
     * sets the path to represent
     * @param PTR the path to represent on the GUI
     */
    public void setPath(Path PTR){
        pathToRepresent = PTR;
    }

    /**
     * updates the buttons on the gui
     */
    public void update(){
        for(int i = 0; i < Buttons.length; i++){
            for(int j = 0; j < Buttons[i].length; j++){
                ButtonGUI B = Buttons[i][j];
                B.setHeading(headingFromPath(i,j));
                

                //System.out.print(B.isObstacle);
                B.update();
            }
        }
    }

    /**
     * gets the heading at node Y,X
     * @param Y y index
     * @param X x index
     * @return heading at node y,x
     */
    public int headingFromPath(int Y, int X){
        if(pathToRepresent == null){
            return 4;
        }
        List<PlanarConfiguration> P = pathToRepresent.getPath();
        for(int i = P.size()-1; i >= 0; i--){
            PlanarConfiguration cf = P.get(i);
            if((cf.getX() == X) && (cf.getY() == Y)){
                return cf.getHeading();
            }
        }
        return 4;
    }


}
