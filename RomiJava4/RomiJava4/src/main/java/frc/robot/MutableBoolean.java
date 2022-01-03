package frc.robot;

/**
 * A boolean holder which can be used by reference
 */
public class MutableBoolean {
    Boolean ValueReference;
    /**
     * Constructor for mutable boolean
     */
    MutableBoolean(){}
    /**
     * Condtructor for mutable boolean with initial default value
     * @param val the initial value
     */
    MutableBoolean(boolean val){set(val);}

    /**
     * sets the boolean to a val
     * @param val the boolean to set it to
     */
    public void set(boolean val){
        ValueReference = val;
    }

    /**
     * gets the boolean value
     * @return the boolean value
     */
    public boolean get(){
        return ValueReference;
    }
}
