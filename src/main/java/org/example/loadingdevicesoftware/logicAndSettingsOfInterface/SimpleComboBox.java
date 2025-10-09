package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

/**
 * Комбобокс с едиными настройками отображения.
 */
public class SimpleComboBox<T> extends ComboBox<T> implements Changeable {

    private final Font font = FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL);
    private final String STYLE = "combo-box";

    public SimpleComboBox() {
        super();
        initialize();
    }

    public void setup() {
        getStyleClass().add(STYLE);
    }

    private void initialize() {
        setCellFactory(listView -> createCell());
        setButtonCell(createCell());
        skinProperty().addListener((observable, oldSkin, newSkin) -> hideArrow());
        Platform.runLater(this::hideArrow);
    }

    @Override
    public void changePosition(int position) {
    }

    @Override
    public void setActualStatus(Status status) {

    }

    private ListCell<T> createCell() {
        return new ListCell<>() {
            {
                setAlignment(Pos.CENTER);
                setFont(font);
            }

            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        };
    }

    private void hideArrow() {
        Node arrowButton = lookup(".arrow-button");
        if (arrowButton != null) {
            arrowButton.setVisible(false);
            arrowButton.setManaged(false);
        }
        Node arrow = lookup(".arrow");
        if (arrow != null) {
            arrow.setVisible(false);
            arrow.setManaged(false);
        }
    }
}
