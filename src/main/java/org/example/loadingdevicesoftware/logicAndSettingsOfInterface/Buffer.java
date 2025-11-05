package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import lombok.Getter;
import lombok.Setter;

public class Buffer {
    //Данная переменная не используется в новой структуре ПО, её нужно будет удалить в будущем
    //TODO удалить в будущем
    @Getter
    @Setter
    private static String previousPage;


    @Getter @Setter
    private static boolean flagForDifProtection = false;
}
