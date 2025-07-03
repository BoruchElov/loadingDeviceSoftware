package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import lombok.Setter;

public class Buffer {

    @Getter
    @Setter
    private static String previousPage;


    @Getter @Setter
    private static boolean flagForDifProtection = false;
}
