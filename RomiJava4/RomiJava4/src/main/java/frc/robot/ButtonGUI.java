package frc.robot;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.Color;


/**
 * Manages a button in the GUI, that represents one node
 */
public class ButtonGUI extends JButton{
    //Boolean ctrPressed;
    PlanarConfiguration configAtPoint;
    public MutableBoolean isntObstacle;
    //Integer headingFromPath;
    PlanarConfiguration ofRobot;
    PlanarConfiguration targetPointer;

    String[] headingString = {"\\/","->","/\\","<-",""};

    /**
     * Constructor of a button object 
     * 
     * @param targetPointer reference to target to go configuration
     * @param isNotObstacle stores occupancy value of node represented by button
     * @param ofRobot reference to the location of the robot
     */
    public ButtonGUI(PlanarConfiguration targetPointer, MutableBoolean isNotObstacle, PlanarConfiguration ofRobot){
        this.configAtPoint = configAtPoint;
        this.isntObstacle = isNotObstacle;
        this.ofRobot = ofRobot;
        this.targetPointer = targetPointer;


        addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                        if(
                            (ActionEvent.CTRL_MASK & e.getModifiers()) != 0
                        ){
                            //System.out.print("ctrClick");
                            setAsObstacle();
                        }else{
                            targetPointer.CopyFrom(configAtPoint); 
                        }

                        update();
                    }  
                });  
    }

    /**
     * sets configuration of node that button representsa
     * @param configAtPoint the configuration of the node
     */
    public void setConfiguration(PlanarConfiguration configAtPoint){
        this.configAtPoint = configAtPoint;
    }

    /**
     * reverts the value of occupancy 
     */
    public void setAsObstacle(){
        isntObstacle.set(!isntObstacle.get());
    }

    /**
     * sets the heading of the node
     * @param heading the heading value at the node
     */
    public void setHeading(int heading){
        configAtPoint.setHeading(heading);
    }

    /**
     * sets the colors and text of a button appropriately
     */
    public void update(){
        setText( headingString[configAtPoint.getHeading()] );
        setFont( new Font("Arial", Font.BOLD, 30) );
        if((configAtPoint.getY() == targetPointer.getY()) && (configAtPoint.getX() == targetPointer.getX())){
            setBackground(Color.WHITE);
        }else{
            if(!isntObstacle.get()){
                setBackground(Color.RED);
            }else{
                if((configAtPoint.getY() == ofRobot.getY()) && (configAtPoint.getX() == ofRobot.getX())){
                    setBackground(Color.GREEN);
                }else{
                    setBackground(Color.GRAY);
                }
            }
        }
    }

}
