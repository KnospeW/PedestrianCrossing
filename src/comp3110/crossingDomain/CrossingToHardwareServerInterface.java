package comp3110.crossingDomain;

/**
 *
 * Defines the interface that the Crossing domain uses to communicate with a Crossing
 * Hardware domain. It represents a set of assumptions that the Crossing domain makes about some
 * crossing hardware domain. 
 *
 * This interface is part of the specification of an Explicit Bridge between the Crossing Domain and
 * some other domain that provides access to the crossing hardware.
 */

 
// This is the server side of the explicit bridge between the crossing and
// the Crossing hardware domains. This interface declares those methods by which the Crossing
// domain can communicate with the hardware domain
// 
public interface CrossingToHardwareServerInterface {

 /**
  * Used by the Crossing domain to the real world that the walkOn event needs to be processed
  *
  */
  public void processWalkOnEvent();

 /**
  * Used by the Crossing domain to the real world that the walkOff event needs to be processed
  *
  */
  public void processWalkOffEvent();

 /**
  * Used by the Crossing domain to the real world that the dontWalkOn event needs to be processed
  *
  */
  public void processDontWalkOnEvent();

 /**
  * Used by the Crossing domain to the real world that the dontWalkOff event needs to be processed
  *
  */
  public void processDontWalkOffEvent();

 /**
  * Used by the Crossing domain to the real world that the fail event needs to be processed
  *
  */
  public void processFailEvent();


 /**
  * Bridge operation used by the Crossing domain to control the real world traffic light
  */
  public void setGreen();

 public void setEmpty();

 /**
  * Bridge operation used by the Crossing domain to control the real world traffic light
  */
  public void setYellow();

 /**
  * Bridge operation used by the Crossing domain to control the real world traffic light
  */
  public void setRed();

}
