package org.example.loadingdevicesoftware.pagesControllers;

import eu.hansolo.tilesfx.colors.Medium;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.FontManager;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.SimpleTextField;

public class _5_TestOfMeasurementTransformerScenarioController extends ScreensController {
    
    //Блок подписей к элементам
    @FXML
    Text secondaryCurrentRMSText;
    @FXML
    Text secondaryCurrentPhaseText;
    @FXML
    Text secondaryVoltageRMSText;
    @FXML
    Text secondaryVoltagePhaseText;
    @FXML
    Text secondaryImpedanceText;
    @FXML
    Text transformerRatioText;
    @FXML
    Text ratedPowerText;
    @FXML
    Text powerFactorText;
    @FXML
    Text measuredCurrentText;
    @FXML
    Text measuredVoltageText;
    @FXML
    Text AOneText;
    @FXML
    Text BOneText;
    @FXML
    Text COneText;
    @FXML
    Text inputCurrentText;
    @FXML
    Text inputPhaseText;
    @FXML
    Text inputTimeText;

    //Блок полей ввода
    @FXML
    SimpleTextField currentInput;
    @FXML
    SimpleTextField phaseInput;
    @FXML
    SimpleTextField timeInput;

    //Блок полей вывода
    @FXML
    SimpleTextField secondaryCurrentRMS;
    @FXML
    SimpleTextField secondaryCurrentPhase;
    @FXML
    SimpleTextField secondaryVoltageRMS;
    @FXML
    SimpleTextField secondaryVoltagePhase;
    @FXML
    SimpleTextField secondaryImpedance;
    @FXML
    SimpleTextField transformerRatio;
    @FXML
    SimpleTextField ratedPower;
    @FXML
    SimpleTextField powerFactor;
    @FXML
    SimpleTextField measuredCurrent;
    @FXML
    SimpleTextField measuredVoltage;
    
    

    @FXML
    public void initialize() {
        super.initialize();

        //Настройка текстов
        secondaryCurrentRMSText.setText("I2, А");
        secondaryCurrentRMSText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        secondaryCurrentRMSText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryCurrentRMSText, 0.0);
        AnchorPane.setLeftAnchor(secondaryCurrentRMSText, 0.0);

        secondaryCurrentPhaseText.setText("φ2, °");
        secondaryCurrentPhaseText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        secondaryCurrentPhaseText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryCurrentPhaseText, 0.0);
        AnchorPane.setLeftAnchor(secondaryCurrentPhaseText, 0.0);

        secondaryVoltageRMSText.setText("U2, В");
        secondaryVoltageRMSText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        secondaryVoltageRMSText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryVoltageRMSText, 0.0);
        AnchorPane.setLeftAnchor(secondaryVoltageRMSText, 0.0);

        secondaryVoltagePhaseText.setText("φ2, °");
        secondaryVoltagePhaseText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        secondaryVoltagePhaseText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryVoltagePhaseText, 0.0);
        AnchorPane.setLeftAnchor(secondaryVoltagePhaseText, 0.0);

        secondaryImpedanceText.setText("Z2, Ом");
        secondaryImpedanceText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        secondaryImpedanceText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryImpedanceText, 0.0);
        AnchorPane.setLeftAnchor(secondaryImpedanceText, 0.0);

        transformerRatioText.setText("KT");
        transformerRatioText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        transformerRatioText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(transformerRatioText, 0.0);
        AnchorPane.setLeftAnchor(transformerRatioText, 0.0);

        ratedPowerText.setText("SН, ВА");
        ratedPowerText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        ratedPowerText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(ratedPowerText, 0.0);
        AnchorPane.setLeftAnchor(ratedPowerText, 0.0);

        powerFactorText.setText("cos φ");
        powerFactorText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        powerFactorText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(powerFactorText, 0.0);
        AnchorPane.setLeftAnchor(powerFactorText, 0.0);

        AOneText.setText("A1");
        AOneText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        AOneText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(AOneText, 0.0);
        AnchorPane.setLeftAnchor(AOneText, 0.0);

        BOneText.setText("B1");
        BOneText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        BOneText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(BOneText, 0.0);
        AnchorPane.setLeftAnchor(BOneText, 0.0);

        COneText.setText("C1");
        COneText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        COneText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(COneText, 0.0);
        AnchorPane.setLeftAnchor(COneText, 0.0);

        measuredCurrentText.setText("I, А");
        measuredCurrentText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        measuredCurrentText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(measuredCurrentText, 0.0);
        AnchorPane.setLeftAnchor(measuredCurrentText, 0.0);

        measuredVoltageText.setText("U, В");
        measuredVoltageText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.SMALL));
        measuredVoltageText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(measuredVoltageText, 0.0);
        AnchorPane.setLeftAnchor(measuredVoltageText, 0.0);

        inputCurrentText.setText("Ток, А");
        inputCurrentText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
        inputCurrentText.setFill(Color.WHITE);
        AnchorPane.setTopAnchor(inputCurrentText, 155.0);
        AnchorPane.setLeftAnchor(inputCurrentText, 1070.0);

        inputPhaseText.setText("Фаза, °");
        inputPhaseText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
        inputPhaseText.setFill(Color.WHITE);
        AnchorPane.setTopAnchor(inputPhaseText, 325.0);
        AnchorPane.setLeftAnchor(inputPhaseText, 1070.0);

        inputTimeText.setText("Время, с");
        inputTimeText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.LARGE));
        inputTimeText.setFill(Color.WHITE);
        AnchorPane.setTopAnchor(inputTimeText, 500.0);
        AnchorPane.setLeftAnchor(inputTimeText, 1050.0);
        /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Настройка текстовых полей ввода
        timeInput.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        timeInput.setAlignment(Pos.CENTER);
        timeInput.setup("0.01", SimpleTextField.Sizes.LARGE, SimpleTextField.typeOfValue.DIGIT);
        timeInput.setLimits(0.01, 3600., SimpleTextField.numberOfDecimals.THREE);
        AnchorPane.setTopAnchor(timeInput, 560.);
        AnchorPane.setLeftAnchor(timeInput, 1005.);
        timeInput.setPrefSize(200., 67.);
        timeInput.setEditable(true);

        phaseInput.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        phaseInput.setAlignment(Pos.CENTER);
        phaseInput.setup("0.0", SimpleTextField.Sizes.LARGE, SimpleTextField.typeOfValue.DIGIT);
        phaseInput.setLimits(-360.0, 360.0, SimpleTextField.numberOfDecimals.THREE);
        AnchorPane.setTopAnchor(phaseInput, 385.);
        AnchorPane.setLeftAnchor(phaseInput, 1005.);
        phaseInput.setPrefSize(200., 67.);
        phaseInput.setEditable(true);

        currentInput.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        currentInput.setAlignment(Pos.CENTER);
        currentInput.setup("0.0", SimpleTextField.Sizes.LARGE, SimpleTextField.typeOfValue.DIGIT);
        currentInput.setLimits(-360.0, 360.0, SimpleTextField.numberOfDecimals.THREE);
        AnchorPane.setTopAnchor(currentInput, 215.);
        AnchorPane.setLeftAnchor(currentInput, 1005.);
        currentInput.setPrefSize(200., 67.);
        currentInput.setEditable(true);

        //Настройка текстовых полей вывода
        measuredCurrent.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        measuredCurrent.setAlignment(Pos.CENTER);
        measuredCurrent.setup("", SimpleTextField.Sizes.MEDIUM, SimpleTextField.typeOfValue.DIGIT);
        measuredCurrent.setLimits(-3000.0, 3000.0, SimpleTextField.numberOfDecimals.THREE);
        AnchorPane.setTopAnchor(measuredCurrent, 215.);
        AnchorPane.setLeftAnchor(measuredCurrent, 1005.);
        measuredCurrent.setPrefSize(200., 67.);
        measuredCurrent.setEditable(true);

    }

}

