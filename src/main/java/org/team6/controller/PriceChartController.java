package org.team6.controller;

import java.util.Arrays;

import org.team6.view.PageStarter;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;

public class PriceChartController {
    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private BarChart<String, Number> priceChart;

    @FXML
    public void initialize() {
        initiateBarChartData();  // This runs when the FXML is loaded
    }

    @FXML
    private void handleHomePageButtonOnAction() {
        PageStarter.switchToHomePage();
    }

    @FXML
    private void handleEnergyButtonOnAction() {
        PageStarter.switchToEnergyPage();
    }

    @FXML
    private void handleSystemSettingsButton() {
        PageStarter.switchToSystemSettings();
    }

    private void initiateBarChartData() {
        priceChart.setTitle("Price Chart");

        xAxis.setCategories(FXCollections.observableArrayList(
                Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")));
        xAxis.setLabel("Month");

        yAxis.setLabel("Price (öre/kWh)");
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(1000);
        yAxis.setTickUnit(100);
        xAxis.setTickLabelFill(Color.WHITE);
        yAxis.setTickLabelFill(Color.WHITE);

        // Remove grid lines
        priceChart.setHorizontalGridLinesVisible(false); // Remove horizontal grid lines
        priceChart.setVerticalGridLinesVisible(false);

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("2024");
        series1.getData().add(new XYChart.Data<>("Jan", 300));
        series1.getData().add(new XYChart.Data<>("Feb", 250));
        series1.getData().add(new XYChart.Data<>("Mar", 200));
        series1.getData().add(new XYChart.Data<>("Apr", 150));


        priceChart.getData().add(series1);
    }

    @FXML
    private void handleBackToEnergyUsagePage(){
        PageStarter.switchToEnergyUsagePane();
    }
}