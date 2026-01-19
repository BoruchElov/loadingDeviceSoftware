package org.example.loadingdevicesoftware.pagesControllers;

import javafx.fxml.FXML;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.ApplicationConstants;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.FontManager;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.SimpleImageView;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.SimpleTextField;

import java.util.ArrayList;
import java.util.List;

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
    SimpleImageView amperemeterFigure;
    @FXML
    SimpleImageView voltmeterFigure;
    @FXML
    SimpleImageView currentTransformer;
    
    

    @FXML
    public void initialize() {
        super.initialize();

        nodesToCheck = new ArrayList<>(List.of(new Node[]{currentInput, phaseInput, timeInput, objectTextField,
                nameTextField}));

        //Настройка текстов
        secondaryCurrentRMSText.setText("I2, А");
        secondaryCurrentRMSText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        secondaryCurrentRMSText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryCurrentRMSText, 210.0);
        AnchorPane.setLeftAnchor(secondaryCurrentRMSText, 73.0);

        secondaryCurrentPhaseText.setText("φI2, °");
        secondaryCurrentPhaseText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        secondaryCurrentPhaseText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryCurrentPhaseText, 210.0);
        AnchorPane.setLeftAnchor(secondaryCurrentPhaseText, 178.0);

        secondaryVoltageRMSText.setText("U2, В");
        secondaryVoltageRMSText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        secondaryVoltageRMSText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryVoltageRMSText, 375.);
        AnchorPane.setLeftAnchor(secondaryVoltageRMSText, 73.0);

        secondaryVoltagePhaseText.setText("φU2, °");
        secondaryVoltagePhaseText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        secondaryVoltagePhaseText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryVoltagePhaseText, 375.0);
        AnchorPane.setLeftAnchor(secondaryVoltagePhaseText, 178.0);

        secondaryImpedanceText.setText("Z2, \nОм");
        secondaryImpedanceText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        secondaryImpedanceText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(secondaryImpedanceText, 208.0);
        AnchorPane.setLeftAnchor(secondaryImpedanceText, 260.0);

        transformerRatioText.setText("KT");
        transformerRatioText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        transformerRatioText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(transformerRatioText, 305.0);
        AnchorPane.setLeftAnchor(transformerRatioText, 260.0);

        ratedPowerText.setText("SН, \nВА");
        ratedPowerText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        ratedPowerText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(ratedPowerText, 375.0);
        AnchorPane.setLeftAnchor(ratedPowerText, 260.0);

        powerFactorText.setText("cos φ");
        powerFactorText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        powerFactorText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(powerFactorText, 468.0);
        AnchorPane.setLeftAnchor(powerFactorText, 260.0);

        AOneText.setText("A1");
        AOneText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        AOneText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(AOneText, 190.0);
        AnchorPane.setLeftAnchor(AOneText, 575.0);

        BOneText.setText("B1");
        BOneText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        BOneText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(BOneText, 190.0);
        AnchorPane.setLeftAnchor(BOneText, 645.0);

        COneText.setText("C1");
        COneText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        COneText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(COneText, 190.0);
        AnchorPane.setLeftAnchor(COneText, 715.0);

        measuredCurrentText.setText("I, А");
        measuredCurrentText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        measuredCurrentText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(measuredCurrentText, 530.0);
        AnchorPane.setLeftAnchor(measuredCurrentText, 588.0);

        measuredVoltageText.setText("U, В");
        measuredVoltageText.setFont(FontManager.getFont(FontManager.FontWeight.LIGHT, FontManager.FontSize.NORMAL));
        measuredVoltageText.setFill(Color.BLACK);
        AnchorPane.setTopAnchor(measuredVoltageText, 530.0);
        AnchorPane.setLeftAnchor(measuredVoltageText, 685.0);

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

        measuredCurrent.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        measuredCurrent.setAlignment(Pos.CENTER);
        measuredCurrent.setup("", SimpleTextField.Sizes.MEDIUM_TWO, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(measuredCurrent, 563.);
        AnchorPane.setLeftAnchor(measuredCurrent, 450.);
        measuredCurrent.setEditable(false);

        measuredVoltage.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        measuredVoltage.setAlignment(Pos.CENTER);
        measuredVoltage.setup("", SimpleTextField.Sizes.MEDIUM_TWO, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(measuredVoltage, 563.);
        AnchorPane.setLeftAnchor(measuredVoltage, 750.);
        measuredVoltage.setEditable(false);

        secondaryImpedance.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        secondaryImpedance.setAlignment(Pos.CENTER);
        secondaryImpedance.setup("", SimpleTextField.Sizes.SMALL_ONE, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(secondaryImpedance, 200.);
        AnchorPane.setLeftAnchor(secondaryImpedance, 319.);
        secondaryImpedance.setEditable(false);

        transformerRatio.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        transformerRatio.setAlignment(Pos.CENTER);
        transformerRatio.setup("", SimpleTextField.Sizes.SMALL_ONE, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(transformerRatio, 282.);
        AnchorPane.setLeftAnchor(transformerRatio, 319.);
        transformerRatio.setEditable(false);

        ratedPower.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        ratedPower.setAlignment(Pos.CENTER);
        ratedPower.setup("", SimpleTextField.Sizes.SMALL_ONE, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(ratedPower, 364.);
        AnchorPane.setLeftAnchor(ratedPower, 319.);
        ratedPower.setEditable(false);

        powerFactor.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        powerFactor.setAlignment(Pos.CENTER);
        powerFactor.setup("", SimpleTextField.Sizes.SMALL_ONE, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(powerFactor, 446.);
        AnchorPane.setLeftAnchor(powerFactor, 319.);
        powerFactor.setEditable(false);

        secondaryCurrentRMS.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        secondaryCurrentRMS.setAlignment(Pos.CENTER);
        secondaryCurrentRMS.setup("", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(secondaryCurrentRMS, 239.);
        AnchorPane.setLeftAnchor(secondaryCurrentRMS, 50.);
        secondaryCurrentRMS.setEditable(false);

        secondaryCurrentPhase.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        secondaryCurrentPhase.setAlignment(Pos.CENTER);
        secondaryCurrentPhase.setup("", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(secondaryCurrentPhase, 239.);
        AnchorPane.setLeftAnchor(secondaryCurrentPhase, 155.);
        secondaryCurrentPhase.setEditable(false);

        secondaryVoltageRMS.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        secondaryVoltageRMS.setAlignment(Pos.CENTER);
        secondaryVoltageRMS.setup("", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(secondaryVoltageRMS, 407.);
        AnchorPane.setLeftAnchor(secondaryVoltageRMS, 50.);
        secondaryVoltageRMS.setEditable(false);

        secondaryVoltagePhase.setFont(FontManager.getFont(FontManager.FontWeight.MEDIUM, FontManager.FontSize.LARGE));
        secondaryVoltagePhase.setAlignment(Pos.CENTER);
        secondaryVoltagePhase.setup("", SimpleTextField.Sizes.MEDIUM_ONE, SimpleTextField.typeOfValue.DIGIT);
        AnchorPane.setTopAnchor(secondaryVoltagePhase, 407.);
        AnchorPane.setLeftAnchor(secondaryVoltagePhase, 155.);
        secondaryVoltagePhase.setEditable(false);

        //Настройка изображений
        amperemeterFigure.setup(new String[]{""}, new Image[]{ApplicationConstants.AMPERMETR}, new double[][]{{60., 60.}});
        AnchorPane.setTopAnchor(amperemeterFigure, 567.);
        AnchorPane.setLeftAnchor(amperemeterFigure, 570.);

        voltmeterFigure.setup(new String[]{""}, new Image[]{ApplicationConstants.VOLTMETR}, new double[][]{{60., 60.}});
        AnchorPane.setTopAnchor(voltmeterFigure, 567.);
        AnchorPane.setLeftAnchor(voltmeterFigure, 673.);

        currentTransformer.setup(new String[]{""}, new Image[]{ApplicationConstants.CURRENT_TRANSFRMER}, new double[][]{{350., 350.}});
        AnchorPane.setTopAnchor(currentTransformer, 180.);
        AnchorPane.setLeftAnchor(currentTransformer, 480.);
    }

}

