package Models;

import hellofx1.Produk;
import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdukModel {

    private static Connection connection = Database.getConnection();

    public List<Produk> getSemuaProduk() {
        List<Produk> produkList = new ArrayList<>();
        String sql = "SELECT * FROM produk";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Membuat objek ProdukController berdasarkan data dari database
                Produk produk = new Produk(
                        rs.getInt("id"),
                        rs.getString("nama_produk"),
                        rs.getDouble("harga")
                );
                produkList.add(produk); // Menambahkan produk ke dalam daftar
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produkList;
    }

    public boolean tambahProduk(Produk produk) {
        String sql = "INSERT INTO produk (nama_produk, harga) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, produk.getNama());
            ps.setDouble(2, produk.getHarga());
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0; // Mengembalikan true jika data berhasil ditambahkan
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProduk(int id, String namaBaru, double hargaBaru) {
        String sql = "UPDATE produk SET nama_produk = ?, harga = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, namaBaru);
            ps.setDouble(2, hargaBaru);
            ps.setInt(3, id);
    
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Mengembalikan true jika data berhasil diperbarui
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public boolean hapusProduk(int id) {
        String sql = "DELETE FROM produk WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0; // Mengembalikan true jika data berhasil dihapus
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
