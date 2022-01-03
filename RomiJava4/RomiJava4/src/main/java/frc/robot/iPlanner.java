package frc.robot;

/**
 * An interface for path planners 
 */
public interface iPlanner {
    /**
     * gets the path from a source configuration to a destination configuration
     * @param Source The source configuration
     * @param Target the target configuration
     * @return the path from Source to Target
     */
    public Path getPath(PlanarConfiguration Source, PlanarConfiguration Target);
}
