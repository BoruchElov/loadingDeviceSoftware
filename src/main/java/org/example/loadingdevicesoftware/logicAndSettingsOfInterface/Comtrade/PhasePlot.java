package org.example.loadingdevicesoftware.logicAndSettingsOfInterface.Comtrade;

import io.fair_acc.chartfx.XYChart;
import io.fair_acc.chartfx.axes.AxisMode;
import io.fair_acc.chartfx.axes.spi.DefaultNumericAxis;
import io.fair_acc.chartfx.plugins.Zoomer;
import io.fair_acc.dataset.spi.DefaultErrorDataSet;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.FontManager;

import java.util.ArrayList;

public class PhasePlot {

    public enum Phase {
        A_ONE,
        A_TWO,
        B_ONE,
        B_TWO,
        C_ONE,
        C_TWO,
    }

    private Phase plotPhase;

    //Объект графика
    private XYChart chart;
    //Объект набора данных
    private final DefaultErrorDataSet dataSet = new DefaultErrorDataSet("PhasePlot");

    double min;
    double max;
    double xMax;

    private volatile boolean isDataSetEmpty = true;
    private String uniqueCode;


    public PhasePlot(String title, ArrayList<Double> xValues, ArrayList<Double> yValues) {
        xAxis.getAxisLabel().fontProperty().set(FontManager.getFont(FontManager.FontWeight.MEDIUM,
                FontManager.FontSize.SMALL_FOURTEEN));
        xAxis.getTickLabelStyle().fontProperty().set(FontManager.getFont(FontManager.FontWeight.LIGHT,
                FontManager.FontSize.SMALL_TWELVE));
        xAxis.setAutoRanging(true);
        //Объект оси Y и настройка подписей, масштаба построения
        yAxis.getAxisLabel().fontProperty().set(FontManager.getFont(FontManager.FontWeight.MEDIUM,
                FontManager.FontSize.SMALL_FOURTEEN));
        yAxis.getTickLabelStyle().fontProperty().set(FontManager.getFont(FontManager.FontWeight.LIGHT,
                FontManager.FontSize.SMALL_TWELVE));
        yAxis.setAutoRanging(true);
        //
        chart = new XYChart(xAxis, yAxis);
        chart.legendVisibleProperty().set(false);
        //Подпись графика
        chart.getTitleLabel().fontProperty().set(FontManager.getFont(FontManager.FontWeight.MEDIUM,
                FontManager.FontSize.SMALL_EIGHTEEN));


        updatePlot(title, xValues, yValues);
        generateCode(title);
    }

    private void generateCode(String title) {
        uniqueCode = title + "_" + this.hashCode();
    }

    public String getCode() {
        return uniqueCode;
    }

    public PhasePlot() {}

    public void setPhase(Phase plotPhase) {
        this.plotPhase = plotPhase;
    }

    public Phase getPhase() {
        return plotPhase;
    }

    public void updatePlot(String title, ArrayList<Double> xValues, ArrayList<Double> yValues) {
        generateDataSet(xValues, yValues);
        setupPlot(title);
    }

    public XYChart getChart() {
        return chart;
    }

    private void generateDataSet(ArrayList<Double> xValues, ArrayList<Double> yValues) {
        dataSet.clearData();
        if (xValues == null || yValues == null) {
            isDataSetEmpty = true;
            return;
        }
        for (int i = 0; i < xValues.size(); i++) {
            dataSet.add(xValues.get(i), yValues.get(i));
        }
        isDataSetEmpty = false;
        for (Double y : yValues) {
            if (y < min) {
                min = y;
            }
            if (y > max) {
                max = y;
            }
        }
        xMax = xValues.isEmpty() ? 1. : xValues.getLast();
    }


    DefaultNumericAxis xAxis = new DefaultNumericAxis("Время", "с");
    DefaultNumericAxis yAxis = new DefaultNumericAxis("Значение", null);

    private void setupPlot(String title) {
        if (isDataSetEmpty) {
            //Объект оси X и настройка подписей
            //Настройка заголовка графика
            chart.setTitle(title);
            //Настройка видимости легенды

            chart.getDatasets().clear();
        } else {
            //Объект оси X и настройка подписей
            xAxis.set(0., xMax);
            //Объект оси Y и настройка подписей, масштаба построения
            double factor = 1.5;
            yAxis.setAutoRanging(false);
            yAxis.set(factor * min, factor * max);
            //Настройка заголовка графика
            chart.setTitle(title);
            //Настройка видимости легенды
            //Инициализация и настройка плагина для приближения графика
            Zoomer zoomer = new Zoomer();
            //Выбор оси, по которой осуществляется приближение
            zoomer.axisModeProperty().set(AxisMode.X);
            //Отключение слайдера
            zoomer.sliderVisibleProperty().set(false);
            //Отключение всплывающего окна с настройками масштабирования
            zoomer.setAddButtonsToToolBar(false);
            zoomer.addButtonsToToolBarProperty().set(false);
            //Подпись графика
            chart.getTitleLabel().fontProperty().set(FontManager.getFont(FontManager.FontWeight.MEDIUM,
                    FontManager.FontSize.SMALL_EIGHTEEN));
            //
            //Настройка изменения масштаба: возможен только Zoom-In при нажатом ctrl
            zoomer.setZoomScrollFilter(e -> e.isControlDown() && e.getDeltaY() > 0);
            chart.getPlugins().add(zoomer);
            chart.getDatasets().add(dataSet);
        }

    }

}
