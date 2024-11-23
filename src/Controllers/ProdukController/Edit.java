package Controllers.ProdukController;

import Models.ProdukModel;
import getter.Produk;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Edit {

    private final ProdukModel produkModel = new ProdukModel();
    private Produk produk;

    @FXML
    private TextField txtNamaProduk, txtHargaProduk;

    // @FXML
    // private TextField txtHargaProduk;

    // Method untuk menerima data produk
    public void setProdukData(Produk produk) {
        this.produk = produk;
        txtNamaProduk.setText(produk.getNama());
        txtHargaProduk.setText(String.valueOf(produk.getHarga()));
    }

    // Method untuk menyimpan perubahan
    @FXML
    public void simpanPerubahan() {
        String namaBaru = txtNamaProduk.getText();
        double hargaBaru = Double.parseDouble(txtHargaProduk.getText());

        boolean updated = produkModel.update(produk.getId(), namaBaru, hargaBaru);
        if (updated) {
            System.out.println("Produk berhasil diperbarui.");
            tutupForm();
        } else {
            System.out.println("Gagal memperbarui produk.");
        }
    }

    // Method untuk menutup form
    @FXML
    public void tutupForm() {
        Stage stage = (Stage) txtNamaProduk.getScene().getWindow();
        stage.close();
    }
}
