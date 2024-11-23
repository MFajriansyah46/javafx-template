package Controllers.ProdukController;

import Models.ProdukModel;
import getter.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Index {

    private final ProdukModel produkModel = new ProdukModel();

    @FXML
    private TableView<Produk> tableProduk;

    @FXML
    private TableColumn<Produk, Integer> colId;

    @FXML
    private TableColumn<Produk, String> colNama;

    @FXML
    private TableColumn<Produk, Double> colHarga;

    // Inisialisasi tabel
    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        refreshTable();

        // Tambahkan event handler untuk klik dua kali pada tabel
        tableProduk.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Deteksi klik dua kali
                Produk selected = tableProduk.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    editProduk(selected); // Buka form edit
                }
            }
        });
    }

    // Method untuk memuat data ke dalam tabel
    public void refreshTable() {
        ObservableList<Produk> data = FXCollections.observableArrayList(produkModel.getAll());
        tableProduk.setItems(data);
    }

    // Method untuk membuka form tambah produk
    @FXML
    public void tambahProduk() {
        try {
            // Load formAdd.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create.fxml"));
            Parent root = loader.load();

            // Buka dialog
            Stage stage = new Stage();
            stage.setTitle("Tambah Produk Baru");
            stage.initModality(Modality.APPLICATION_MODAL); // Membuat dialog modal
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Tunggu hingga dialog ditutup

            // Setelah form ditutup, refresh tabel
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method untuk mengedit produk
    @FXML
    public void editProduk(Produk selected) {
        try {
            // Load formEdit.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/edit.fxml"));
            Parent root = loader.load();

            // Mengakses controller formEdit
            Edit controller = loader.getController();
            controller.setProdukData(selected); // Kirim data produk yang akan diedit

            // Buka dialog
            Stage stage = new Stage();
            stage.setTitle("Edit Produk");
            stage.initModality(Modality.APPLICATION_MODAL); // Membuat dialog modal
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Tunggu hingga dialog ditutup

            // Setelah form ditutup, refresh tabel
            refreshTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method untuk menghapus produk
    public void hapusProduk() {
        Produk selected = tableProduk.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean deleted = produkModel.delete(selected.getId());
            if (deleted) {
                System.out.println("Produk berhasil dihapus.");
                refreshTable();
            } else {
                System.out.println("Gagal menghapus produk.");
            }
        } else {
            System.out.println("Pilih produk yang ingin dihapus.");
        }
    }
}