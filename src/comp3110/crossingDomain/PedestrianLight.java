package comp3110.crossingDomain;

import comp3110.mechanismsDomain.*;

   
class PedestrianLight extends ActiveClass implements TimerConstants {
   
  public static final int ALLOW_CROSSING_EVENT   = 0;

  private static final int DONT_WALK_EVENT        = 1;
  private static final int FLASH_WALK_LIGHT_EVENT = 2;
  private static final int WALK_ON_EVENT          = 3;
  private static final int WALK_OFF_EVENT         = 4;

  private long walkTime = 6l*seconds;
  private int  flashCount;
  
  
  private class DontWalkState extends State {
    
    public void procedure(Object parameters) {
      Messages.debug("PedestrianLight DontWalkState procedure called");
      ApplicationEventQueue.generateEvent(TrafficLight.CROSSING_CLEAR_EVENT, null, linkedTrafficLight);
      hardwareEE.processWalkOffEvent();
      hardwareEE.processDontWalkOnEvent();
    }
  }
   
  private class WalkState extends State {
    public void procedure(Object parameters) {
      Messages.debug("PedestrianLight WalkState procedure called");
      hardwareEE.processWalkOnEvent();
      hardwareEE.processDontWalkOffEvent();
      ApplicationEventQueue.generateDelayedEvent(walkTime, FLASH_WALK_LIGHT_EVENT, null, PedestrianLight.this);
      flashCount = 0;
    }
  }
   
  private class WalkLightOffState extends State {
    public void procedure(Object parameters) {
      Messages.debug("PedestrianLight WalkLightOffState procedure called");
      hardwareEE.processWalkOffEvent();
      flashCount = flashCount + 1;
      if (flashCount == 10)
        ApplicationEventQueue.generateEvent(DONT_WALK_EVENT, null, PedestrianLight.this);
      else
        ApplicationEventQueue.generateDelayedEvent(500*ms, WALK_ON_EVENT, null, PedestrianLight.this);     
    }
  }
   
  private class WalkLightOnState extends State {
    public void procedure(Object parameters) {
      Messages.debug("PedestrianLight WalkLightOnState procedure called");
      hardwareEE.processWalkOnEvent();
      ApplicationEventQueue.generateDelayedEvent(500*ms, WALK_OFF_EVENT, null, PedestrianLight.this);     
    }
  }
   
   
  public static CrossingToHardwareServerInterface hardwareEE;

  public PedestrianLight(CrossingToHardwareServerInterface hardwareEE) {
          
    this.hardwareEE = hardwareEE;
    
    DontWalkState      dontWalkState     = new DontWalkState();
    WalkState          walkState         = new WalkState();
    WalkLightOnState   walkLightOnState  = new WalkLightOnState();
    WalkLightOffState  walkLightOffState = new WalkLightOffState();
      
    addState(dontWalkState);
    addState(walkState);
    addState(walkLightOnState);
    addState(walkLightOffState);

    addTransition(new Transition(dontWalkState,  walkState, ALLOW_CROSSING_EVENT));
    addTransition(new Transition(walkState, walkLightOffState,  FLASH_WALK_LIGHT_EVENT));
    addTransition(new Transition(walkLightOffState, dontWalkState,  DONT_WALK_EVENT));
    addTransition(new Transition(walkLightOffState, walkLightOnState, WALK_ON_EVENT));
    addTransition(new Transition(walkLightOnState, walkLightOffState, WALK_OFF_EVENT));
            
    setInitialState(dontWalkState);
  }
  
 
  private TrafficLight linkedTrafficLight;

  public void linkToTrafficLight(TrafficLight theTrafficLight) {
    linkedTrafficLight = theTrafficLight;
  }
  
     
}
