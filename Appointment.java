public class Appointment {
    int AppointmentID;
    Patient pasien;
    Doctor dokter;
    String waktu;
    Appointment next;

    void setDokter(StackDoctor stack, String nama){
        if(stack.stackKosong()){
            System.out.println("List dokter masih kosong!");
        } else {
            boolean ketemu = false;
            Doctor dokter = null;
            Doctor current = stack.head;
            while(current != null){
                if(current.name.equals(nama)){
                    dokter = current;
                    ketemu = true;
                }
                current = current.next;
            }
            if(ketemu) {
                this.dokter = dokter;
                System.out.println("Berhasil set dokter!");
            } else {
                System.out.println("Gagal menemukan dokter!");
            }
        }
    }

    void setAll(Patient pasien, Doctor dokter, String waktu, int AppointmentID){
        this.dokter = dokter;
        this.pasien = pasien;
        this.waktu = waktu;
        this.AppointmentID = AppointmentID;
    }

    void setDokter(Doctor dokter){
        this.dokter = dokter;
    }

    void setPasien(Patient pasien){
        this.pasien = pasien;
    }
    
    void setPasien(PatientList list, String nama){
        if(list.listKosong()){
            System.out.println("List pasien masih kosong!");
        } else {
            boolean ketemu = false;
            Patient pasien = null;
            Patient current = list.head;
            while(current != null){
                if(current.nama.equals(nama)){
                    pasien = current;
                    ketemu = true;
                }
                current = current.next;
            }
            if(ketemu) {
                this.pasien = pasien;
                System.out.println("Berhasil set pasien!");
            } else {
                System.out.println("Gagal menemukan pasien!");
            }
        }
    }

    public void setWaktu(String s){
        this.waktu = s;
    }

    
}
