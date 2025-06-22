import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class JavaFXInterface extends Application {


    private ListView<String> outputList;
    private LineChart<Number, Number> convergenceChart;
    Button runButton,saveButton;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simulated Annealing: Rastrigin Optimizer");

         runButton = new Button("Run Optimization");
         saveButton = new Button("Save Chart");

        saveButton.setDisable(true);

        outputList = new ListView<>();
        outputList.setPrefHeight(250);
        outputList.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 14px;");


        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Iteration");
        yAxis.setLabel("Best Value");

        convergenceChart = new LineChart<>(xAxis, yAxis);
        convergenceChart.setTitle("Convergence Curve");

        runButton.setOnAction(e -> runOptimization());
        saveButton.setOnAction(e -> saveChart());

        VBox layout = new VBox(10, runButton, saveButton, outputList, convergenceChart);
        layout.setStyle("-fx-padding: 15;");
        Scene scene = new Scene(layout, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("dark-theme.css").toExternalForm());

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void runOptimization() {
        outputList.getItems().clear();
        convergenceChart.getData().clear();

        Result result = SimulatedAnnealingRastrigin.optimize();

        outputList.getItems().add(String.format("Best value: %.4f", result.bestValue));
        outputList.getItems().add("Best solution (x values):");
        for (int i = 0; i < result.bestX.length; i++) {
            outputList.getItems().add(String.format("x[%d] = %.4f", i, result.bestX[i]));
        }

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Best value over time");

        for (int i = 0; i < result.history.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, result.history.get(i)));
        }

        convergenceChart.getData().add(series);
        saveButton.setDisable(false);

    }

    private void saveChart() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chart as PNG");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            WritableImage image = convergenceChart.snapshot(new SnapshotParameters(), null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Chart saved successfully.");
                alert.showAndWait();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save chart.");
                alert.showAndWait();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}