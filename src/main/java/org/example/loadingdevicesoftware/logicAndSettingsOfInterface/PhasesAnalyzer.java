package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

public class PhasesAnalyzer {

    public static String[] getPhases(int[] data) {
        int phaseShift = getPhaseShift(data[1], data[3]);
        int feedingWinding = data[8];
        String faultType = getFaultType(data[4], data[5], data[6], data[7]);
        String[] phases = new String[]{"-", "-", "-", "-", "-", "-"};
        if (data[9] == 2) {
            if (faultType.equals("ABCG")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "120";
                phases[3] = Integer.toString(-180 + phaseShift);
                phases[4] = Integer.toString(60 + phaseShift);
                phases[5] = Integer.toString(-60 + phaseShift);
            }
        } else {
            if (faultType.equals("ABCG")) {
                return phases;
            }
            if (faultType.equals("AB-G")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "120";
                phases[3] = "0";
                phases[4] = "-120";
                phases[5] = Integer.toString(-60 + phaseShift);
            }
            if (faultType.equals("A-CG")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "120";
                phases[3] = "0";
                phases[4] = Integer.toString(60 + phaseShift);
                phases[5] = "120";
            }
            if (faultType.equals("-BCG")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "120";
                phases[3] = "0";
                phases[4] = "-120";
                phases[5] = Integer.toString(-60 + phaseShift);
            }
            if (faultType.equals("AB--")) {
                phases[0] = "0";
                phases[1] = "-180";
                phases[2] = "120";
                phases[3] = "0";
                phases[4] = "-180";
                phases[5] = Integer.toString(-60 + phaseShift);
            }
            if (faultType.equals("A-C-")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "-180";
                phases[3] = "0";
                phases[4] = Integer.toString(60 + phaseShift);
                phases[5] = "-180";
            }
            if (faultType.equals("-BC-")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "60";
                phases[3] = Integer.toString(-180 + phaseShift);
                phases[4] = "-120";
                phases[5] = "60";
            }
            if (faultType.equals("A--G")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "120";
                phases[3] = "0";
                phases[4] = Integer.toString(60 + phaseShift);
                phases[5] = Integer.toString(-60 + phaseShift);
            }
            if (faultType.equals("-B-G")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "120";
                phases[3] = Integer.toString(-180 + phaseShift);
                phases[4] = "-120";
                phases[5] = Integer.toString(-60 + phaseShift);
            }
            if (faultType.equals("--CG")) {
                phases[0] = "0";
                phases[1] = "-120";
                phases[2] = "120";
                phases[3] = Integer.toString(-180 + phaseShift);
                phases[4] = Integer.toString(60 + phaseShift);
                phases[5] = "120";
            }
        }
        if (feedingWinding == 2) {
            String[] buffer = new String[]{phases[3], phases[4], phases[5], phases[0], phases[1], phases[2]};
            phases = buffer;
        }
        return phases;
    }

    private static int getPhaseShift(int groupOne, int groupTwo) {

        int phaseOne = groupOne * 30 > 180 ? 360 - groupOne * 30 : -groupOne * 30;
        int phaseTwo = groupTwo * 30 > 180 ? 360 - groupTwo * 30 : -groupTwo * 30;

        return phaseOne - phaseTwo;
    }

    private static String getFaultType(int phaseA, int phaseB, int phaseC, int ground) {
        char one = phaseA == 1 ? 'A' : '-';
        char two = phaseB == 1 ? 'B' : '-';
        char three = phaseC == 1 ? 'C' : '-';
        char four = ground == 1 ? 'G' : '-';
        return new String(new char[]{one, two, three, four});
    }


}
