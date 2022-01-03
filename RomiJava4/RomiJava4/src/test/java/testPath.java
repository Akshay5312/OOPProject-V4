//package frc.robot;
import org.junit.*;

import frc.robot.EncapsulatedProject;

import static org.junit.Assert.*;

public class testPath {

    @Test 
    public void testOutOfBounds(){
        EncapsulatedProject newProj = new EncapsulatedProject();
        int[] expectedAns = {3,1};
        assertArrayEquals(newProj.testPath(6,1,0), expectedAns);
    }
    
    @Test 
    public void testCorrect(){
        EncapsulatedProject newProj = new EncapsulatedProject();
        int[] expectedAns = {1,2};
        assertArrayEquals(newProj.testPath(1,2,0), expectedAns);
    }

    @Test 
    public void testBlocked(){
        EncapsulatedProject newProj = new EncapsulatedProject();
        int[] expectedAns = {2,2};
        assertArrayEquals(newProj.testPath(2,3,0), expectedAns);
    }

}
