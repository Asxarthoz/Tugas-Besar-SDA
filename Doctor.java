
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Doctor {
    
    int ID;
    String name;
    String specialty;
    String loginTime;
    String logoutTime;
    Doctor next;
    boolean absen;
    String masukKerja;
    String selesaiKerja;

    public Doctor(int ID, String name, String specialty, String masuk, String keluar) {
        this.ID = ID;
        this.name = name;
        this.specialty = specialty;
        this.loginTime = "User belum log in!";
        this.logoutTime = "User belum log out!";
        this.absen = false;
        this.masukKerja = masuk;
        this.selesaiKerja = keluar;
    }

    public void doneAbsen(){
        this.absen = true;
    }

    public String getTime() {
        DateTimeFormatter formated = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime time = LocalDateTime.now();
        String forTime = time.format(formated);
        return forTime;
    }

    public void addTime(){
        DateTimeFormatter formated = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime time = LocalDateTime.now();
        this.loginTime = time.format(formated);
    }    

    public String getLoginTime(){
        return this.loginTime;
    }

    public void setLoginTime(String s){
        this.loginTime = s;
    }

    public void setLogOutTime(String s){
        this.logoutTime = s;
    }

    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.ID;
    }

    public String getSpeciality(){
        return this.specialty;
    }

    public String getLogOutTime(){
        return this.logoutTime;
    }

    public String getJamMasuk(){
        return this.masukKerja;
    }

    public String getJamSelesai() {
        return this.selesaiKerja;
    }
}
