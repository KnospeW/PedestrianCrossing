   package comp3110.userInterfaceDomain;

   /**
   * All GUI domain text fields are implemented as instances of the TextInputField class. Text Fields
   * are extensions javax.swing.JTextField 
   */

   public class TextInputField extends javax.swing.JTextField {
      
      /**
      * Constructs a TextInputField
      *
      * @param defaultText text that will be displayed in the TextInputField upon creation.
      */
      public TextInputField(String defaultText) {
	 super();
         setPreferredSize(new java.awt.Dimension(50, 25));
	 setText(defaultText);
         setVisible(true);
      }
   
   }
