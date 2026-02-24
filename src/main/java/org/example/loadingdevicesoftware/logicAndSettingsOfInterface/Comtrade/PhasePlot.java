package org.example.loadingdevicesoftware.logicAndSettingsOfInterface.Comtrade;

import io.fair_acc.chartfx.XYChart;
import io.fair_acc.chartfx.axes.AxisMode;
import io.fair_acc.chartfx.axes.spi.DefaultNumericAxis;
import io.fair_acc.chartfx.plugins.Zoomer;
import io.fair_acc.dataset.spi.DefaultErrorDataSet;
import org.example.loadingdevicesoftware.logicAndSettingsOfInterface.FontManager;

import java.util.ArrayList;

public class PhasePlot {
    //Объект графика
    private XYChart chart;
    //Объект набора данных
    private DefaultErrorDataSet dataSet;

    double min;
    double max;
    double xMax;


    public PhasePlot(String title, ArrayList<Double> xValues, ArrayList<Double> yValues) {
        generateDataSet(xValues, yValues);
        setupPlot(title);

    }

    public XYChart getChart() {
        return chart;
    }

    private void generateDataSet(ArrayList<Double> xValues, ArrayList<Double> yValues) {
        dataSet = new DefaultErrorDataSet("PhasePlot");
        dataSet.clearData();
        for (int i = 0; i < xValues.size(); i++) {
            dataSet.add(xValues.get(i), yValues.get(i));
        }
        for (Double y : yValues) {
            if (y < min) {
                min = y;
            }
            if (y > max) {
                max = y;
            }
        }
        xMax = xValues.getLast();
    }

    private void setupPlot(String title) {
        //Объект оси X и настройка подписей
        DefaultNumericAxis xAxis = new DefaultNumericAxis("Время", "с");
        xAxis.getAxisLabel().fontProperty().set(FontManager.getFont(FontManager.FontWeight.MEDIUM,
                FontManager.FontSize.SMALL_FOURTEEN));
        xAxis.getTickLabelStyle().fontProperty().set(FontManager.getFont(FontManager.FontWeight.LIGHT,
                FontManager.FontSize.SMALL_TWELVE));
        xAxis.setAutoRanging(false);
        xAxis.set(0., xMax);
        //Объект оси Y и настройка подписей, масштаба построения
        DefaultNumericAxis yAxis = new DefaultNumericAxis("Значение", null);
        yAxis.getAxisLabel().fontProperty().set(FontManager.getFont(FontManager.FontWeight.MEDIUM,
                FontManager.FontSize.SMALL_FOURTEEN));
        yAxis.getTickLabelStyle().fontProperty().set(FontManager.getFont(FontManager.FontWeight.LIGHT,
                FontManager.FontSize.SMALL_TWELVE));
        double factor = 1.5;
        yAxis.setAutoRanging(false);
        yAxis.set(factor * min, factor * max);
        chart = new XYChart(xAxis, yAxis);
        //Настройка заголовка графика
        chart.setTitle(title);
        //Настройка видимости легенды
        chart.legendVisibleProperty().set(false);
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
