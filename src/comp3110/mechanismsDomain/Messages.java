   package comp3110.mechanismsDomain;

/**
* The Messages class provides standardised formatting for error and debug messages
*/

   public class Messages {
   
   /**
   * Displays an error message and then terminates the calling program.
   */
      public static void fatalError(String message) {
         System.out.println("FATAL ERROR: " + message);
         System.exit(0);
      }
   
   /**
   * Displays an error message and returns.
   */
      public static void error(String message) {
         System.out.println("ERROR: " + message);
      }
   
   /**
   * Displays an debug message and returns.
   */
      public static void debug(String message) {
         System.out.println("DEBUG: " + message);
      }
   }
