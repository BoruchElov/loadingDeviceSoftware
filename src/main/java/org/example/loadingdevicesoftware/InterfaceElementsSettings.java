package org.example.loadingdevicesoftware;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Класс <b>InterfaceElementsSettings</b> используется для удобной настройки внешнего
 * вида элементов интерфейса в соответствие с корпоративным стилем холдинга Россети
 */
public class InterfaceElementsSettings {

    public InterfaceElementsSettings() {}

    public enum Background {
        BLUE(ApplicationConstants.colours.BLUE), 
        LIGHT_BLUE(ApplicationConstants.colours.LIGHT_BLUE);

        private final ApplicationConstants.colours colours;

        Background(ApplicationConstants.colours colours) {
            this.colours = colours;
        }
        public ApplicationConstants.colours getColours() {
            return colours;
        }
    }

    public void getBlackMenuButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.BLACK, 26, 0, imageView, ApplicationConstants.BLACK_BUTTON,
                button, 140, 64, true, "МЕНЮ");
    }

    public void getWhiteMenuButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.WHITE, 26, 0, imageView, ApplicationConstants.WHITE_BUTTON,
                button, 140, 64, true, "МЕНЮ");
    }

    public void getBlackStartButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.BLACK, 26, 0, imageView, ApplicationConstants.BLACK_BUTTON,
                button, 140, 64, true, "ПУСК");
    }

    public void getWhiteStartButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.WHITE, 26, 0, imageView, ApplicationConstants.WHITE_BUTTON,
                button, 140, 64, true, "ПУСК");
    }

    public void getBlackCancelButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.BLACK, 26, 0, imageView, ApplicationConstants.BLACK_BUTTON_LONG,
                button, 210, 64, true, "ОТМЕНА");
    }

    public void getWhiteCancelButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.WHITE, 26, 0, imageView, ApplicationConstants.WHITE_BUTTON_LONG,
                button, 210, 64, true, "ОТМЕНА");
    }

    public void getBlackReportButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.BLACK, 26, 0, imageView, ApplicationConstants.BLACK_BUTTON_LONG,
                button, 210, 64, true, "ПРОТОКОЛ");
    }

    public void getWhiteReportButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.WHITE, 26, 0, imageView, ApplicationConstants.WHITE_BUTTON_LONG,
                button, 210, 64, true, "ПРОТОКОЛ");
    }

    public void getBlackSaveButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.BLACK, 26, 0, imageView, ApplicationConstants.BLACK_BUTTON_LONG,
                button, 210, 64, true, "СОХРАНИТЬ");
    }

    public void getWhiteSaveButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.WHITE, 26, 0, imageView, ApplicationConstants.WHITE_BUTTON_LONG,
                button, 210, 64, true, "СОХРАНИТЬ");
    }
    public void getBlackDirectoryButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.BLACK, 26, 0, imageView, ApplicationConstants.BLACK_BUTTON_LONG,
                button, 210, 64, true, "ДИРЕКТОРИЯ");
    }

    public void getWhiteDirectoryButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.WHITE, 26, 0, imageView, ApplicationConstants.WHITE_BUTTON_LONG,
                button, 210, 64, true, "ДИРЕКТОРИЯ");

    }
    public void getBlackClearButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.BLACK, 26, 0, imageView, ApplicationConstants.BLACK_BUTTON_LONG,
                button, 210, 64, true, "ОЧИСТИТЬ");
    }

    public void getWhiteClearButton(ButtonBase button, ImageView imageView, Background background) {
        buttonSettings(background.getColours(), background.getColours(), 0, 17, 0,
                ApplicationConstants.colours.WHITE, 26, 0, imageView, ApplicationConstants.WHITE_BUTTON_LONG,
                button, 210, 64, true, "ОЧИСТИТЬ");

    }




    /**
     * <p>Данная версия метода <code>buttonsSettings</code> предназначена для настройки объектов типа <code>Button</code>
     * и <code>ToggleButton</code> в случае, если на кнопке необходимо расположить только изображение.</p>
     *
     * @param colourOfBackground  цвет фона
     * @param colourOfBorder      цвет границы
     * @param borderWidth         ширина границы
     * @param backgroundRadius    радиус закругления фона
     * @param borderRadius        радиус закругления границы
     * @param colourOfText        цвет шрифта
     * @param padding             отступ текста на кнопке
     * @param backgroundImageView объект для расположения картинки на кнопке
     * @param backgroundImage     картинка, расположенная на кнопке
     * @param button              объект кнопки
     * @param fitWidth            ширина картинки
     * @param fitHeight           высота картинки
     * @param isVisible настройка видимости изображения на кнопке
     */
    public void buttonSettings(ApplicationConstants.colours colourOfBackground,
                               ApplicationConstants.colours colourOfBorder,
                               int borderWidth, int backgroundRadius,
                               int borderRadius, ApplicationConstants.colours colourOfText,
                               int padding, ImageView backgroundImageView, Image backgroundImage,
                               ButtonBase button, int fitWidth, int fitHeight, boolean isVisible) {

        backgroundImageView.setImage(backgroundImage);
        backgroundImageView.setFitWidth(fitWidth);
        backgroundImageView.setFitHeight(fitHeight);
        backgroundImageView.setVisible(isVisible);

        String backgroundColour = setColour(colourOfBackground, true);
        String borderColour = setColour(colourOfBorder, true);

        String widthOfBorder = setIntToText(borderWidth);
        String radiusOfBackground = setIntToText(backgroundRadius);
        String radiusOfBorder = setIntToText(borderRadius);

        String textColour = setColour(colourOfText, false);

        String textPadding = " " + padding + ";";

        button.setStyle("-fx-background-color:" + backgroundColour +
                "-fx-border-color:" + borderColour +
                "-fx-border-width:" + widthOfBorder +
                "-fx-background-radius:" + radiusOfBackground +
                "-fx-border-radius:" + radiusOfBorder +
                "-fx-text-fill:" + textColour +
                "-fx-padding:" + textPadding +
                "-fx-background-insets: 0; " +
                "-fx-border-insets: 0; ");


    }

    /**
     * <p>Данная версия метода <code>buttonSettings</code> предназначена для настройки объектов типа <code>Button</code>
     *    и <code>ToggleButton</code> в случае, если на кнопке необходимо расположить только текст.</p>
     *
     * @param colourOfBackground цвет фона
     * @param colourOfBorder цвет границы
     * @param borderWidth ширина границы
     * @param backgroundRadius радиус закругления фона
     * @param borderRadius радиус закругления границы
     * @param colourOfText цвет шрифта
     * @param fontSize размер шрифта
     * @param padding отступ текста на кнопке
     * @param button объект кнопки
     */
    public void buttonSettings(ApplicationConstants.colours colourOfBackground,
                                      ApplicationConstants.colours colourOfBorder,
                                      int borderWidth, int backgroundRadius,
                                      int borderRadius, ApplicationConstants.colours colourOfText,
                                      int fontSize, int padding, ButtonBase button) {

        String backgroundColour = setColour(colourOfBackground, true);
        String borderColour = setColour(colourOfBorder, true);

        String widthOfBorder = setIntToText(borderWidth);
        String radiusOfBackground = setIntToText(backgroundRadius);
        String radiusOfBorder = setIntToText(borderRadius);

        String textColour = setColour(colourOfText, false);
        String sizeOfFont = setIntToText(fontSize);
        String textPadding = " " + padding + ";";

        button.setStyle("-fx-background-color:" + backgroundColour + // Цвет фона
                "-fx-border-color:" + borderColour + // Цвет границы
                "-fx-border-width:" + widthOfBorder + // Ширина границы
                "-fx-background-radius:" + radiusOfBackground + // Закругление фона
                "-fx-border-radius:" + radiusOfBorder + // Закругление границы
                "-fx-text-fill:" + textColour +   // Цвет текста
                "-fx-font-size:" + sizeOfFont +        // Размер текста
                "-fx-font-family: " + ApplicationConstants.FONT_NAME + "; " +    // Шрифт текста
                "-fx-padding:" + textPadding + //Величина отступа
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
    }

    /**
     * <p>Данная версия метода <code>buttonsSettings</code> предназначена для настройки объектов типа <code>Button</code>
     * и <code>ToggleButton</code> в случае, если на кнопке необходимо расположить и изображение и текст.</p>
     *
     * @param colourOfBackground  цвет фона
     * @param colourOfBorder      цвет границы
     * @param borderWidth         ширина границы
     * @param backgroundRadius    радиус закругления фона
     * @param borderRadius        радиус закругления границы
     * @param colourOfText        цвет шрифта
     * @param fontSize размер шрифта
     * @param padding             отступ текста на кнопке
     * @param backgroundImageView объект для расположения картинки на кнопке
     * @param backgroundImage     картинка, расположенная на кнопке
     * @param button              объект кнопки
     * @param fitWidth            ширина картинки
     * @param fitHeight           высота картинки
     * @param isVisible настройка видимости изображения на кнопке
     * @param text текст на кнопке
     */
    public void buttonSettings(ApplicationConstants.colours colourOfBackground,
                                      ApplicationConstants.colours colourOfBorder,
                                      int borderWidth, int backgroundRadius,
                                      int borderRadius, ApplicationConstants.colours colourOfText,
                                      int fontSize, int padding, ImageView backgroundImageView,
                                      Image backgroundImage, ButtonBase button, int fitWidth, int fitHeight,
                                      boolean isVisible, String text) {

        backgroundImageView.setImage(backgroundImage);
        backgroundImageView.setFitWidth(fitWidth);
        backgroundImageView.setFitHeight(fitHeight);
        backgroundImageView.setVisible(isVisible);
        button.setText(text);

        String backgroundColour = setColour(colourOfBackground, true);
        String borderColour = setColour(colourOfBorder, true);

        String widthOfBorder = setIntToText(borderWidth);
        String radiusOfBackground = setIntToText(backgroundRadius);
        String radiusOfBorder = setIntToText(borderRadius);

        String textColour = setColour(colourOfText, false);
        String sizeOfFont = setIntToText(fontSize);

        String textPadding = " " + padding + ";";

        button.setStyle("-fx-background-color:" + backgroundColour + // Цвет фона
                "-fx-border-color:" + borderColour + // Цвет границы
                "-fx-border-width:" + widthOfBorder + // Ширина границы
                "-fx-background-radius:" + radiusOfBackground + // Закругление фона
                "-fx-border-radius:" + radiusOfBorder + // Закругление границы
                "-fx-text-fill:" + textColour +   // Цвет текста
                "-fx-font-size:" + sizeOfFont +        // Размер текста
                "-fx-font-family: " + ApplicationConstants.FONT_NAME + "; " +    // Шрифт текста
                "-fx-padding:" + textPadding + //Величина отступа
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");


    }

    /**
     * <p>Метод <code>textFieldSettings</code> предназначен для настройки объектов типа <code>TextField</code>
     *    и <code>ToggleButton</code>.</p>
     * @param colourOfBackground цвет фона
     * @param colourOfBorder цвет границы
     * @param borderWidth ширина границы
     * @param backgroundRadius радиус закругления фона
     * @param borderRadius радиус закругления границы
     * @param colourOfText цвет шрифта
     * @param fontSize размер шрифта
     * @param padding отступ текста на кнопке
     * @param textField объект текстового поля
     * @param prompt текст по умолчанию
     */
    public void textFieldSettings(ApplicationConstants.colours colourOfBackground,
                                  ApplicationConstants.colours colourOfBorder,
                                  int borderWidth, int backgroundRadius,
                                  int borderRadius, ApplicationConstants.colours colourOfText,
                                  int fontSize, int padding, TextField textField, String prompt) {

        String backgroundColour = setColour(colourOfBackground, true);
        String borderColour = setColour(colourOfBorder, true);

        String widthOfBorder = setIntToText(borderWidth);
        String radiusOfBackground = setIntToText(backgroundRadius);
        String radiusOfBorder = setIntToText(borderRadius);

        String textColour = setColour(colourOfText, false);
        String sizeOfFont = setIntToText(fontSize);
        String textPadding = " " + padding + ";";

        textField.setStyle("-fx-background-color:" + backgroundColour + // Цвет фона
                "-fx-border-color:" + borderColour + // Цвет границы
                "-fx-border-width:" + widthOfBorder + // Ширина границы
                "-fx-background-radius:" + radiusOfBackground + // Закругление фона
                "-fx-border-radius:" + radiusOfBorder + // Закругление границы
                "-fx-text-fill:" + textColour +   // Цвет текста
                "-fx-font-size:" + sizeOfFont +        // Размер текста
                "-fx-font-family: " + ApplicationConstants.FONT_NAME + "; " +    // Шрифт текста
                "-fx-padding:" + textPadding + //Величина отступа
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
        //Задание текста по умолчанию
        textField.setPromptText(prompt);
        //Выравнивание по центру
        textField.setAlignment(javafx.geometry.Pos.CENTER);
        //Код для отключения мигания каретки при вводе
        textField.setOnAction(event -> {
            textField.getParent().requestFocus();
        });
    }

    public void comboBoxSettings (ApplicationConstants.colours colourOfBackground,
                               ApplicationConstants.colours colourOfBorder,
                               int borderWidth, int backgroundRadius,
                               int borderRadius, ApplicationConstants.colours colourOfText,
                               int fontSize, int padding, ComboBox comboBox) {

        String backgroundColour = setColour(colourOfBackground, true);
        String borderColour = setColour(colourOfBorder, true);

        String widthOfBorder = setIntToText(borderWidth);
        String radiusOfBackground = setIntToText(backgroundRadius);
        String radiusOfBorder = setIntToText(borderRadius);

        String textColour = setColour(colourOfText, false);
        String sizeOfFont = setIntToText(fontSize);
        String textPadding = " " + padding + ";";

        comboBox.setStyle("-fx-background-color:" + backgroundColour + // Цвет фона
                "-fx-border-color:" + borderColour + // Цвет границы
                "-fx-border-width:" + widthOfBorder + // Ширина границы
                "-fx-background-radius:" + radiusOfBackground + // Закругление фона
                "-fx-border-radius:" + radiusOfBorder + // Закругление границы
                "-fx-text-fill:" + textColour +   // Цвет текста
                "-fx-font-size:" + sizeOfFont +        // Размер текста
                "-fx-font-family: " + ApplicationConstants.FONT_NAME + "; " +    // Шрифт текста
                "-fx-padding:" + textPadding + //Величина отступа
                "-fx-background-insets: 0; " +    // Убираем стандартные отступы JavaFX
                "-fx-border-insets: 0; ");
    }

    /**
     * <p>Метод <code>setColour</code> носит вспомогательный характер и используется для обеспечения логики
     * функционирования класса <code>InterfaceElementsSettings</code>.<p/>
     * <p>Преобразует объект типа <code>enum</code> в текстовый объект нужного вида (цвет в кодировке HEX или в виде
     * названия цвета на английском).</p>
     *
     * @param colours цвет в формате <code>enum</code>
     * @param isHex   переменная, определяющая вид возвращаемого текста. Если <code>true</code> - кодировка HEX,
     *                <code>false</code> - просто слово.
     * @return текстовый объект нужного вида (цвет в кодировке HEX или в виде
     * названия цвета на английском).
     */
    private String setColour(ApplicationConstants.colours colours, boolean isHex) {
        if (isHex) {
            return " " + switch (colours) {
                case BLACK -> ApplicationConstants.BLACK_HEX;
                case BLUE -> ApplicationConstants.BLUE_HEX;
                case ORANGE -> ApplicationConstants.ORANGE_HEX;
                case LIGHT_BLUE -> ApplicationConstants.LIGHT_BLUE_HEX;
                default -> ApplicationConstants.WHITE_HEX;
            } + "; ";
        } else {
            return " " + switch (colours) {
                case BLACK -> ApplicationConstants.BLACK_WORD;
                default -> ApplicationConstants.WHITE_WORD;
            } + "; ";
        }
    }

    /**
     * <p>Метод <code>setIntToText</code> носит вспомогательный характер и используется для обеспечения логики
     * функционирования класса <code>InterfaceElementsSettings</code>.<p/>
     * <p>Преобразует объект типа <code>int</code> в текстовый объект нужного вида (такое же число, но с
     * нужным форматированием).</p>
     *
     * @return текстовый объект нужного вида (число, но с нужным форматированием).
     */
    private String setIntToText(int value) {
        return " " + value + "px; ";
    }


}
