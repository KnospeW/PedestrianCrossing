package comp3110.mechanismsDomain;

/**
 *
 * Each executable domain in an application is implemented as a java class which extends the
 * Domain class. For examples see the {@link comp3110.crossingDomain.CrossingDomain CrossingDomain} and 
 * {@link comp3110.simulatedCrossingHardwareDomain.SimulatedCrossingHardwareDomain
 * SimulatedCrossingHardwareDomain} classes.
 */
public abstract class Domain {

    /**
     * The initialise() method creates the objects and links specified in the domain's Class Diagram that
     * are needed when the application starts. The initialise() method may also
     * initialise class attributes.
     */
  public abstract void initialise();
  
}
