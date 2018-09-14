   package comp3110.mechanismsDomain;

   import java.util.Vector;
   import java.util.Enumeration;

/**
* All classes in an xtUML domain that have dynamic behaviour are implemented as java classes which
* extend the ActiveClass class.
*/
   public class ActiveClass {
   
      private   Vector stateVector;
      private   Vector transitionVector;
      protected State  currentState;
   
      public ActiveClass() {
         stateVector      = new Vector();
         transitionVector = new Vector();
         currentState     = null;
      }
   
      /**
      * Adds a state to the classes state model.
      *
      * @param state the state to be added to the class
      */
      public void addState(State state) {
         stateVector.add(state);
      }
   
      /**
      * Adds a transition to the classes state model
      *
      * @param transition the transition to be added to the class
      */
      public void addTransition(Transition transition) {
         transitionVector.add(transition);
      }
   
     /**
      * returns an enumeration of all states defined in the classes state model.
      *
      * @return an enumeration of all states
      */
      public Enumeration states() {
         return stateVector.elements();
      }
   
     /**
      * returns an enumeration of all transitions defined in the classes state model.
      *
      * @return an enumeration of all transitions
      */
      public Enumeration transitions() {
         return transitionVector.elements();
      }
   
     /**
      * Takes an event and applies it to the classes state model. The method first checks if
      * the event is allowed for the current state. If it is, the object moves to a new state
      * and actions associated with the new state are executed. If the event is not allowed for
      * the current state, the event is ignored.
      *
      * Note that this method is usually called by the 
      * {@link comp3110.mechanismsDomain.ApplicationEventQueue#start() ApplicationEventQueue.start()}
      * method.
      *
      * @param event the event to process
      */
      public void processEvent(EventInstance eventInstance) {
         Transition transition;
      
         Messages.debug("Process event");
         Messages.debug("   - class '" + toString() + "'");
         Messages.debug("   - current state is '" + currentState.toString() + "'");
      
         for (Enumeration e=transitions(); e.hasMoreElements(); ) {
            transition = (Transition)(e.nextElement());
            if (transition.getFromState().equals(currentState)) {
               if (transition.getEvent() == eventInstance.getEvent()) {
                  Messages.debug("   - moving to state '" + transition.getToState().toString() + "'");
                  transition.getToState().procedure(eventInstance.getParameters());
                  currentState = transition.getToState();
                  return;
               }
            }
         }
         Messages.debug("   - event ignored");
      }
   
      protected void setInitialState(State creationState) {
         currentState = creationState;	
      }

     public void initialise() {
        currentState.procedure(null);
     }
   
   }
