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
     * Обходит всю форму и применяет действие к элементам
     * @param root Корневой элемент (AnchorPane/VBox и т.д.)
     * @param action Действие (нода + доп. параметр)
     * @param param Дополнительный параметр для действия
     * @param exclusions Условия исключения элементов (например, по fx:id)
     */
    public static <T> void walk(Parent root, BiConsumer<Node, T> action, T param, Predicate<Node>... exclusions) {
        walkRecursive(root, action, param, Arrays.asList(exclusions));
    }

    private static <T> void walkRecursive(Node node, BiConsumer<Node, T> action, T param, List<Predicate<Node>> exclusions) {
        // Проверяем исключения
        boolean shouldSkip = exclusions.stream()
                .anyMatch(predicate -> predicate.test(node));

        if (!shouldSkip) {
            action.accept(node, param); // Применяем действие
        }

        // Рекурсивный обход дочерних элементов
        if (node instanceof Parent) {
            ((Parent) node).getChildrenUnmodifiable()
                    .forEach(child -> walkRecursive(child, action, param, exclusions));
        }
    }
}

