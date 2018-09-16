package tgwofficial.atma.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankdarahData {


    @SerializedName("kapasitas_kendaraan")
    @Expose
    private String kapasitas_kendaraan;
    @SerializedName("jenis_kendaraan")
    @Expose
    private String jenis_kendaraan;
    @SerializedName("gubug")
    @Expose
    private String gubug;
    @SerializedName("profesi")
    @Expose
    private String profesi;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;



    @SerializedName("is_send")
    @Expose
    private String isSend;
    @SerializedName("is_sync")
    @Expose
    private String isSync;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getKapasitas_kendaraan() {
        return kapasitas_kendaraan;
    }

    public void setKapasitas_kendaraan(String kapasitas_kendaraan) {
        this.kapasitas_kendaraan = kapasitas_kendaraan;
    }

    public String getJenis_kendaraan() {
        return jenis_kendaraan;
    }

    public void setJenis_kendaraan(String jenis_kendaraan) {
        this.jenis_kendaraan = jenis_kendaraan;
    }

    public String getGubug() {
        return gubug;
    }

    public void setGubug(String gubug) {
        this.gubug = gubug;
    }

    public String getProfesi() {
        return profesi;
    }

    public void setProfesi(String profesi) {
        this.profesi = profesi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
