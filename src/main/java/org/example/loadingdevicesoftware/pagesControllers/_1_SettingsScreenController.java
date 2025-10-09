package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class _1_SettingsScreenController extends BasicController {
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
    public void initialize() {
        super.initialize();
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
            clear();
        });

        SimpleImageView[] images = new SimpleImageView[]{inverterImageViewOne, inverterImageViewTwo,
                inverterImageViewThree, inverterImageViewFour, inverterImageViewFive, inverterImageViewSix};

        ComboBox[] combos = new ComboBox[]{phaseAOneComboBox, phaseBOneComboBox, phaseCOneComboBox, phaseATwoComboBox,
                phaseBTwoComboBox, phaseCTwoComboBox};

        Circle[] circles = new Circle[]{circleAOne, circleBOne, circleCOne, circleATwo, circleBTwo, circleCTwo};

        Text[] texts = new Text[]{textInverterAOne, textInverterBOne, textInverterCOne, textInverterATwo,
                textInverterBTwo, textInverterCTwo};

        String[] phrases = new String[]{"МОДУЛЬ А1", "МОДУЛЬ В1", "МОДУЛЬ С1", "МОДУЛЬ А2", "МОДУЛЬ В2", "МОДУЛЬ С2"};
        List<String> addresses = new ArrayList<>();
        try {
            addresses = AddressesStorage.readAddresses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < images.length; i++) {
            images[i].setup(new String[]{""}, new Image[]{ApplicationConstants.INVERTER_IMAGE},
                    new double[][]{{150, 150}});
            combos[i].setPromptText("---Не задан---");
            combos[i].getItems().addAll("00:FF:EE:SA", "11:RR:SS:WE", "22:XX:CC:1D", "33:XX:DD:EE", "44:XX:DD:EE:FF");
            String value = addresses.get(i);
            if (value.isEmpty()) {
            }
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



    }

    private void save() throws IOException {
        List<String> addressesList = new ArrayList<String>();
        for (Node child : this.anchorPane.getChildren()) {
            if (child instanceof SimpleComboBox<?> comboBox) {
                //addressesList.add(comboBox.getSelectionModel().getSelectedItem().toString());
                Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
                addressesList.add(selectedItem == null ? "" : selectedItem.toString());
            }
        }
        AddressesStorage.writeAddresses(addressesList);
    }

    private void clear() {
        for (Node child : this.anchorPane.getChildren()) {
            if (child instanceof SimpleComboBox<?> comboBox) {
                comboBox.getSelectionModel().clearSelection();
            }
        }
    }
}