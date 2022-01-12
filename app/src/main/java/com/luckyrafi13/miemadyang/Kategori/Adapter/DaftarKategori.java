package com.luckyrafi13.miemadyang.Kategori.Adapter;

public class DaftarKategori {
    private String id_kategori,nama_kategori,deskripsi_kategori;

    public DaftarKategori() {
    }

    public DaftarKategori(String id_kategori, String nama_kategori, String deskripsi_kategori) {
        this.id_kategori = id_kategori;
        this.nama_kategori = nama_kategori;
        this.deskripsi_kategori = deskripsi_kategori;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public String getDeskripsi_kategori() {
        return deskripsi_kategori;
    }

    public void setDeskripsi_kategori(String deskripsi_kategori) {
        this.deskripsi_kategori = deskripsi_kategori;
    }
}
