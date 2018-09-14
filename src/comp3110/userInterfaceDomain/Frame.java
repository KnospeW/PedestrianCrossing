   package comp3110.userInterfaceDomain;

   import java.awt.Component;
   import java.awt.FlowLayout;

/**
* All User Interface domain application windows are implemented as classes which extend the Frame class.
* The Frame class is implemented as a {@link javax.swing.JFrame javax.swing.JFrame} with an associated window event listener which closes
* the entire application when the window is closed. The layout of the frame is based on
* {@link java.awt.FlowLayout java.awt.FlowLayout}.
*/

   public class Frame extends javax.swing.JFrame {
   
       public Frame(String title, int width, int height) {
         super(title);
         setSize(width, height);
         setVisible(true);
         getContentPane().setLayout(new FlowLayout());
      
         addWindowListener(
               new java.awt.event.WindowAdapter() {
                  public void windowClosing(java.awt.event.WindowEvent e) {
                     System.exit(0);
                  }
               });
      }
   
      /**
      * adds a specified component to the frame
      *
      * @param component the component (a PushButton or TextInputField) to be added to the frame
      * @param position  the position of the component in the border layout (NORTH, SOUTH, EAST, WEST or CENTER)
      */
      public void addComponent(Component component) {
         getContentPane().add(component);
      }
   
      public void refresh() {
         pack();
      }
   }
