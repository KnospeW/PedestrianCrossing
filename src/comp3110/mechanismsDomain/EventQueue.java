   package comp3110.mechanismsDomain;

   import java.util.Vector;
   
/**
 *
 * This class implements a simple queue used by 
 * the {@link comp3110.mechanismsDomain.ApplicationEventQueue ApplicationEventQueue}
 */
   
   class EventQueue {
      Vector queue;
   
      public EventQueue(int size) {
         queue = new Vector(size);
      }
   
      public synchronized void queueEvent(EventInstance theEvent) {
         queue.addElement((Object)theEvent);
         notifyAll();
      }
   
      public synchronized EventInstance getEvent(){
         while (queue.isEmpty())
            try {
               wait();
            }
               catch(InterruptedException e) {
               }
         EventInstance theObject = (EventInstance)queue.firstElement();
         queue.removeElementAt(0);
         return theObject;
      }
   }
