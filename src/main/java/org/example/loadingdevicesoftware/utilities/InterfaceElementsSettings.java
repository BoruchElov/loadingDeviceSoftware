package org.example.loadingdevicesoftware.utilities;


/**
 * Класс <b>InterfaceElementsSettings</b> используется для удобной настройки внешнего
 * вида элементов интерфейса в соответствие с корпоративным стилем холдинга Россети
 */
public class InterfaceElementsSettings {

    private final String fontName = "Myriad Pro";

    /**
     * <p>Данные константы обозначают разрешённые в приложении цвета,
     *    соответствующие брендбуку Россетей.</p>
     * <p>Каждый цвет соответствует следующему коду в формате HEX:</p>
     * <ul>
     * <li><b>BLACK</b> (чёрный) <i>#F9AE40</i></li>
     * <li><b>BLUE</b> (синий) <i>#0F5D9C</i></li>
     * <li><b>DARK_BLUE</b> (тёмно-синий) <i>#221E1F</i></li>
     * <li><b>LIGHT_BLUE</b> (голубой) <i>#CFECF8</i></li>
     * <li><b>WHITE</b> (белый) - константа для текста, задаётся не через HEX-код</li>
     * <ul>
     */
    public enum colours {
        BLACK, BLUE, DARK_BLUE, LIGHT_BLUE, WHITE

    }

    /**
     * Метод-конструктор класса <b>InterfaceElementsSettings</b>
     */

    public InterfaceElementsSettings() {}

    public void buttonSettings(colours colourOfBackground, colours colourOfBorder) {
        int min = 7;
        System.out.printf("S = ", min);
    }

}
