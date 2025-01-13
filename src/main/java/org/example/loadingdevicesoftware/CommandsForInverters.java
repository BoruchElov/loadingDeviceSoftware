package org.example.loadingdevicesoftware;

public class CommandsForInverters {

    private String messageFromControl;
    private String messageFromInverter;

     public enum messagesFromControl {
        DEVICE_ONLINE_OK, SET_PARAMETER, GET_PARAMETERS, BLINK_LED_START, BLINK_LED_STOP, CHECK_SYNCH,
        CHECK_SWITCH_POS, MASTER_TIME_REQ, CHECK_RESISTANCE, CHECK_RESISTANCE_RESULTS_OK, SCENARIO_PULSE_OPERATION_BEGIN,
        SCENARIO_CURRENT_STABILIZATION_BEGIN, SCENARIO_RESULTS_OK, FAULT_OK, FORCED_STOP
    }

     public enum MessagesInverter {
        DEVICE_ONLINE, SET_PARAMETER_OK, PARAMETERS, BLINK_LED_START_OK, BLINK_LED_START_ERROR, BLINK_LED_STOP_OK,
        BLINK_LED_STOP_ERROR, CHECK_SYNCH, CHECK_SWITCH_POS_RESP, MASTER_TIME_RESP, CHECK_RESISTANCE_OK,
        CHECK_RESISTANCE_ERROR, CHECK_RESISTANCE_RESULTS, SCENARIO_PULSE_OPERATION_BEGIN_OK,
        SCENARIO_PULSE_OPERATION_BEGIN_ERROR, SCENARIO_CURRENT_STABILIZATION_BEGIN_OK,
        SCENARIO_CURRENT_STABILIZATION_BEGIN_ERROR, SCENARIO_RESULTS, FAULT, FORCED_STOP_OK
        }

    //[MAC, message, parameters]
    public void constructMessage(String MACAddress, messagesFromControl messageType, String[] parameters) {

        String messageForInverter;

        messageForInverter = switch (messageType) {
            case DEVICE_ONLINE_OK -> "DEVICE_ONLINE_OK";
            case GET_PARAMETERS -> "GET_PARAMETERS";
            case BLINK_LED_START -> "BLINK_LED_START";
            case BLINK_LED_STOP -> "BLINK_LED_STOP";
            case CHECK_SYNCH -> "CHECK_SYNCH";
            case CHECK_SWITCH_POS -> "CHECK_SWITCH_POS";
            case MASTER_TIME_REQ -> "MASTER_TIME_REQ";
            case CHECK_RESISTANCE_RESULTS_OK -> "CHECK_RESISTANCE_RESULTS_OK";
            case SCENARIO_RESULTS_OK -> "SCENARIO_RESULTS_OK";
            case FAULT_OK -> "FAULT_OK";
            case FORCED_STOP -> "FORCED_STOP";
            case SET_PARAMETER -> "SET_PARAMETER" + "(" + parameters[0] + "," + parameters[1] + ")";
            case CHECK_RESISTANCE -> "CHECK_RESISTANCE" + "(" + parameters[0] + "," + parameters[1] + "," +
                    parameters[2] + "," + parameters[3] +  ")";
            case SCENARIO_PULSE_OPERATION_BEGIN -> "SCENARIO_PULSE_OPERATION_BEGIN" + "(" + parameters[0] + ","
                    + parameters[1] + "," + parameters[2] + "," + parameters[3] +  ")";
            case SCENARIO_CURRENT_STABILIZATION_BEGIN -> "SCENARIO_CURRENT_STABILIZATION_BEGIN" + "(" + parameters[0]
                    + "," + parameters[1] + "," + parameters[2] + "," + parameters[3] +  ")";
        };

        this.messageFromControl = messageForInverter;
    }

    public void sendMessage() {
        System.out.println(this.messageFromControl);
    }

    public String getMessageFromInverter() {
        return "";
    }

    public void readMessageFromInverter() {
        System.out.println(this.messageFromInverter);
    }

    private String returnMessage(int a) {
        return Integer.toString(a);
    }
    private String returnMessage(int a, int b) {
        return Integer.toString(a + b);
    }



}
