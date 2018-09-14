   package comp3110.mechanismsDomain;

/**
* All applications are implemented as classes which extend the Application class. For example,
* see the {@link comp3110.MicrowaveOven MicrowaveOven} class.
*/

   public abstract class Application {
   
    /**
     * The initialise method needs to be defined in each sub class to create
     * and initialise the application's domains and to start the 
     * {@link comp3110.mechanismsDomain.ApplicationEventQueue ApplicationEventQueue}
     */
      public abstract void initialise();
   
   }
