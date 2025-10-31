package org.example.loadingdevicesoftware.pagesControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.example.loadingdevicesoftware.logicAndSettingsOfInterface.FunForScenariev.Check_Condition_Start;


class ScreensController extends BasicController {

    boolean[] flags;                        //Массив для хранения булевых значений для пользовательских решений

    @FXML
    SimpleButton clearButton;
    @FXML
    SimpleButton menuButton;
    @FXML
    SimpleButton startButton;

    @FXML
    SimpleTextField nameTextField;
    @FXML
    SimpleTextField objectTextField;


    @FXML
    public void initialize() {
        super.initialize();
        int i = 0;
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof SimpleButton || node instanceof ButtonWithPicture) {
                i++;
            }
        }
        flags = new boolean[i];
        Arrays.fill(flags, false);
        imageView.setImage(ApplicationConstants.NEW_BACKGROUND);
        //Настройка текстовых полей для ввода ФИО и названия объекта
        objectTextField.setup("Название объекта", SimpleTextField.Sizes.LARGE);
        objectTextField.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        nameTextField.setup("Ф.И.О. исполнителя", SimpleTextField.Sizes.LARGE);
        nameTextField.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        AnchorPane.setLeftAnchor(objectTextField, 50.0);
        AnchorPane.setTopAnchor(objectTextField, 540.);
        AnchorPane.setLeftAnchor(nameTextField, 50.0);
        AnchorPane.setTopAnchor(nameTextField, 600.);
        //Настройка положения кнопок в нижней части приложения
        AnchorPane.setTopAnchor(menuButton, 695.0);
        AnchorPane.setLeftAnchor(menuButton, 50.0);
        AnchorPane.setTopAnchor(startButton, 695.0);
        AnchorPane.setLeftAnchor(startButton, 1030.0);
        AnchorPane.setTopAnchor(clearButton, 695.0);
        AnchorPane.setLeftAnchor(clearButton, 770.0);
        //Визуальная настройка и привязка метода для кнопки МЕНЮ
        menuButton.setup(SimpleButton.Presets.MENU);
        menuButton.setActualStatus(Changeable.Status.NORMAL);
        menuButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        startButton.setup(SimpleButton.Presets.START);
        startButton.setActualStatus(Changeable.Status.NORMAL);
        startButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
                PagesBuffer.savePage(this);

                Check_Condition_Start();            //метод запускающий все проверки TODO:p1 Необходимо исправить всю логику проверки и как у нас обновляется орма
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clearButton.setup(SimpleButton.Presets.CLEAR);
        clearButton.setActualStatus(Changeable.Status.NORMAL);
        clearButton.setOnAction(event -> {
            clearAll(this);
        });
    }

    //Метод по очистке всех элементов окна приложения
    public void clearAll(Object controller) {
        for (Node child : this.anchorPane.getChildren()) {
            if (child instanceof Changeable changeable) {
                changeable.setActualStatus(Changeable.Status.NORMAL);
                changeable.changePosition(0);
            }
        }
        Arrays.fill(flags, false);
        if (controller instanceof Configurable object) {
            object.changeConfiguration(new ActionEvent());
        }
    }

    //Метод по блокировке всех элементов окна приложения
    public void lockAll(Node... excludedNodes) {
        // Создаем множество для быстрой проверки исключений
        Set<Node> excludedSet = new HashSet<>(Arrays.asList(excludedNodes));

        for (Node child : this.anchorPane.getChildren()) {
            if (child instanceof Changeable changeable) {
                // Блокируем только если элемент НЕ в списке исключений
                if (!excludedSet.contains(child)) {
                    changeable.setActualStatus(Changeable.Status.LOCKED);
                }
            }
        }
    }

    //Метод по разблокировке всех элементов окна приложения
    public void unlockAll(Node... children) {
        if (children.length == 0) {
            for (Node child : (this.anchorPane.getChildren())) {
                if (child instanceof Changeable changeable) {
                    changeable.setActualStatus(Changeable.Status.NORMAL);
                }
            }
        } else {
            for (Node child : (this.anchorPane.getChildren())) {
                if (child instanceof Changeable changeable) {
                    for (Node child2 : children) {
                        if (!child.equals(child2)) {
                            changeable.setActualStatus(Changeable.Status.NORMAL);
                        }
                    }
                }
            }
        }
    }

    public void restoreState() {
        if (PagesBuffer.fxmlName != null) {
            ArrayList<String> timeList = PagesBuffer.buffer;
            ArrayList<Node> elements = new ArrayList<>();
            for (Node node : anchorPane.getChildren()) {
                if (node instanceof SimpleTextField || node instanceof SimpleButton
                        || node instanceof ButtonWithPicture || node instanceof SimpleImageView) {
                    elements.add(node);
                }
            }
            for (int i = 0; i < timeList.size(); i++) {
                Node node = elements.get(i);
                if (node instanceof SimpleTextField textField) textField.setText(timeList.get(i));
                if (node instanceof SimpleButton button) button.changePosition(Integer.parseInt(timeList.get(i)));
                if (node instanceof ButtonWithPicture button) button.changePosition(Integer.parseInt(timeList.get(i)));
                if (node instanceof SimpleImageView image) image.changePosition(Integer.parseInt(timeList.get(i)));
            }
            PagesBuffer.buffer.clear();
        }
    }


}
