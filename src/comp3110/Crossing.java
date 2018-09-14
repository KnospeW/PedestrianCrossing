  package comp3110;


   import comp3110.crossingDomain.*;
   import comp3110.simulatedCrossingHardwareDomain.*;
   import comp3110.mechanismsDomain.*;

/**
 *
 * This is the main program for the pedestrian crossing application. It creates each of the
 * executable domains in the application (instances of {@link comp8120.crossingDomain.CrossingDomain
 * CrossingDomain} and {@link comp8120.simulatedCrossingHardwareDomain.SimulatedCrossingHardwareDomain
 * SimulatedCrossingHardwareDomain}) and connects them together with an explicit bridge. The domains
 * are then initialised and the application event queue is started. The program will then start responding
 * to exeternal events.
 */


   // Main programs are declared within a class that extends
   // comp8120.mechanismsDomain.Application.
   //
   public class Crossing extends Application {
   
      /**
       * The initialise() method creates and connects the executable domains
       * of the application. It then starts the application event queue. 
       *
       * See the <A HREF="../src-html/comp8120/Crossing.html">source code</A> for commented implementation.
       */
      public void initialise() {
	 
	 // Create instances of the two executable domains involved in the
         // microwave oven
	 //
	 CrossingDomain                  crossingDomain  = new CrossingDomain();
	 SimulatedCrossingHardwareDomain hardwareDomain  = new SimulatedCrossingHardwareDomain();
	 
         // Establish the explicit bridge between the Microwave and Hardware domains
         //
	 crossingDomain.setHardwareEE(hardwareDomain);
	 hardwareDomain.setCrossingEE(crossingDomain);
	 
         // Initialise the two domains
         //
         hardwareDomain.initialise();
         crossingDomain.initialise();
      
         // Start the event queue in order to start the processing
         // of events
         //
         ApplicationEventQueue.start();
      }
   
      /**
       * The standard Java main method. It creates an instance of the enclosing class and
       * calls it's initialise() method to setup and start the microwave application.
       */
      public static void main(String[] args) {
         Crossing crossing = new Crossing();
         crossing.initialise();
      }
   
   }
