// Mahasiswa.java

package com.example.model;
public class cerc {
//! Properties sesuai dengan tabel mahasiswa
private String id_minat;
private String nama_minat;
private String id_ketua;
private String nama_ketua;
private String nim_ketua;
private String angkatan_ketua;
private String id_anggota;
private String nama_anggota;
private String nim_anggota;
private String angkatan_anggota;
private String id_search;
private String deleted;
//! Setter dan Getter Properties
public String deleted(){
    return deleted;
}

public void getDeleted(String deleted){
    this.deleted = deleted;
}

public String getId_minat() {
    return id_minat;
}
public void setId_minat(String id_minat) {
    this.id_minat = id_minat;
}
public String getId_ketua() {
    return id_ketua;
}
public void setId_ketua(String id_ketua) {
    this.id_ketua = id_ketua;
}

public String getNama_minat() {
return nama_minat;
}
public void setNama_minat(String nama_minat) {
this.nama_minat = nama_minat;
}
public String getNim_ketua() {
return nim_ketua;
}
public void setNim_ketua(String nim_ketua) {
this.nim_ketua = nim_ketua;
}
public String getNama_ketua() {
    return nama_ketua;
    }
public void setNama_ketua(String nama_ketua) {
this.nama_ketua = nama_ketua;
 }
public String getAngkatan_ketua() {
return angkatan_ketua;
}
public void setAngkatan_ketua(String angkatan_ketua) {
this.angkatan_ketua = angkatan_ketua;
}
public String getId_anggota() {
    return id_anggota;
}
public void setId_anggota(String id_anggota) {
    this.id_anggota = id_anggota;
}
public String getNama_anggota() {
return nama_anggota;
}
public void setNama_anggota(String nama_anggota) {
this.nama_anggota = nama_anggota;
}

public String getNim_anggota() {
    return nim_anggota;
}
public void setNim_anggota(String nim_anggota) {
    this.nim_anggota = nim_anggota;
}

public String getAngkatan_anggota() {
    return angkatan_anggota;
}
public void setAngkatan_anggota(String angkatan_anggota) {
    this.angkatan_anggota = angkatan_anggota;
}
public String getId_search() {
    return id_search;
}
public void setId_search(String id_search) {
    this.id_search = id_search;
}
}