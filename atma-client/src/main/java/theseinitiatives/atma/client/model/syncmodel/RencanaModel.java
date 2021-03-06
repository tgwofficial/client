package theseinitiatives.atma.client.model.syncmodel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RencanaModel {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("id_ibu")
    @Expose
    private String id_ibu;
    @SerializedName("id_trans")
    @Expose
    private String id_trans;

    public String getId_donor() {
        return id_donor;
    }

    public void setId_donor(String id_donor) {
        this.id_donor = id_donor;
    }

    public String getPenolong_lainnya() {
        return penolong_lainnya;
    }

    public void setPenolong_lainnya(String penolong_lainnya) {
        this.penolong_lainnya = penolong_lainnya;
    }

    public String getTempat_lainnya() {
        return tempat_lainnya;
    }

    public void setTempat_lainnya(String tempat_lainnya) {
        this.tempat_lainnya = tempat_lainnya;
    }

    public String getPendamping_lainnya() {
        return pendamping_lainnya;
    }

    public void setPendamping_lainnya(String pendamping_lainnya) {
        this.pendamping_lainnya = pendamping_lainnya;
    }

    public String getHub_pemiliktrans_lainnya() {
        return hub_pemiliktrans_lainnya;
    }

    public void setHub_pemiliktrans_lainnya(String hub_pemiliktrans_lainnya) {
        this.hub_pemiliktrans_lainnya = hub_pemiliktrans_lainnya;
    }

    public String getHub_donor_lainnya() {
        return hub_donor_lainnya;
    }

    public void setHub_donor_lainnya(String hub_donor_lainnya) {
        this.hub_donor_lainnya = hub_donor_lainnya;
    }

    @SerializedName("penolong_lainnya")
    @Expose
    private String penolong_lainnya;
    @SerializedName("tempat_lainnya")
    @Expose
    private String tempat_lainnya;
    @SerializedName("pendamping_lainnya")
    @Expose
    private String pendamping_lainnya;
    @SerializedName("id_hub_pemiliktrans_lainnyadonor")
    @Expose
    private String hub_pemiliktrans_lainnya;
    @SerializedName("hub_donor_lainnya")
    @Expose
    private String hub_donor_lainnya;



    @SerializedName("id_donor")
    @Expose
    private String id_donor;
    @SerializedName("penolong_persalinan")
    @Expose
    private String penolong_persalinan;
    @SerializedName("tempat_persalinan")
    @Expose
    private String tempat_persalinan;
    @SerializedName("pendamping_persalinan")
    @Expose
    private String pendamping_persalinan;
    @SerializedName("hubungan_ibu")
    @Expose
    private String hubungan_ibu;
    @SerializedName("name_pendonor")
    @Expose
    private String name_pendonor;
    @SerializedName("pemilik_kendaraan")
    @Expose
    private String pemilik_kendaraan;
    @SerializedName("hubungan_pendonor")
    @Expose
    private String hubungan_pendonor;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("location_id")
    @Expose
    private String location_id;
    @SerializedName("update_id")
    @Expose
    private String update_id;
    @SerializedName("is_send")
    @Expose
    private String isSend;
    @SerializedName("is_sync")
    @Expose
    private String isSync;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_ibu() {
        return id_ibu;
    }

    public void setId_ibu(String id_ibu) {
        this.id_ibu = id_ibu;
    }

    public String getId_trans() {
        return id_trans;
    }

    public void setId_trans(String id_trans) {
        this.id_trans = id_trans;
    }

    public String getPenolong_persalinan() {
        return penolong_persalinan;
    }

    public void setPenolong_persalinan(String penolong_persalinan) {
        this.penolong_persalinan = penolong_persalinan;
    }

    public String getTempat_persalinan() {
        return tempat_persalinan;
    }

    public void setTempat_persalinan(String tempat_persalinan) {
        this.tempat_persalinan = tempat_persalinan;
    }

    public String getPendamping_persalinan() {
        return pendamping_persalinan;
    }

    public void setPendamping_persalinan(String pendamping_persalinan) {
        this.pendamping_persalinan = pendamping_persalinan;
    }

    public String getHubungan_ibu() {
        return hubungan_ibu;
    }

    public void setHubungan_ibu(String hubungan_ibu) {
        this.hubungan_ibu = hubungan_ibu;
    }

    public String getName_pendonor() {
        return name_pendonor;
    }

    public void setName_pendonor(String name_pendonor) {
        this.name_pendonor = name_pendonor;
    }

    public String getPemilik_kendaraan() {
        return pemilik_kendaraan;
    }

    public void setPemilik_kendaraan(String pemilik_kendaraan) {
        this.pemilik_kendaraan = pemilik_kendaraan;
    }

    public String getHubungan_pendonor() {
        return hubungan_pendonor;
    }

    public void setHubungan_pendonor(String hubungan_pendonor) {
        this.hubungan_pendonor = hubungan_pendonor;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(String update_id) {
        this.update_id = update_id;
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
