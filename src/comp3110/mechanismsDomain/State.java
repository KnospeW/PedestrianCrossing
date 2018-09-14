   package comp3110.mechanismsDomain;

/**
* Each state in an xtUML State Model is implemented as a class which extends the State class.
*/
   public abstract class State {
   
   /**
   * This method is implemented in each subclass (each state) to execute the xtUML actions 
   * associated with the state.
   */
      public abstract void procedure(Object parameters);
   
   }

