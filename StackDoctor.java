public class StackDoctor {
    Doctor head;

    public void loginDoctor(Doctor d){
        Doctor current = head;
        boolean sama = false;
        while(current != null){
            if(current.ID == d.ID){
                sama = true;
            }
            current = current.next;
        }
        if(head == null){
            head = d;
        } else {
            if(sama){
                System.out.println("Dokter sudah ada di list!");
            } else{
                d.next = head;
                head = d;
            }
            
        }
    }

    public void addDoctor(Doctor d) {
        if(head == null) {
            head = d;
        } else {
            d.next = head;
            head = d;
        }
    }

    public void setLogOut(String name){
        Doctor current = head;
        Doctor d = null;
        while(current != null){
            if(current.name.equals(name)){
                d = current;
            }
            current = current.next;
        }
        d.setLogOutTime(d.getTime());
    }

    public void outAbsenDoctor(int id){
        if(head == null){
            System.err.println("List dokter masih kosong!");
        } else if(head.ID == id){
            head = head.next;
            System.err.println("Berhasil log out!");
        } else{
            boolean ketemu = false;
            Doctor current = head;

            while (current.next != null) { 
                if(current.next.ID == id){
                    ketemu = true;
                    current.next = current.next.next;
                }
                current = current.next;
            }
            if(ketemu){
                System.err.println("Berhasil log out!");
            } else{
                System.err.println("Data tidak ditemukan!");
            }
        }
    }

    public void viewLastLoggedDoctor(){
        if(head == null) {
            System.err.println("List dokter masih kosong!");
        } else {
            System.out.println("Dokter yang terakhir login adalah :\nNama : " + head.name + "\nWaktu : " + head.getLoginTime());
        }
    }

    public void listDokter(){
        Doctor current = head;
        while (current!=null) { 
            System.out.println("ID : " + current.ID);
            System.out.println("Nama : " + current.name);
            System.out.println("Spesialitas : " + current.specialty);
            System.out.println("Jam Masuk : " + current.masukKerja);
            System.out.println("Jam Keluar : " + current.selesaiKerja + "\n");
            current = current.next;
        }
    }

    public void getAllLoggedInDoctors(){
        if(head == null){
            System.out.println("List Dokter masih kosong!");
        } else {
            Doctor current = head;
        
            while (current!= null) { 
                String waktuEdited = current.loginTime;
                System.err.println("Nama : " + current.name + "\nWaktu login : " + waktuEdited + "\nWaktu logout : " + current.logoutTime + "\n");
                current = current.next;
            }
        }
    }

    public Doctor getDoctor(String name){
        boolean ketemu = false;
        Doctor dokter = null;
        if(head == null) {
            System.out.println("List dokter masih kosong!");
        } else {
            Doctor current = head;
            while(current != null){
                if(current.name.equals(name)){
                    dokter = current;
                    ketemu = true;
                }
                current = current.next;
            }
        }
        if(!ketemu) {
            System.out.println("Nama dokter tidak ditemukan!");
        } 
        return dokter;
    }

    boolean stackKosong(){
        if(head == null){
            return true;
        } else {
            return false;
        }
    }

    int getLastDoctorID(){
        return head.ID;
    }
}
