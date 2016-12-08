package dronegraph;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;

import gdrone.GeneticDrone;
import gdrone.Population;
import gdrone.Route;
import gdrone.Parcel;
import javafx.application.Application;

public class JavafxTests extends Application {

	final NumberAxis xAxis = new NumberAxis();
	final NumberAxis yAxis = new NumberAxis();

	final XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();

	// final XYChart.Series<String, Number> series2 =
	// new XYChart.Series<String, Number>();
	// final XYChart.Series<String, Number> series3 =
	// new XYChart.Series<String, Number>();
	// final StackedBarChart<String, Number> sbc =
	// new StackedBarChart<String, Number>(xAxis, yAxis);

	@Override
	public void start(Stage stage) {
		// Start
		GeneticDrone.run();
		// Polling loop
		while (GeneticDrone.finalPop == null) {
		}

		// base chart
		// final BarChart<String, Number> barChart = new BarChart<String,
		// Number>(xAxis, yAxis);
		// barChart.setLegendVisible(false);
		// barChart.setAnimated(false);

		xAxis.setLabel("xAxis");
		yAxis.setLabel("yAxis");

		// overlay chart
		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
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

		// sbc.getData().addAll(createbar1Series(), createbar2Series(),
		// createbar3Series());
		lineChart.getData().addAll(createChartSeries());

		FlowPane pane = new FlowPane(Orientation.HORIZONTAL);
		pane.getChildren().addAll(lineChart);

		Scene scene = new Scene(pane);

		stage.setScene(scene);
		stage.show();
	}

	private Series<Number, Number> createChartSeries() {
		Population pop = GeneticDrone.finalPop;
		Route best = pop.getFittest();
		ArrayList<Parcel> parcelRoute = best.getParcelArrayList();

		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		
		for(int i = 0; i < best.routeSize(); i++) {
			int x = parcelRoute.get(i).getX();
			int y = parcelRoute.get(i).getY();
			series.getData().add(new XYChart.Data<Number,Number>(x, y));
		}

		return series;
	}

	public static void main(String[] args) {
		launch(args);
	}
}