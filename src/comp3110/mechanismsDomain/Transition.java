   package comp3110.mechanismsDomain;

  /**
   * All transitions between states in an xtUML state model are implemented as instances of the Transition
   * class.
   */
   public class Transition {
   
      State fromState;
      State toState;
      int   event;
   
      /**
      * Constructs a transition between two states that will occur in response to
      * a given event.
      *
      * @param fromState the state from which the event will cause a transition
      * @param toState the state to which the object is required to move
      * @param event the ID of the event that will cause the transition
      */
      public Transition(State fromState, State toState, int event) {
         this.fromState  = fromState;
         this.toState    = toState;
         this.event      = event;
      }
   
      /**
      * returns the from state of the transition
      *
      * @return the from state
      */
      public State getFromState() {
         return fromState;
      }
   
     /**
      * returns the to state of the transition
      *
      * @return the to state
      */
      public State getToState() {
         return toState;
      }
   
     /**
      * returns the ID of the event associated with the transition
      *
      * @return the event ID
      */
      public int getEvent() {
         return event;
      }
   
   }
