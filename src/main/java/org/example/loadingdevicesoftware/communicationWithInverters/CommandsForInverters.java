package org.example.loadingdevicesoftware.communicationWithInverters;

public class CommandsForInverters {

    private String messageForInverter;
    private String messageFromInverter;

     public enum messagesForInverter {
        DEVICE_ONLINE_OK, SET_PARAMETER, GET_PARAMETERS, BLINK_LED_START, BLINK_LED_STOP, CHECK_SYNCH,
        CHECK_SWITCH_POS, MASTER_TIME_REQ, CHECK_RESISTANCE, CHECK_RESISTANCE_RESULTS_OK, SCENARIO_PULSE_OPERATION_BEGIN,
        SCENARIO_CURRENT_STABILIZATION_BEGIN, SCENARIO_RESULTS_OK, FAULT_OK, FORCED_STOP
     }

     public enum messagesFromInverter {
        DEVICE_ONLINE, SET_PARAMETER_OK, PARAMETERS, BLINK_LED_START_OK, BLINK_LED_START_ERROR, BLINK_LED_STOP_OK,
        BLINK_LED_STOP_ERROR, CHECK_SYNCH, CHECK_SWITCH_POS_RESP, MASTER_TIME_RESP, CHECK_RESISTANCE_OK,
        CHECK_RESISTANCE_ERROR, CHECK_RESISTANCE_RESULTS, SCENARIO_PULSE_OPERATION_BEGIN_OK,
        SCENARIO_PULSE_OPERATION_BEGIN_ERROR, SCENARIO_CURRENT_STABILIZATION_BEGIN_OK,
        SCENARIO_CURRENT_STABILIZATION_BEGIN_ERROR, SCENARIO_RESULTS, FAULT, FORCED_STOP_OK
     }

     public void constructMessageToInverter(String MACAddress, messagesForInverter messageType, String[] parameters) {
        String messageForInverter = switch (messageType) {
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

        this.messageForInverter = "TO       " + MACAddress + ":\n" + messageForInverter;
    }

    public void constructMessageFromInverter(String MACAddress, messagesFromInverter messageType, String[] parameters) {

        String messageFromInverter;

        messageFromInverter = switch (messageType) {
            case DEVICE_ONLINE -> "DEVICE_ONLINE";
            case SET_PARAMETER_OK -> "SET_PARAMETER_OK";
            case PARAMETERS -> {
                StringBuilder param = new StringBuilder("PARAMETERS(");
                for (int i = 0; i < 20; i++) {
                    if (i < 19) {
                        param.append(parameters[i]).append(",");
                    } else {
                        param.append(parameters[i]).append(")");
                    }
                }
                yield param.toString();
            }
            case BLINK_LED_START_OK -> "BLINK_LED_START_OK";
            case BLINK_LED_START_ERROR -> "BLINK_LED_START_ERROR";
            case BLINK_LED_STOP_OK -> "BLINK_LED_STOP";
            case BLINK_LED_STOP_ERROR -> "BLINK_LED_STOP_ERROR";
            case CHECK_SYNCH -> "CHECK_SYNCH(" + parameters[0] + ")";
            case CHECK_SWITCH_POS_RESP -> "CHECK_SWITCH_POS_RESP(" + parameters[0] + ")";
            case MASTER_TIME_RESP -> "MASTER_TIME_RESP(" + parameters[0] + ")";
            case CHECK_RESISTANCE_OK -> "CHECK_RESISTANCE_OK";
            case CHECK_RESISTANCE_ERROR -> "CHECK_RESISTANCE_ERROR";
            case CHECK_RESISTANCE_RESULTS -> "CHECK_RESISTANCE_RESULTS(" + parameters[0] + "," +
                    parameters[1] + ")";
            case SCENARIO_PULSE_OPERATION_BEGIN_OK -> "SCENARIO_PULSE_OPERATION_BEGIN_OK";
            case SCENARIO_PULSE_OPERATION_BEGIN_ERROR -> "SCENARIO_PULSE_OPERATION_BEGIN_ERROR";
            case SCENARIO_CURRENT_STABILIZATION_BEGIN_OK -> "SCENARIO_CURRENT_STABILIZATION_BEGIN_OK";
            case SCENARIO_CURRENT_STABILIZATION_BEGIN_ERROR -> "SCENARIO_CURRENT_STABILIZATION_BEGIN_ERROR";
            case SCENARIO_RESULTS -> {
                StringBuilder param = new StringBuilder("SCENARIO_RESULTS(");
                for (int i = 0; i < 8; i++) {
                    if (i < 7) {
                        param.append(parameters[i]).append(",");
                    } else {
                        param.append(parameters[i]).append(")");
                    }
                }
                yield param.toString();
            }
            case FAULT -> "FAULT(" + parameters[0]+ ")";
            case FORCED_STOP_OK -> "FORCED_STOP_OK";

        };

        this.messageFromInverter = "FROM    " + MACAddress + ":\n" + messageFromInverter;
    }

    public void sendMessage() {
        System.out.println(this.messageForInverter);
    }

    public String getMessageFromInverter() {
        return "";
    }

    public void readMessageFromInverter() {
        System.out.println(this.messageFromInverter);
    }

}
