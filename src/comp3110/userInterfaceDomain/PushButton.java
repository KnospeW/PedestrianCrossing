   package comp3110.userInterfaceDomain;

   /**
   * All GUI domain buttons are implemented as instances of the PushButton class. Push Buttons
   * are implemented as javax.swing.JButtons and are created with an instance of a class
   * that implements the {@link comp3110.userInterfaceDomain.PushButtonCallback PushButtonCallback}
   * interface. When a Push Button is clicked, the 'buttonPressed()' method of this object is called. 
   */

   public class PushButton extends javax.swing.JButton {
   
      private PushButtonCallback callback;
   
      /**
      * Constructs a PushButton
      *
      * @param label the text to be displayed on the button
      * @param callback an object that implements the {@link comp3110.userInterfaceDomain.PushButtonCallback PushButtonCallback} interface
      */
      public PushButton(String label, final PushButtonCallback callback) {
         super(label);
         setSize(100, 100);
         this.callback = callback;
      
         addActionListener(
               new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent e) {
                     callback.buttonPressed();
                  }
               });
         setVisible(true);
      }

   
   }
