public class PatientList {

    Patient head;

    public boolean listKosong(){
        if(head == null){
            return true;
        } else {
            return false;
        }
    }

    public void addPatient(int ID, String nama, int age, String alamat, String number, String riwayat){
        if(head == null){
            Patient n = new Patient(ID, nama, age, alamat, number, riwayat);
            head = n;
        } else{
            Patient current = head;
            while(current.next!=null){
                current = current.next;
            }
            current.next = new Patient(ID, nama, age, alamat, number, riwayat);
        }
    }

    public int getLastPatientID(){
        if(head == null){
            System.out.println("List Pasien Masih Kosong!");
            return 0;
        } else{
            int ID;
            Patient current = head;

            while(current.next != null){
                current = current.next;
            }

            ID = current.ID;
            return ID;            
        }
    }

    public void removePatientById(int n){
        if(head == null){
            System.err.println("List pasien masih kosong!");
        } else if(head.ID == n){
            System.err.println("Data pasien berhasil dihapus!");
        }
        boolean ketemu = false;
        Patient current = head;

        while(current.next!=null){
            if(current.next.ID == n){
                current.next = current.next.next;
                ketemu = true;
            }
            current = current.next;
        }

        if(ketemu){
            System.err.println("Data pasien berhasil dihapus!");
        } else{
            System.err.println("Data pasien tidak ditemukan!");
        }
    }

    public Patient getPatientByName(String n){
        boolean ketemu = false;
        Patient current = head;
        Patient pasien = null;
        if(head == null) {
            System.out.println("List pasien masih kosong!");
        } else {
            while(current !=null){
                if(current.nama.equals(n)){
                    ketemu = true;
                    break;
                }
                current = current.next;
            }

            if(ketemu){
                pasien = current;
            } else{
                // System.err.println("Data pasien atas nama" + n + "tidak ditemukan!");
            }
        }
        return pasien;
    }

    public void findPatientByName(String n){
        boolean ketemu = false;
        Patient current = head;
        if(head == null) {
            System.out.println("List pasien masih kosong!");
        } else {
            while(current!=null){
                if(current.nama.equals(n)){
                    ketemu = true;
                    break;
                }
                current = current.next;
            }

            if(ketemu){
                System.err.println("Data pasien berhasil ditemukan!");
                System.err.println("ID : " + current.ID + "\nNama : " + current.nama + "\nUmur : " + current.age + "\nAlamat : " + current.alamat + "\nNomor Telepon : " + current.number + "\n" + "Riwayat Penyakit : " + current.riwayat + "\n");

            } else{
                System.err.println("Data pasien tidak ditemukan!");
            }
        }
    }

    public void displayPatient(){
        if(head == null){
            System.err.println("List pasien masih kosong!");
        } else {
            Patient current = head;

            while (current != null) { 
            System.err.println("ID : " + current.ID + "\nNama : " + current.nama + "\nUmur : " + current.age + "\nAlamat : " + current.alamat + "\nNomor Telepon : " + current.number + "\n" + "Riwayat Penyakit : " + current.riwayat + "\n");
            current = current.next;
            
            }
        }
        

    }
    
}
