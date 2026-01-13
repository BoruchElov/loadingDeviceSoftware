package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public final class PdfViewer {

    // Можно менять качество (DPI): 120-200 обычно ок; 250+ тяжелее
    private static final float DEFAULT_DPI = 160f;

    private PdfViewer() {}

    /**
     * Открывает всплывающее окно и показывает тестовый PDF из ресурсов:
     * /pdf/test.pdf
     */
    public static void openPDFFile() {
        openPdfFromResources("/pdf/test.pdf", "PDF Viewer");
    }

    /**
     * Если позже захочешь открывать конкретный ресурс.
     */
    public static void openPdfFromResources(String resourcePath, String title) {
        InputStream is = PdfViewer.class.getResourceAsStream(resourcePath);
        if (is == null) {
            showError("Не найден PDF в resources: " + resourcePath);
            return;
        }

        PDDocument document;
        try {
            document = PDDocument.load(is);
        } catch (IOException e) {
            showError("Не удалось открыть PDF: " + e.getMessage());
            return;
        }

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        PDFRenderer renderer = new PDFRenderer(document);

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        ScrollPane scrollPane = new ScrollPane(imageView);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Button btnPrev = new Button("←");
        Button btnNext = new Button("→");
        Label lblPage = new Label();
        Slider zoomSlider = new Slider(0.5, 2.5, 1.0);
        zoomSlider.setPrefWidth(180);
        Label lblZoom = new Label("Zoom");

        HBox controls = new HBox(10, btnPrev, btnNext, new Separator(), lblPage, new Pane(), lblZoom, zoomSlider);
        HBox.setHgrow(controls.getChildren().get(5), Priority.ALWAYS);
        controls.setAlignment(Pos.CENTER_LEFT);
        controls.setPadding(new Insets(10));

        BorderPane root = new BorderPane(scrollPane);
        root.setBottom(controls);

        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);

        int pageCount = document.getNumberOfPages();
        int[] currentPage = {0}; // чтобы менять внутри лямбд

        Runnable render = () -> {
            try {
                float zoom = (float) zoomSlider.getValue();
                BufferedImage bim = renderer.renderImageWithDPI(currentPage[0], DEFAULT_DPI * zoom);
                WritableImage fxImg = SwingFXUtils.toFXImage(bim, null);
                imageView.setImage(fxImg);

                lblPage.setText((currentPage[0] + 1) + " / " + pageCount);
                btnPrev.setDisable(currentPage[0] <= 0);
                btnNext.setDisable(currentPage[0] >= pageCount - 1);
            } catch (IOException e) {
                showError("Ошибка рендера страницы: " + e.getMessage());
            }
        };

        btnPrev.setOnAction(a -> { currentPage[0]--; render.run(); });
        btnNext.setOnAction(a -> { currentPage[0]++; render.run(); });
        zoomSlider.valueProperty().addListener((obs, ov, nv) -> render.run());

        // Закрытие документа при закрытии окна (важно!)
        stage.setOnCloseRequest(e -> {
            try { document.close(); } catch (IOException ignored) {}
        });

        // Первый рендер
        render.run();
        stage.showAndWait();
    }

    private static void showError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("PDF Viewer Error");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
