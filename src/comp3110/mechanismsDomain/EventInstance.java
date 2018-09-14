   package comp3110.mechanismsDomain;

/**
* All events generated during the execution of an application are implemented
* as instances of the EventInstance Java class. This class is not often used directly by the programmer.
*/

   public class EventInstance {
   
      int           event;
      Object        parameters;
      ActiveClass  target;
   
      /**
      * Creates a new EventInstance
      *
      * @param theEvent the event to be transmitted
      * @param theParameters an object containing any event data
      * @param theTarget the class that is to receive the event
      */
      public EventInstance(int theEvent, Object theParameters, ActiveClass theTarget) {
         event      = theEvent;
	 parameters = theParameters;
         target     = theTarget;
      }
   
   /**
   * returns the event associated with this EventInstance
   *
   * @return the event associated with this instance
   */
      public int getEvent() {
         return event;
      }
   
   /**
   * returns the parameters associated with this EventInstance
   *
   * @return the parameters associated with this instance
   */
      public Object getParameters() {
         return parameters;
      }
   
   /**
   * returns the target class associated with this EventInstance
   *
   * @return the target class associated with this instance
   */
   
      public ActiveClass getTarget() {
         return target;
      }
   
   }
