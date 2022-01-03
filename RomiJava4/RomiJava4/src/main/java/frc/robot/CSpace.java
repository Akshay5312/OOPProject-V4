package frc.robot;

/**
 * represents the configuration space
 */
public interface CSpace {

    /**
     * checks if a configuration is free
     * @param C the configuration to check
     * @return if the configuration is in the Cspace
     */
    public MutableBoolean isFree(PlanarConfiguration C);
    
}
