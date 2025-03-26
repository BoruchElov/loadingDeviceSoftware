package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.loadingdevicesoftware.ApplicationFile;
import java.io.File;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import java.util.Objects;


public class InterfaceElementsLogic {
    /**
     * Конструктор класса InterfaceElementsLogic. Данный класс содержит методы, используемые для настройки различных
     * функций интерфейса: переход со страницы на страницу.
     */
    public InterfaceElementsLogic() {}

    /**
     * Метод для перехода с одного экрана на другой.
     * @param node графический узел (в любом контроллере в качестве этой переменной должно задаваться
     *             <code>(Node) event.getSource()</code>
     * @param fxmlFilePath имя файла fxml в формате <b>fileName.fxml</b>
     * @throws IOException исключение
     */
    @FXML
    public static void switchScene(Node node, String fxmlFilePath) throws IOException {
        // Получаем текущий Stage из элемента интерфейса
        Stage stage = (Stage) node.getScene().getWindow();

        // Загружаем новый FXML
        Parent root = FXMLLoader.load(Objects.requireNonNull(ApplicationFile.class.
                getResource(fxmlFilePath)));

        // Устанавливаем новую сцену
        stage.setScene(new Scene(root, ApplicationConstants.APPLICATION_WINDOW_LENGTH,
                ApplicationConstants.APPLICATION_WINDOW_HEIGHT));
        stage.show();
    }

    @FXML
    public static void openFileManager() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите рабочую директорию");

        // Устанавливаем начальную директорию
        File initialDirectory = new File(System.getProperty("user.home"));
        directoryChooser.setInitialDirectory(initialDirectory);

        // Открываем окно выбора директории
        directoryChooser.showDialog(new Stage());
    }

    //тестовые функции для перехода
    /**
     * -- SETTER --
     *  Устанавливает флаг, указывающий, откуда был выполнен переход.
     * -- GETTER --
     *  Возвращает значение флага.
     */
    @Getter
    @Setter
    private static boolean fromCheckingStartConditions = false;


    /**
     * Специализированный метод для булевых операций (блокировка/разблокировка)
     * @param root Корневой элемент формы
     * @param shouldDisable true - заблокировать, false - разблокировать
     * @param action Действие с нод и булевым флагом (например, setDisable)
     * @param exclusionConditions Условия исключения элементов
     */
    public static void walk(
            Parent root,
            boolean shouldDisable,
            BiConsumer<Node, Boolean> action,
            Predicate<Node>... exclusionConditions
    ) {
        walkRecursive(
                root,
                action,
                shouldDisable,
                // Объединяем все условия исключений
                node -> Arrays.stream(exclusionConditions)
                        .anyMatch(condition -> condition.test(node))
        );
    }

    private static void walkRecursive(
            Node node,
            BiConsumer<Node, Boolean> action,
            boolean shouldDisable,
            Predicate<Node> shouldExclude
    ) {
        // Пропускаем элементы, соответствующие условиям исключения
        if (shouldExclude.test(node)) {
            return;
        }

        // Применяем действие
        action.accept(node, shouldDisable);

        // Рекурсивный обход дочерних элементов
        if (node instanceof Parent) {
            ((Parent) node).getChildrenUnmodifiable()
                    .forEach(child -> walkRecursive(child, action, shouldDisable, shouldExclude));
        }
    }
}

