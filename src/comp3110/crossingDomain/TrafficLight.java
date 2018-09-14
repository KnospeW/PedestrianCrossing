package comp3110.crossingDomain;


import comp3110.mechanismsDomain.*;

public class TrafficLight extends ActiveClass implements TimerConstants {
    public static final int SET_FAIL_MODE_EVENT    = 0;
    public static final int SET_TRAFFIC_MODE_EVENT = 1;
    private static final int DRIVE_TIME_OVER_EVENT  = 2;
    public static final int CROSSING_REQUEST_EVENT = 3;
    private static final int YELLOW_TIME_OVER_EVENT = 4;
    private static final int START_CROSSING_SEQUENCE_EVENT = 5;
    public static final int CROSSING_CLEAR_EVENT = 6;
    private static final int YELLOW_OFF = 7;
    private static final int YELLOW_ON = 8;
    private static final int FLASH_ON = 9;

    private long driveTime = 5l*seconds;
    private long yellowTime = 2l*seconds;
    private long redTime = 5l*seconds;
    private long flashTime = 1l*seconds;

    public static CrossingToHardwareServerInterface hardwareEE;

    private PedestrianLight linkedPedestrainLight;

    private class Green extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light Green procedure called");
            hardwareEE.setGreen();
            ApplicationEventQueue.generateDelayedEvent(driveTime, DRIVE_TIME_OVER_EVENT, null, TrafficLight.this);
        }
    }

    private class Yellow extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light Yellow procedure called");
            hardwareEE.setYellow();
            ApplicationEventQueue.generateDelayedEvent(yellowTime, YELLOW_TIME_OVER_EVENT, null, TrafficLight.this);
        }
    }

    private class Red extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light Red procedure called");
            hardwareEE.setRed();
            ApplicationEventQueue.generateDelayedEvent(redTime, START_CROSSING_SEQUENCE_EVENT, null, TrafficLight.this);
        }
    }

    private class FailMode extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light FailMode procedure called");
            hardwareEE.processFailEvent();
            ApplicationEventQueue.generateEvent(FLASH_ON, null, TrafficLight.this);
        }
    }

    private class YellowOn extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light YellowOn procedure called");
            hardwareEE.setYellow();
            ApplicationEventQueue.generateDelayedEvent(flashTime, YELLOW_OFF, null, TrafficLight.this);
        }
    }

    private class YellowOff extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light YellowOff procedure called");
            hardwareEE.setEmpty();
            ApplicationEventQueue.generateDelayedEvent(flashTime, YELLOW_ON, null, TrafficLight.this);
        }
    }

    private class TrafficStopped extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light TrafficStopped procedure called");
            ApplicationEventQueue.generateEvent(PedestrianLight.ALLOW_CROSSING_EVENT, null, linkedPedestrainLight);
        }
    }

    private class WaitingForCrossingRequest extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light WaitingForCrossingRequest procedure called");
        }
    }

    private class RequestPending extends State {
        public void procedure(Object parameters) {
            Messages.debug("Traffic Light RequestPending procedure called");
        }
    }

    public void linkToPedestrianLight(PedestrianLight thePedestrainLight) {
        linkedPedestrainLight = thePedestrainLight;
    }

    public TrafficLight(CrossingToHardwareServerInterface hardwareEE) {

        this.hardwareEE = hardwareEE;

        FailMode failMode = new FailMode();
        Green green = new Green();
        Red red = new Red();
        Yellow yellow = new Yellow();
        WaitingForCrossingRequest waitingForCrossingRequest = new WaitingForCrossingRequest();
        RequestPending requestPending = new RequestPending();
        TrafficStopped trafficStopped = new TrafficStopped();
        YellowOn yon = new YellowOn();
        YellowOff yoff = new YellowOff();

        addState(green);
        addState(red);
        addState(yon);
        addState(yoff);
        addState(yellow);
        addState(failMode);
        addState(waitingForCrossingRequest);
        addState(requestPending);
        addState(trafficStopped);

        addTransition(new Transition(green,  failMode, SET_FAIL_MODE_EVENT));
        addTransition(new Transition(green, waitingForCrossingRequest,  DRIVE_TIME_OVER_EVENT));
        addTransition(new Transition(waitingForCrossingRequest, yellow,  CROSSING_REQUEST_EVENT));
        addTransition(new Transition(green, requestPending, CROSSING_REQUEST_EVENT));
        addTransition(new Transition(requestPending, yellow, DRIVE_TIME_OVER_EVENT));
        addTransition(new Transition(yellow, red, YELLOW_TIME_OVER_EVENT));
        addTransition(new Transition(red, trafficStopped, START_CROSSING_SEQUENCE_EVENT));
        addTransition(new Transition(trafficStopped, green, CROSSING_CLEAR_EVENT));
        addTransition(new Transition(failMode, green, SET_TRAFFIC_MODE_EVENT));
        addTransition(new Transition(yon, yoff, YELLOW_OFF));
        addTransition(new Transition(yoff, yon, YELLOW_ON));
        addTransition(new Transition(yon, green, SET_TRAFFIC_MODE_EVENT));
        addTransition(new Transition(yoff, green, SET_TRAFFIC_MODE_EVENT));
        addTransition(new Transition(failMode, yon, FLASH_ON));

        setInitialState(green);
    }

}
