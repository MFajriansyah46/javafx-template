package hellofx1;

public class Produk {
    private int id;
    private String nama_produk;
    private double harga;

    public Produk(int id, String nama_produk, double harga) {
        this.id = id;
        this.nama_produk = nama_produk;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama_produk;
    }

    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return "Produk [id=" + id + ", nama=" + nama_produk + ", harga=" + harga + "]";
    }
}
