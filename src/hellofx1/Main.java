package hellofx1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;

public class Main extends Application {

    private static Connection connection = Database.getConnection();
    private static ProdukModel produkModel = new ProdukModel(connection);

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainscene.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setMainWindow(primaryStage);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }


    public static void getData(int id){

        Produk produk = produkModel.find(id);
        if (produk != null) {
            System.out.println("Produk ditemukan: " + produk);
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    public static void tambahProduk(String nama, double harga){

        Produk produkBaru = new Produk(0, nama, harga);

        // Tambah data ke database
        if (produkModel.tambahProduk(produkBaru)) {
            System.out.println("Produk berhasil ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan produk.");
        }
    }

    public static void hapus(int idProduk){
    
        // Hapus produk berdasarkan ID
        if (produkModel.hapusProduk(idProduk)) {
            System.out.println("Produk dengan ID " + idProduk + " berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus produk dengan ID " + idProduk + ". Mungkin ID tidak ditemukan.");
        }

        Database.initializeShutdownHook();
    }

    public static void main(String[] args) {

        
        // launch(args);

    
        getData(4);

        Database.initializeShutdownHook();
    }
}




class ProdukController {

}
