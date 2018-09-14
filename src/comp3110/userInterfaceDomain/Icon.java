   package comp3110.userInterfaceDomain;

   /**
   * All GUI domain buttons are implemented as instances of the PushButton class. Push Buttons
   * are implemented as javax.swing.JButtons and are created with an instance of a class
   * that implements the {@link comp3110.userInterfaceDomain.PushButtonCallback PushButtonCallback}
   * interface. When a Push Button is clicked, the 'buttonPressed()' method of this object is called. 
   */

   public class Icon extends javax.swing.JLabel {
   
      private PushButtonCallback callback;
   
      /**
      * Constructs a PushButton
      *
      * @param label the text to be displayed on the button
      * @param callback an object that implements the {@link comp3110.userInterfaceDomain.PushButtonCallback PushButtonCallback} interface
      */
      public Icon(String filename) {
	super();
	setIcon(new javax.swing.ImageIcon(filename));
      }


     public void showIcon() {
	enable();
        repaint();
     }
   
     public void hideIcon() {
	disable();
        repaint();
     }
   
 
   
   }
