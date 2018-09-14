package comp3110.crossingDomain;

import comp3110.mechanismsDomain.*;


public class CrossingDomain extends Domain implements CrossingToHardwareClientInterface {
  
  // The following fields represent instances of classes (objects) that
  // are required by the Crossing domain. They will be created by the initialise() method
  // declared below.
  //
  private PedestrianLight  thePedestrianLight;
  private TrafficLight     theTrafficLight;


  // Each domain class has an initialise() method which is called by the
  // application's main program.
  //
  public void initialise() {
    Messages.debug("CrossingDomain initialising");

    // Create objects that must exist before application starts
    //
    thePedestrianLight = new PedestrianLight(hardwareEE);
    theTrafficLight    = new TrafficLight(hardwareEE);
    
    // Link the objects together as per the microwave domain class diagram
    //
    thePedestrianLight.linkToTrafficLight(theTrafficLight);
    theTrafficLight.linkToPedestrianLight(thePedestrianLight);
    
    thePedestrianLight.initialise();
    theTrafficLight.initialise();

    Messages.debug("CrossingDomain initialised");
  }
  
  // This field connects this domain (Crossing domain) to some
  // hardware domain. The hardware domain may be an interface to the real hardware or 
  // some kind of simulation. The setHardwareEE() method below is used by the application's
  // main program to set the hardware domin with which the Crossing will communicate.
  //
  public static CrossingToHardwareServerInterface hardwareEE;

  public void setHardwareEE(CrossingToHardwareServerInterface hardwareEE) {
    this.hardwareEE = hardwareEE;
  }
  
  
  // The following methods implement the CrossingToHardwareClientInterface
  // which is used by some harware domin (an external entity) to communicate with the
  // Crossing domain. Each of the following methods implements a call from the hardware
  // external entity by generating an appropriate event to some object within the crossing domain
  // as per the Crossing domain Collaboration Diagram. That is these methods implement the
  // sending of events from the hardware domain (an external entity to the crossing domain)
  // to the crossing domain.
  //

  public void processCrossingRequestEvent() {
    Messages.debug("crossingDomain: received CROSSING REQUEST event");
    ApplicationEventQueue.generateEvent(TrafficLight.CROSSING_REQUEST_EVENT, null, theTrafficLight); 
  }
  
  public void processSetFailModeEvent() {
    Messages.debug("crossingDomain: received SET FAIL MODE event");
    ApplicationEventQueue.generateEvent(TrafficLight.SET_FAIL_MODE_EVENT, null, theTrafficLight); 
  }
  
  public void processSetTrafficModeEvent() {
    Messages.debug("crossingDomain: received SET TRAFFIC MODE event");
    ApplicationEventQueue.generateEvent(TrafficLight.SET_TRAFFIC_MODE_EVENT, null, theTrafficLight); 
  }
    
}





