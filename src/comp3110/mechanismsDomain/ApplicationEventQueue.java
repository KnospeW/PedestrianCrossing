   package comp3110.mechanismsDomain;

/**
 *
 * All events generated in xtUML models are put on to the ApplicationEventQueue for later asynschronous
 * processing. There is a single event queue for an entire application.
 */
   public class ApplicationEventQueue {
   
      private static final int MAX_QUEUE_LENGTH = 1000;
      private static EventQueue eventQueue = new EventQueue(MAX_QUEUE_LENGTH);
   
    /**
     * Queues an event to a specific target object. This method is used to implement event generation
     * actions.
     *
     * @param event	 the event to be queued
     * @param parameters an object that contains parameters associated with the event
     * @param target the object to which the event is directed
     */
      public static void generateEvent(int event, Object parameters, ActiveClass target) {
         Messages.debug("Queuing Event: " + event + " for target " + target.toString());
         eventQueue.queueEvent(new EventInstance(event, parameters, target));
      }
   
    /**
     * Queues an event to a specific target object after a specified delay.
     *
     * @param delay     the delay before the event is queued (in milliseconds)
     * @param event	the event to be queued
     * @param parameters an object that contains parameters associated with the event
     * @param target the object to which the event is directed
     */
      public static void generateDelayedEvent(long delay, int event, Object parameters, ActiveClass target) {
         Messages.debug("Queuing Delayed Event: " + event + " for target " + target.toString() + " delay " + delay + "ms");
         Timer timer = new Timer(delay, event, parameters, target);
      }
   
      private static EventInstance getEvent() {
         return eventQueue.getEvent();
      }
   
   
    /**
     * Starts a loop that reads an event from the queue and then dispatches it to the associated target object.
     * This method is called by the applications main program after all domains are initialised.
     */
      public static void start() {
         EventInstance eventInstance;
         while (true) {
            eventInstance = ApplicationEventQueue.getEvent();
            Messages.debug("Dequeuing Event: " + eventInstance.getEvent()
                            + " for target " + eventInstance.getTarget().toString() );
            eventInstance.getTarget().processEvent(eventInstance);
         }
      }
   
   }
