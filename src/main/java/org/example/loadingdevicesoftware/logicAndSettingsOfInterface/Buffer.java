package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import lombok.Setter;
import org.example.loadingdevicesoftware.pagesControllers.*;

public class Buffer {

    @Getter
    @Setter
    private static String previousPage;
    //Флаги для форм по прохождению проверки
    @Getter @Setter
    private static boolean flagForSwitcherPage = false;
    @Getter @Setter
    private static boolean flagForProtectionPage = false;
    @Getter @Setter
    private static boolean flagForTransformerPage = false;
    @Getter @Setter
    private static boolean flagForComTradePage = false;
    @Getter @Setter
    private static boolean flagForDifProtection = false;
    @Getter @Setter
    private static boolean flagForHandControlPage = false;
}
