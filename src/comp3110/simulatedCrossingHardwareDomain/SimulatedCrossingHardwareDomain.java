package comp3110.simulatedCrossingHardwareDomain;

import comp3110.userInterfaceDomain.*;
import comp3110.mechanismsDomain.*;
import comp3110.crossingDomain.CrossingToHardwareClientInterface;
import comp3110.crossingDomain.CrossingToHardwareServerInterface;

/**
 *
 * This is the Domain Class (see the {@link comp3110.mechanismsDomain.Domain Domain}) for one possible CrossingHardware Domain. It implements the
 * {@link comp3110.crossingDomain.CrossingToHardwareServerInterface CrossingToHardwareServerInterface}
 * by simulating the crossing hardware. It creates a GUI 
 * which can be used to simulate the generation of hardware events such as crossingRequest. This implementation of the 
 * hardware domain uses text messages to
 * decribe the crossing's manipulation of hardware devices (eg. controlling the traffic
 * light). See the  <A HREF="../../src-html/comp3110/crossingDomain/TrafficLight.html">source code</A>
 * for a detailed decription of this class.
 * 
 */

public class SimulatedCrossingHardwareDomain extends Domain implements CrossingToHardwareServerInterface {

  // The following private fields will hold the various elements that make up
  // the GUI
  //
  private Frame          theFrame;
  private PushButton     crossingRequestButton;
  private PushButton     setFailModeButton;
  private PushButton     setTrafficModeButton;

  private Icon     walkLight;
  private Icon     dontWalkLight;
  private Icon     greenLight;
  private Icon     yellowLight;
  private Icon     redLight;


  // Each GUI button is associated with an instance of a callback class.
  // A callback class contains a method (buttonPressed) which is called when  a PushButton
  // is clicked
  //
  // The following callback is used to generate a 'crossingRequest' event when 
  // the associated Push Button is clicked.
  //
  private class CrossingRequestCallback implements PushButtonCallback {
    
    // buttonPressed is called when a button is pressed
    //
    public void buttonPressed() {
      // call the crossing client interface to get the crossing to process the event
      crossingEE.processCrossingRequestEvent();
    }
  }
  
  private class SetFailModeCallback implements PushButtonCallback {
    public void buttonPressed() {
      crossingEE.processSetFailModeEvent();
    }
  }
  
  private class SetTrafficModeCallback implements PushButtonCallback {
    public void buttonPressed() {
      crossingEE.processSetTrafficModeEvent();
    }
  }
  
  private class EmptyCallback implements PushButtonCallback {
    public void buttonPressed() {
    }
  }
  
  
  
  // Each domain class has an initialise() method which is called by the
  // application's main program.
  //
  // The initialise() method for a domain with a GUI is required to instantiate
  // each of the elements to be displayed on the GUI. These instance are then added to the GUI
  // frame.
  //
  public void initialise() {
    Messages.debug("SimulatedCrossingHardwareDomain initialising");
    
    //create the GUI frame and the elements that will be displayed on it
    //
    theFrame              = new Frame("COMP3110 Pedestrian Crossing", 800, 50);
    crossingRequestButton = new PushButton("Crossing Request", new CrossingRequestCallback());
    setFailModeButton     = new PushButton("Set Fail Mode", new SetFailModeCallback());
    setTrafficModeButton  = new PushButton("set Traffic Mode", new SetTrafficModeCallback());
    
    walkLight      = new Icon("images/walk.png");
    dontWalkLight  = new Icon("images/dontWalk.png");
    greenLight     = new Icon("images/green.png");
    yellowLight    = new Icon("images/yellow.png");
    redLight       = new Icon("images/red.png");

    // Add the GUI elements to the frame
    //
    theFrame.addComponent(crossingRequestButton);
    theFrame.addComponent(setFailModeButton);
    theFrame.addComponent(setTrafficModeButton);
    
    theFrame.addComponent(walkLight);
    theFrame.addComponent(dontWalkLight);
    theFrame.addComponent(greenLight);
    theFrame.addComponent(yellowLight);
    theFrame.addComponent(redLight);


    // Refresh the frame to lay it out properly
    //
    theFrame.refresh();
    Messages.debug("SimulatedCrossingHardwareDomain initialised");
  }
  


  // This field connects this domain (simulated Crossing Hardware domain) to
  // the crossing domain. The setCrossingEE() method below is used by the application's
  // main program to set the Crossing domain with which the Simulated Crossing Hardare
  // domain will communicate.
  //
  private CrossingToHardwareClientInterface crossingEE;
  
  public void setCrossingEE(CrossingToHardwareClientInterface crossingEE) {
    this.crossingEE = crossingEE;
  }
  


  // The following methods implement the CrossingToHardwareServerInterface
  // which is used by the Crossing domain (an external entity) to communicate with the
  // Simulated Crossing Hardware domain. Each of the following methods represent a bridge operation
  // used by the crossing external entity.
  //
  // In this simulated hardware domain the bridge operations are implemented by displaying
  // a text message describing the nature of each operation
  //
  // If this was a real hardware domain, these methods would control real hardware devices.
  //

  public void setGreen() {
    TextDisplay.show(">> Simulated Hardware Domain: SET GREEN");
    greenLight.showIcon();
    yellowLight.hideIcon();
    redLight.hideIcon();
  }
  
  public void setYellow() {
    TextDisplay.show(">> Simulated Hardware Domain: SET YELLOW");
    greenLight.hideIcon();
    yellowLight.showIcon();
    redLight.hideIcon();
  }
  
  public void setRed() {
    TextDisplay.show(">> Simulated Hardware Domain: SET RED");
    greenLight.hideIcon();
    yellowLight.hideIcon();
    redLight.showIcon();
  }

  public void setEmpty() {
    TextDisplay.show(">> Simulated Hardware Domain: EMPTY");
    greenLight.hideIcon();
    yellowLight.hideIcon();
    redLight.hideIcon();
  }
  

  // Event processing
  //

  public void processWalkOnEvent() {
    TextDisplay.show(">> Simulated Hardware Domain: process WALK ON event");
    walkLight.showIcon();
  }

  public void processWalkOffEvent() {
    TextDisplay.show(">> Simulated Hardware Domain: process WALK OFF event");
    walkLight.hideIcon();
  }

  public void processDontWalkOnEvent() {
    TextDisplay.show(">> Simulated Hardware Domain: process DON'T WALK ON event");
    dontWalkLight.showIcon();
  }

  public void processDontWalkOffEvent() {
    TextDisplay.show(">> Simulated Hardware Domain: process DON'T WALK OFF event");
    dontWalkLight.hideIcon();
  }

  public void processFailEvent() {
    TextDisplay.show(">> Simulated Hardware Domain: process FAIL event");
    dontWalkLight.showIcon();
    walkLight.hideIcon();
    setRed();
  }
  
}





