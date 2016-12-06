package Genetic-Drone;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javafx.application.Application;

public class JavafxTests extends Application {
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    final XYChart.Series<String, Number> series1 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series2 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series3 =
            new XYChart.Series<String, Number>();
    final StackedBarChart<String, Number> sbc =
            new StackedBarChart<String, Number>(xAxis, yAxis);

    @Override
    public void start(Stage stage) {


        // base chart
        final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        barChart.setLegendVisible(false);
        barChart.setAnimated(false);

        xAxis.setLabel("Path");
        yAxis.setLabel("Distance");

        // overlay chart
        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setLegendVisible(false);
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(true);
        lineChart.setAlternativeRowFillVisible(false);
        lineChart.setAlternativeColumnFillVisible(false);
        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.setVerticalGridLinesVisible(false);
        lineChart.getXAxis().setVisible(false);
        lineChart.getYAxis().setVisible(false);
        lineChart.getStylesheets().addAll(getClass().getResource("chart.css").toExternalForm());

        sbc.getData().addAll(createbar1Series(), createbar2Series(), createbar3Series());
        lineChart.getData().add( createChartSeries());

        FlowPane pane = new FlowPane(Orientation.HORIZONTAL);
        pane.getChildren().addAll(lineChart, sbc);

        Scene scene = new Scene(pane, 1000, 600);

        stage.setScene(scene);
        stage.show();
    }

    private XYChart.Series<String,Number> createChartSeries() {

        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        series.getData().add(new XYChart.Data<String,Number>("Node 1", 25601.34));
        series.getData().add(new XYChart.Data<String,Number>("Node 2", 20148.82));
        series.getData().add(new XYChart.Data<String,Number>("Node 3", 10000));
        series.getData().add(new XYChart.Data<String,Number>("Node 4", 35407.15));
        series.getData().add(new XYChart.Data<String,Number>("Node 4", 12000));

        return series;
    }
    private XYChart.Series<String,Number> createbar1Series() {
        series1.setName("2003");
        series1.getData().add(new XYChart.Data<String, Number>(austria, 25601.34));
        series1.getData().add(new XYChart.Data<String, Number>(brazil, 20148.82));
        series1.getData().add(new XYChart.Data<String, Number>(france, 10000));
        series1.getData().add(new XYChart.Data<String, Number>(italy, 35407.15));
        series1.getData().add(new XYChart.Data<String, Number>(usa, 12000));

        return series1;
    }
    private XYChart.Series<String,Number> createbar2Series() {
        series2.setName("2004");
        series2.getData().add(new XYChart.Data<String, Number>(austria, 57401.85));
        series2.getData().add(new XYChart.Data<String, Number>(brazil, 41941.19));
        series2.getData().add(new XYChart.Data<String, Number>(france, 45263.37));
        series2.getData().add(new XYChart.Data<String, Number>(italy, 117320.16));

        return series2;
    }
    private XYChart.Series<String,Number> createbar3Series() {
        series3.setName("2005");
        series3.getData().add(new XYChart.Data<String, Number>(austria, 45000.65));
        series3.getData().add(new XYChart.Data<String, Number>(brazil, 44835.76));
        series3.getData().add(new XYChart.Data<String, Number>(france, 18722.18));
        series3.getData().add(new XYChart.Data<String, Number>(italy, 17557.31));
        series3.getData().add(new XYChart.Data<String, Number>(usa, 92633.68));

        return series3;
    }


    public static void main(String[] args) {
        launch(args);
    }
}