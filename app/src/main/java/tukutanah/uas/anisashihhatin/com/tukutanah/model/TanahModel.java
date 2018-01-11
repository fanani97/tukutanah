package tukutanah.uas.anisashihhatin.com.tukutanah.model;

public class TanahModel { //digunakan untuk menyimpan data setelah mengambil dari API
    private String id;
    private String soilImage;
    private String soilName;
    private String soilPrice;
    private String soilAddress;
    private String soilPhone;
    private String soilLarge;
    private String soilCity;
    private String soilSertificate;
    private String soilDescription;
    private double soilLat;
    private double soilLng;
    private String soilDistrict;

    public TanahModel(String id, String soilImage, String soilName, String soilPrice, String soilAddress, String soilPhone, String soilLarge,
                      String soilCity, String soilSertificate, String soilDescription, double soilLat, double soilLng, String soilDistrict) {
        this.id = id;
        this.soilImage = soilImage;
        this.soilName = soilName;
        this.soilPrice = soilPrice;
        this.soilAddress = soilAddress;
        this.soilPhone = soilPhone;
        this.soilLarge = soilLarge;
        this.soilCity = soilCity;
        this.soilSertificate = soilSertificate;
        this.soilDescription = soilDescription;
        this.soilLat = soilLat;
        this.soilLng = soilLng;
        this.soilDistrict = soilDistrict; //digunakan untuk memasukkan data kedalam list berdasarkan urutan pendeklarasian object disamping
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoilImage() {
        return soilImage;
    } //digunakan untuk mengambil data dari image dan get seterusnya digunakan untuk mengambil data sesuai dengan fungsinya

    public void setSoilImage(String soilImage) {
        this.soilImage = soilImage;
    } //digunakan untuk set data image

    public String getSoilName() {
        return soilName;
    }

    public void setSoilName(String soilName) {
        this.soilName = soilName;
    }

    public String getSoilPrice() {
        return soilPrice;
    }

    public void setSoilPrice(String soilPrice) {
        this.soilPrice = soilPrice;
    }

    public String getSoilAddress() {
        return soilAddress;
    }

    public void setSoilAddress(String soilAddress) {
        this.soilAddress = soilAddress;
    }

    public String getSoilCity() {
        return soilCity;
    }

    public void setSoilCity(String soilCity) {
        this.soilCity = soilCity;
    }

    public String getSoilSertificate() {
        return soilSertificate;
    }

    public void setSoilSertificate(String soilSertificate) {
        this.soilSertificate = soilSertificate;
    }

    public String getSoilDescription() {
        return soilDescription;
    }

    public void setSoilDescription(String soilDescription) {
        this.soilDescription = soilDescription;
    }

    public String getSoilLarge() {
        return soilLarge;
    }

    public void setSoilLarge(String soilLarge) {
        this.soilLarge = soilLarge;
    }

    public String getSoilPhone() {
        return soilPhone;
    }

    public void setSoilPhone(String soilPhone) {
        this.soilPhone = soilPhone;
    }

    public double getSoilLat() {
        return soilLat;
    }

    public void setSoilLat(double soilLat) {
        this.soilLat = soilLat;
    }

    public double getSoilLng() {
        return soilLng;
    }

    public void setSoilLng(double soilLng) {
        this.soilLng = soilLng;
    }

    public String getSoilDistrict() {
        return soilDistrict;
    }

    public void setSoilDistrict(String soilDistrict) {
        this.soilDistrict = soilDistrict;
    }
}
