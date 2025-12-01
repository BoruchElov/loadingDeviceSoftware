package org.example.loadingdevicesoftware.pagesControllers;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.*;


class ScreensController extends BasicController {

    boolean[] flags;                        //Массив для хранения булевых значений для пользовательских решений

    ArrayList<Node> nodesToCheck;

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

    //Список для хранения некорректно-заполненных элементов
    List<Node> uncheckedNodes = new ArrayList<>();
    Map<Node, Object> listeners = new HashMap<>();

    /**
     * Статический флаг, разрешающий или запрещающий старт сценария.
     */
    @Setter
    private static boolean isAllowedToStartScenario;

    @FXML
    public void initialize() {
        super.initialize();
        uncheckedNodes.clear();
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
        objectTextField.setup("Название объекта", SimpleTextField.Sizes.LARGE, SimpleTextField.typeOfValue.ORDINARY);
        objectTextField.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        nameTextField.setup("Ф.И.О. исполнителя", SimpleTextField.Sizes.LARGE, SimpleTextField.typeOfValue.ORDINARY);
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
        clearButton.setup(SimpleButton.Presets.CLEAR);
        clearButton.setActualStatus(Changeable.Status.NORMAL);
        setPageState(PageState.DEFAULT);
    }

    //Метод по очистке всех элементов окна приложения
    public void clearAll(Object controller) {
        for (Node child : this.anchorPane.getChildren()) {
            if (child instanceof SimpleTextField field) {
                if (listeners.containsKey(field)) {
                    field.textProperty().removeListener((ChangeListener<? super String>) listeners.get(field));
                    listeners.remove(field);
                    field.getStyleClass().remove("warning");
                }
            }
            if (child instanceof SimpleComboBox<?> comboBox) {
                if (listeners.containsKey(comboBox)) {
                    comboBox.getSelectionModel().selectedItemProperty().removeListener((ChangeListener<? super Object>) listeners.get(comboBox));
                    listeners.remove(comboBox);
                    comboBox.getStyleClass().remove("warning");
                }
                comboBox.getSelectionModel().clearSelection();
            }
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

    //TODO доработать вариант с ручным вводом

    public void restoreState() {
        if (PagesBuffer.getFxmlName() != null) {
            ArrayList<String> timeList = PagesBuffer.buffer;
            ArrayList<Node> elements = new ArrayList<>();
            for (Node node : anchorPane.getChildren()) {
                if (node instanceof SimpleTextField || node instanceof SimpleButton
                        || node instanceof ButtonWithPicture || node instanceof SimpleImageView || node instanceof SimpleComboBox) {
                    elements.add(node);
                }
            }
            for (int i = 0; i < timeList.size(); i++) {
                Node node = elements.get(i);
                if (node instanceof SimpleTextField textField) textField.setText(timeList.get(i));
                if (node instanceof SimpleButton button) button.changePosition(Integer.parseInt(timeList.get(i)));
                if (node instanceof ButtonWithPicture button) button.changePosition(Integer.parseInt(timeList.get(i)));
                if (node instanceof SimpleImageView image) image.changePosition(Integer.parseInt(timeList.get(i)));
                if (node instanceof SimpleComboBox<?> comboBox) comboBox.getSelectionModel().select(Integer.parseInt(timeList.get(i)));
            }
            PagesBuffer.buffer.clear();
        }
        //Если разрешён запуск сценария
        if (isAllowedToStartScenario) {
            setPageState(PageState.ALLOWED_TO_START);
        }
    }

    public void addElementsListeners() {
        for (Node node : uncheckedNodes) {
            List<String> copy = new ArrayList<>();
            copy.add("warning");
            copy.addAll(node.getStyleClass());
            node.getStyleClass().setAll(copy);
            if (node instanceof SimpleTextField field && !listeners.containsKey(field)) {
                ChangeListener<String> textListener = (observable, oldValue, newValue) -> {
                    if (field.getText().isBlank()) {
                        node.getStyleClass().setAll(copy);
                    } else {
                        node.getStyleClass().remove("warning");
                    }
                };
                field.textProperty().addListener(textListener);
                listeners.put(node, textListener);
            }
            if (node instanceof SimpleComboBox<?> comboBox && !listeners.containsKey(comboBox)) {
                ChangeListener<Object> modelListener = (observable, oldValue, newValue) -> {
                  if (comboBox.getSelectionModel().isEmpty()) {
                      node.getStyleClass().setAll(copy);
                  }  else {
                      node.getStyleClass().remove("warning");
                  }
                };
                comboBox.getSelectionModel().selectedItemProperty().addListener(modelListener);
                listeners.put(comboBox, modelListener);
            }
        }
    }

    public void savePageParameters() {
        if (!CheckingManager.getFormParameters().isEmpty()) {
            CheckingManager.getFormParameters().clear();
        }
    }

    /**
     * Метод для проверки заполнения требуемых элементов. Проходится по массиву заданных элементов и, в зависимости от
     * состояния элемента, добавляет его в список непроверенных. Помимо этого, если хоть один элемент проверку не прошёл,
     * метод возвращает флаг true.
     *
     * @return true, если хоть один из нужных элементов находится не в том состоянии
     */
    public boolean isThereSomethingToCheck() {
        boolean result = false;
        for (Node node : nodesToCheck) {
            if (node instanceof ButtonWithPicture button) {
                switch (button.getObjectPosition().getActualPosition()) {
                    case 0:
                        result = true;
                        uncheckedNodes.add(button);
                        break;
                    default:
                        break;
                }
            } else if (node instanceof SimpleButton button) {
                switch (button.getObjectPosition().getActualPosition()) {
                    case 0:
                        result = true;
                        uncheckedNodes.add(button);
                        break;
                    default:
                        break;
                }
            } else if (node instanceof SimpleTextField textField) {
                if (textField.getText().isBlank()) {
                    result = true;
                    uncheckedNodes.add(textField);
                }
            } else if (node instanceof SimpleComboBox<?> comboBox) {
                if (comboBox.getSelectionModel().isEmpty()) {
                    result = true;
                    uncheckedNodes.add(comboBox);
                }
            }
        }
        return result;
    }

    /**
     * Метод для запуска сценария. Данный метод возвращает флаг об успешном завершении
     * выполнения сценария, который сигнализирует
     * @return
     */
    public boolean launchScenario() {
        if (isAllowedToStartScenario) {
            clearButton.changePosition(0);
            clearButton.setText("СОХРАНИТЬ");
        }
        setPageState(PageState.WAITING_FOR_CHOICE);
        return true;
    }

    /**
     * Метод для экстренного прекращения выполнения сценария
     */
    private void shutdownScenario() {
    }

    /**
     * enum для задания возможных состояний формы. Всего выделено 4 состояния:
     * <li>DEFAULT - состояние по умолчанию</li>
     * <li>ALLOWED_TO_START - состояние, когда получено разрешение на запуск
     * сценария</li>
     * <li>IN_PROCESS - состояние в процессе выполнения сценария</li>
     * <li>WAITING_FOR_CHOICE - состояние, когда форма ожидает выбор пользователя
     * после выполнения сценария</li>
     */
    private enum PageState {
        DEFAULT, ALLOWED_TO_START, IN_PROCESS, WAITING_FOR_CHOICE
    }

    /**
     * Метод для задания состояния формы. Он определяет положение и функционал кнопок
     * в зависимости от значения enum
     */
    private void setPageState(PageState state) {
        switch (state) {
            case DEFAULT:

                clearButton.changePosition(0);
                clearButton.setText("ОЧИСТИТЬ");
                clearButton.setOnAction(event -> {
                    clearAll(this);
                });

                startButton.changePosition(0);
                startButton.setText("ПУСК");
                startButton.setOnAction(event -> {
                    try {
                        if (true) {
                            if (!isThereSomethingToCheck()) {
                                InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
                                PagesBuffer.savePage(this);
                                savePageParameters();
                            } else {
                                for (Node node : uncheckedNodes) {
                                    node.getStyleClass().add("okay");
                                }
                                InterfaceElementsLogic.showAlert("Ошибка в заполнении формы!" +
                                        "\nПроверьте выделенные элементы.", InterfaceElementsLogic.Alert_Size.SMALL);
                                addElementsListeners();
                                uncheckedNodes.clear();
                            }
                        } else {
                            InterfaceElementsLogic.switchScene((Node) event.getSource(), "100.checkingStartConditions.fxml");
                            PagesBuffer.savePage(this);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case ALLOWED_TO_START:
                unlockAll();
                lockAll(clearButton, startButton);

                clearButton.changePosition(2);
                clearButton.setText("ОТМЕНА");
                clearButton.setOnAction(_ -> {
                    isAllowedToStartScenario = false;
                    unlockAll();
                    setPageState(PageState.DEFAULT);
                });

                startButton.changePosition(1);
                startButton.setText("ПУСК");
                startButton.setOnAction(_ -> {
                    isAllowedToStartScenario = false;
                    launchScenario();
                });

                break;
            case IN_PROCESS:
                unlockAll();
                lockAll(startButton);

                startButton.changePosition(2);
                startButton.setText("СТОП");
                startButton.setOnAction(event -> {
                    shutdownScenario();
                    setPageState(PageState.ALLOWED_TO_START);
                });

                break;
            case WAITING_FOR_CHOICE:
                unlockAll();
                lockAll(clearButton, startButton);

                clearButton.changePosition(0);
                clearButton.setText("СОХРАНИТЬ");
                clearButton.setOnAction(event -> {
                    InterfaceElementsLogic.openFileManager();
                });

                startButton.changePosition(0);
                startButton.setText("ПРОДОЛЖИТЬ");
                startButton.setOnAction(event -> {
                    unlockAll();
                    setPageState(PageState.DEFAULT);
                });

                break;
        }
    }
}
