package comp3110.mechanismsDomain;

/**
* The Timer class is used to implement the timed event generation actions available
* in the {@link comp3110.mechanismsDomain.ApplicationEventQueue ApplicationEventQueue} class.
*/

public class Timer extends Thread implements TimerConstants {
   
  private long          delay;
  private int           event;
  private Object        parameters;
  private ActiveClass  target;
  private long          startTime;
    
   
  /**
   * Constructs a new timer that will place an event on the
   * {@link comp3110.mechanismsDomain.ApplicationEventQueue ApplicationEventQueue}
   * after a given time
   *
   * @param theDelay the time delay, in milliseconds, before the event is generated.
   * @param theEvent the event that is to be sent to the target object
   * @param theParameters the parameters object sent with the event
   * @param theTarget the object to which the event is to be sent
   */
  public Timer(long theDelay, int theEvent, Object theParameters, ActiveClass theTarget) {
    delay      = theDelay;
    event      = theEvent;
    parameters = theParameters;
    target     = theTarget;
    startTime  = System.currentTimeMillis();
    start();
    Messages.debug("Timer started - will fire event in " + delay + " milliseconds");
  }
   
  public void run() {
    try {
      sleep(delay);
      Messages.debug("Timer firing now");
      ApplicationEventQueue.generateEvent(event, parameters, target);
    }
      catch (InterruptedException e) {
        Messages.error("Timer " + toString() + " cancelled");
    }
  }
   
   /**
   * Returns the time remaining before an event is placed on the
   * {@link comp3110.mechanismsDomain.ApplicationEventQueue ApplicationEventQueue}.
   *
   * @return the time remaining
   */
  public long timeToGo() {
    return System.currentTimeMillis() - startTime;
  }
  
   /**
   * Cancels the event
   */
  public void cancel() {
    Messages.debug("Cancelling timer " + toString());
    interrupt();
  }
   
}
