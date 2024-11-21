package hellofx1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdukModel {
    private Connection connection;

    public ProdukModel(Connection connection) {
        this.connection = connection;
    }

    public Produk find(int id) {
        Produk produk = null;
        String sql = "SELECT * FROM produk WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                produk = new Produk(
                        rs.getInt("id"),
                        rs.getString("nama_produk"),
                        rs.getDouble("harga")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produk;
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