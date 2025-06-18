public class Patient {
    int ID;
    String nama;
    int age;
    String alamat;
    String number;
    String riwayat;
    Patient next;

    public Patient(int ID, String nama, int age, String alamat, String number, String riwayat) {
        this.ID = ID;
        this.nama = nama;
        this.age = age;
        this.alamat = alamat;
        this.number = number;
        this.riwayat = riwayat;
        next = null;
    }
}
