package Controllers.ProdukController;

import Models.ProdukModel;
import getter.Produk;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class Create { // ubah menjadi publik
    @FXML
    private TextField txtNamaProduk;

    @FXML
    private TextField txtHargaProduk;

    private ProdukModel produkModel = new ProdukModel();

    @FXML
    private void simpanProduk() {
        String nama = txtNamaProduk.getText().trim();
        String hargaText = txtHargaProduk.getText().trim();

        if (nama.isEmpty() || hargaText.isEmpty()) {
            System.out.println("Semua field harus diisi!");
            return;
        }

        try {
            double harga = Double.parseDouble(hargaText);

            Produk produkBaru = new Produk(0, nama, harga);
            boolean berhasil = produkModel.create(produkBaru);

            if (berhasil) {
                System.out.println("Produk berhasil ditambahkan.");
                Stage stage = (Stage) txtNamaProduk.getScene().getWindow();
                stage.close();
            } else {
                System.out.println("Gagal menambahkan produk.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Harga harus berupa angka.");
        }
    }

    @FXML
    private void batal() {
        Stage stage = (Stage) txtNamaProduk.getScene().getWindow();
        stage.close();
    }
}
