package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.communicationWithInverters.ConnectionControl;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class _1_SettingsController extends BasicController {
    @FXML
    SimpleButton saveButton;
    @FXML
    SimpleButton clearButton;
    @FXML
    SimpleButton menuButton;

    @FXML
    SimpleImageView inverterImageViewOne;
    @FXML
    SimpleImageView inverterImageViewTwo;
    @FXML
    SimpleImageView inverterImageViewThree;
    @FXML
    SimpleImageView inverterImageViewFour;
    @FXML
    SimpleImageView inverterImageViewFive;
    @FXML
    SimpleImageView inverterImageViewSix;

    @FXML
    CheckBox expertModeCheckBox;

    @FXML
    SimpleComboBox<String> phaseAOneComboBox;
    @FXML
    SimpleComboBox<String> phaseATwoComboBox;
    @FXML
    SimpleComboBox<String> phaseBOneComboBox;
    @FXML
    SimpleComboBox<String> phaseBTwoComboBox;
    @FXML
    SimpleComboBox<String> phaseCOneComboBox;
    @FXML
    SimpleComboBox<String> phaseCTwoComboBox;

    @FXML
    Circle circleAOne;
    @FXML
    Circle circleATwo;
    @FXML
    Circle circleBOne;
    @FXML
    Circle circleBTwo;
    @FXML
    Circle circleCOne;
    @FXML
    Circle circleCTwo;

    @FXML
    Text textInverterAOne;
    @FXML
    Text textInverterATwo;
    @FXML
    Text textInverterBOne;
    @FXML
    Text textInverterBTwo;
    @FXML
    Text textInverterCOne;
    @FXML
    Text textInverterCTwo;

    @FXML
    Text expertMode;


    @FXML
    public void initialize() {
        super.initialize();

        expertModeCheckBox.setSelected(ApplicationConstants.expertStatus);
        checkBoxAction();

        imageView.setImage(ApplicationConstants.NEW_BASE_BACKGROUND);

        saveButton.setup(SimpleButton.Presets.SAVE);
        AnchorPane.setTopAnchor(saveButton, 695.0);
        AnchorPane.setLeftAnchor(saveButton, 1030.0);
        saveButton.setOnAction(event -> {
            try {
                save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        menuButton.setup(SimpleButton.Presets.MENU);
        AnchorPane.setTopAnchor(menuButton, 695.0);
        AnchorPane.setLeftAnchor(menuButton, 50.0);
        menuButton.setOnAction(event -> {
            try {
                InterfaceElementsLogic.switchScene((Node) event.getSource(), "0.baseWindow.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        clearButton.setup(SimpleButton.Presets.CLEAR);
        AnchorPane.setTopAnchor(clearButton, 695.0);
        AnchorPane.setLeftAnchor(clearButton, 770.0);
        clearButton.setOnAction(event -> {
            try {
                clear();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        SimpleImageView[] images = new SimpleImageView[]{inverterImageViewOne, inverterImageViewTwo,
                inverterImageViewThree, inverterImageViewFour, inverterImageViewFive, inverterImageViewSix};

        ComboBox[] combos = new ComboBox[]{phaseAOneComboBox, phaseBOneComboBox, phaseCOneComboBox, phaseATwoComboBox,
                phaseBTwoComboBox, phaseCTwoComboBox};

        Circle[] circles = new Circle[]{circleAOne, circleBOne, circleCOne, circleATwo, circleBTwo, circleCTwo};

        Text[] texts = new Text[]{textInverterAOne, textInverterBOne, textInverterCOne, textInverterATwo,
                textInverterBTwo, textInverterCTwo};

        String[] phrases = new String[]{"БЛОК А1", "БЛОК В1", "БЛОК С1", "БЛОК А2", "БЛОК В2", "БЛОК С2"};
        List<String> addresses;
        try {
            addresses = AddressesStorage.getListOfSavedAddresses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < images.length; i++) {
            ComboBox<String> combo = combos[i];
            images[i].setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                    new double[][]{{150, 150}});
            combos[i].setOnShowing(event -> {
                fillComboBoxList((ComboBox) event.getSource());
            });
            String value = addresses.get(i);
            combos[i].setValue(value.equals("00:00:00:00") ? "--НЕ ЗАДАН--" : value);
            combos[i].valueProperty().addListener((obs, oldVal, newVal) -> {
                if (combo.isShowing() && newVal != null && !newVal.equals(oldVal)) {
                    String keep = (String) newVal;
                    combo.hide(); // важно: закрыть popup перед манипуляциями с items
                    javafx.application.Platform.runLater(() -> {
                        fillComboBoxList(combo);   // меняет items
                        combo.setValue(keep);      // возвращаем выбранное значение на отображение
                    });
                }
            });
            circles[i].setFill(Color.web(ApplicationConstants.Gray));
            circles[i].setRadius(10);
            circles[i].getStyleClass().add("circles");
            texts[i].setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
            texts[i].setText(phrases[i]);
            if (i < 3) {
                AnchorPane.setTopAnchor(images[i], 170.0);
                AnchorPane.setLeftAnchor(images[i], 55.0 + i * 425.0);

                AnchorPane.setTopAnchor(combos[i], 195.0);
                AnchorPane.setLeftAnchor(combos[i], 220.0 + i * 425.0);

                AnchorPane.setTopAnchor(circles[i], 165.0);
                AnchorPane.setLeftAnchor(circles[i], 220.0 + i * 425.0);

                AnchorPane.setTopAnchor(texts[i], 168.0);
                AnchorPane.setLeftAnchor(texts[i], 250.0 + i * 425.0);
            } else {
                AnchorPane.setTopAnchor(images[i], 400.0);
                AnchorPane.setLeftAnchor(images[i], 55.0 + (i - 3) * 425.0);

                AnchorPane.setTopAnchor(combos[i], 425.0);
                AnchorPane.setLeftAnchor(combos[i], 220.0 + (i - 3) * 425.0);

                AnchorPane.setTopAnchor(circles[i], 395.0);
                AnchorPane.setLeftAnchor(circles[i], 220.0 + (i - 3) * 425.0);

                AnchorPane.setTopAnchor(texts[i], 398.0);
                AnchorPane.setLeftAnchor(texts[i], 250.0 + (i - 3) * 425.0);
            }
        }
        expertMode.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        expertMode.setText("Режим эксперта");
        AnchorPane.setTopAnchor(expertMode,590.);
        AnchorPane.setLeftAnchor(expertMode,140.);

        expertModeCheckBox.getStyleClass().add("check-box");
        AnchorPane.setTopAnchor(expertModeCheckBox,577.);
        AnchorPane.setLeftAnchor(expertModeCheckBox,65.);
        expertModeCheckBox.setOnAction(event -> {
            checkBoxAction();
        });

    }

    private void checkBoxAction() {
        boolean isSelected = expertModeCheckBox.isSelected();
        ApplicationConstants.expertStatus = isSelected;
        clearButton.setDisable(!isSelected);
        saveButton.setDisable(!isSelected);
        ComboBox[] combos = new ComboBox[]{phaseAOneComboBox, phaseBOneComboBox, phaseCOneComboBox, phaseATwoComboBox,
                phaseBTwoComboBox, phaseCTwoComboBox};
        for (ComboBox comboBox : combos) {
            comboBox.setDisable(!isSelected);
        }
    }

    private void save() throws IOException {
        List<String> addressesList = new ArrayList<>();
        ComboBox[] combos = new ComboBox[]{phaseAOneComboBox, phaseBOneComboBox, phaseCOneComboBox, phaseATwoComboBox,
                phaseBTwoComboBox, phaseCTwoComboBox};
        for (ComboBox<String> combo : combos) {
            String selectedItem = combo.getSelectionModel().getSelectedItem();
            if (selectedItem.equals("") || selectedItem.equals("--НЕ ЗАДАН--")) {
                selectedItem = "00:00:00:00";
            }
            addressesList.add(selectedItem);
        }
        AddressesStorage.writeAddresses(addressesList);
    }

    private void clear() throws IOException {
        for (Node child : this.anchorPane.getChildren()) {
            if (child instanceof SimpleComboBox<?> genericBox) {
                @SuppressWarnings("unchecked")
                SimpleComboBox<String> comboBox = (SimpleComboBox<String>) genericBox;
                comboBox.setValue("--НЕ ЗАДАН--");
            }

        }
    }

    private void fillComboBoxList(ComboBox<String> combo) {
        // 1) базовый список
        List<String> base = new ArrayList<>(ConnectionControl.getSavedAddresses());
        base.removeIf(a -> a == null || a.isBlank() || "00:00:00:00".equals(a));

        // 2) уже выбранные в других комбах
        java.util.Set<String> selected = anchorPane.getChildren().stream()
                .filter(n -> n instanceof SimpleComboBox<?> && n != combo)
                .map(n -> ((SimpleComboBox<?>) n).getSelectionModel().getSelectedItem())
                .filter(java.util.Objects::nonNull)
                .map(Object::toString)
                .collect(java.util.stream.Collectors.toSet());

        // 3) фильтрация
        java.util.List<String> filtered = base.stream()
                .filter(a -> !selected.contains(a))
                .collect(java.util.stream.Collectors.toList());

        // 4) безопасное обновление items
        if (filtered.isEmpty()) {
            combo.getItems().clear();          // ок, пустой список допустим
        } else {
            combo.getItems().setAll(filtered); // нет subList-ов руками
        }
    }
}