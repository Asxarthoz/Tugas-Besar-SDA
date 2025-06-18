import java.io.*;
import java.util.Scanner;

public class Menu {
    PatientList patientList = readPatientList();
    BST bstPasien = new BST();
    StackDoctor doctorStack = readDoctorList("Data/listDokter.txt");
    StackDoctor absenDokter = bacaFileAbsen();
    Map mapQueueDokterReq = readFileAppointmentReq(doctorStack, patientList);
    Map mapQueueDokter = readFileAppointment(doctorStack, patientList);
    AppointmentQueue reqAppDokter;
    AppointmentQueue appDokter;    
    Scanner input = new Scanner(System.in);
    int userType;
    int logOrReg;
    String userName;
    Doctor userDokter;
    Patient userPatient;
    
    //=========================READ FILE=========================

    Map readFileAppointmentReq(StackDoctor stack, PatientList plist) {
        Map map = new Map();
        String filename = "Appointment/request.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) continue;

                String dokterName = parts[0];
                Doctor dokter = stack.getDoctor(dokterName);

                if (dokter == null){

                    continue;
                } 

                AppointmentQueue queue = new AppointmentQueue();

                for (int i = 1; i < parts.length; i++) {
                    String[] data = parts[i].split("/");
                    if (data.length != 3) continue;

                    String patientName = data[0];
                    String waktu = data[1];
                    int ID = Integer.parseInt(data[2]);

                    Patient pasien = plist.getPatientByName(patientName);
                    if (pasien != null) {
                        Appointment ap = new Appointment();
                        ap.setAll(pasien, dokter, waktu, ID);
                        queue.enqueue(ap);
                    }
                }

                map.put(dokterName, queue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    Map readFileAppointment(StackDoctor stack, PatientList plist) {
        Map map = new Map();
        String filename = "Appointment/appointment.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length < 2) continue;

                String dokterName = parts[0];
                Doctor dokter = stack.getDoctor(dokterName);
                if (dokter == null) continue;

                AppointmentQueue queue = new AppointmentQueue();

                for (int i = 1; i < parts.length; i++) {
                    String[] data = parts[i].split("/");
                    if (data.length != 3) continue;

                    String patientName = data[0];
                    String waktu = data[1];
                    int ID = Integer.parseInt(data[2]);

                    Patient pasien = plist.getPatientByName(patientName);
                    if (pasien != null) {
                        Appointment ap = new Appointment();
                        ap.setAll(pasien, dokter, waktu, ID);
                        queue.enqueue(ap);
                    }
                }

                map.put(dokterName, queue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }


    StackDoctor bacaFileAbsen(){
        String file = "Data/absenDokter.txt";
        String baris;
        StackDoctor listDokter = new StackDoctor();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while((baris = br.readLine()) != null) {
                String[] part = baris.split("/");
                int ID = Integer.parseInt(part[0]);
                Doctor dokter = new Doctor(ID, part[1], part[2], null, null);
                dokter.setLoginTime(part[3]);
                if(part.length == 5){
                    dokter.setLogOutTime(part[4]);
                }
                listDokter.loginDoctor(dokter);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return listDokter;
    }

    StackDoctor readDoctorList(String file){
        String baris;
        StackDoctor stDokter = new StackDoctor();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while((baris = br.readLine()) != null){
                String[] part = baris.split("/");
                int ID = Integer.parseInt(part[0]);
                String nama = part[1];
                String spesiality = part[2];
                Doctor dokter = new Doctor(ID, nama, spesiality, part[3], part[4]);
                stDokter.loginDoctor(dokter);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return stDokter;
    }

    PatientList readPatientList(){
        String file = "Data/listPatient.txt";
        String baris;
        PatientList buffer = new PatientList();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while((baris = br.readLine()) != null){
                String[] part = baris.split("/");
                int ID = Integer.parseInt(part[0]);
                String nama = part[1];
                int age = Integer.parseInt(part[2]);
                String alamat = part[3];
                String number = part[4];
                String riwayat = part[5];
                buffer.addPatient(ID, nama, age, alamat, number, riwayat);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return buffer;
    }

    //=========================WRITE FILE=========================

    void writeFileAppointmentReq(Map map) {
        String filename = "Appointment/request.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Map.Entry[] entries = map.entryArray();
            for (int i = 0; i < entries.length; i++) {
                String dokter = entries[i].key;
                AppointmentQueue queue = entries[i].value;
                Appointment current = queue.head;

                if (current == null) continue;

                bw.write(dokter);
                while (current != null) {
                    bw.write(":" + current.pasien.nama + "/" + current.waktu + "/" + current.AppointmentID);
                    current = current.next;
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menulis ke file: " + e.getMessage());
        }
    }


    void writeFileAppointment(Map map) {
        String filename = "Appointment/appointment.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Map.Entry[] entries = map.entryArray();
            for (int i = 0; i < entries.length; i++) {
                String dokter = entries[i].key;
                AppointmentQueue queue = entries[i].value;
                Appointment current = queue.head;

                if (current == null) continue;

                bw.write(dokter);
                while (current != null) {
                    bw.write(":" + current.pasien.nama + "/" + current.waktu + "/" + current.AppointmentID);
                    current = current.next;
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menulis ke file: " + e.getMessage());
        }
    }




    void writeFilePasien(){
        
        String filePasien = "Data/listPatient.txt";

        try {

            FileWriter writer = new FileWriter(filePasien);
            Patient current = patientList.head;

            while(current!=null){

                writer.write(current.ID + "/" + current.nama + "/" + current.age + "/" + 
                current.alamat + "/" + current.number + "/" 
                + current.riwayat + "\n");

                current = current.next;

            }
            writer.close();

        } catch (IOException e) {

        } 

    }

    void writeFileAbsen(){
        String fileAbsen = "Data/absenDokter.txt";
        try {
            FileWriter writer = new FileWriter(fileAbsen);
            Doctor current = absenDokter.head;

            while(current!= null){
                
                writer.write(current.getID() + "/" + current.getName() + "/" + 
                current.getSpeciality() + "/" + current.getLoginTime());
                
                if(!current.getLogOutTime().equals("User belum log out!")){
                    writer.write("/" + current.getLogOutTime());
                }
                writer.write("\n");
                current = current.next;
            }
            writer.close();
            
        } catch (IOException e) {
        
        }
    }

    void clearFileAbsen(){
        String fileAbsen = "Data/absenDokter.txt";
        try {
            FileWriter writter = new FileWriter(fileAbsen);
            writter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void tambahUserDokter(int ID, String nama, String speciality, String pass, String masuk, String keluar){
        String file = "Data/listDokter.txt";
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("\n" + ID + "/" + nama + "/" + speciality + "/" + masuk + "/" + keluar);
            writer.close();
        } catch (IOException e) {
        }
        tambahDataDokter(nama, pass);
    }

    void tambahDataDokter(String nama, String pass) {
        String file = "Data/dataDokter.txt";
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("\n" + nama + ":" + pass);
            writer.close();
        } catch (IOException e) {
        }
    }

    void tambahUserPasien(String a, String b){
        String filePasien = "Data/dataPasien.txt";
        try {
            FileWriter writter = new FileWriter(filePasien, true);
            writter.write("\n" + a + ":" + b);
            writter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void listDokterAbsen(){
        System.out.println("List Dokter yang Tersedia Hari Ini : ");
        absenDokter.listDokter();
    }

    Doctor getUserDokter(String s){
        String file = "Data/listDokter.txt";
        String baris;
        Doctor dokter = null;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while((baris = br.readLine()) != null){
                String[] part = baris.split("/");
                int ID = Integer.parseInt(part[0]);
                String nama = part[1];
                String spesiality = part[2];
                if(nama.equals(s)){
                    dokter = new Doctor(ID, nama, spesiality, part[3], part[4]);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return dokter;
    }

    boolean checkLoginDokter(String name, String password){
        String file = "Data/dataDokter.txt";
        String baris;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while((baris = br.readLine()) != null){
                String[] part = baris.split(":");
                String bufferName = part[0];
                String bufferPw = part[1];
                if(bufferName.equals(name) && bufferPw.equals(password)){
                    return true;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    boolean checkLoginPasien(String name, String password){
        String file = "Data/dataPasien.txt";
        String baris;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            while((baris = br.readLine()) != null){
                String[] part = baris.split(":");
                String bufferName = part[0];
                String bufferPw = part[1];
                if(bufferName.equals(name) && bufferPw.equals(password)){
                    return true;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    void Sleep(int number){
        try {
            Thread.sleep(number);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
        }
    }

    //=========================MAIN PROGRAM=========================

    public void pilihTipeUser(){
        while(true){
            System.out.println("================PROGRAM RUMAH SAKIT DAISUKE================");
            artHospital();
            Sleep(2000);
            clear();
            System.out.println("Silahkan pilih login atau register!\n1. Login\n2. Register");
            System.out.print("Pilihan : ");
            logOrReg = input.nextInt();
            input.nextLine();
            clear();
            if(logOrReg == 1){
                while(true){
                    System.out.println("Login sebagai :\n1. Pasien\n2. Dokter\n3. Admin");
                    System.out.print("Pilihan : ");
                    userType = input.nextInt();
                    input.nextLine();
                    if(userType == 1 || userType == 2 || userType == 3){
                        break;
                    } else {
                        System.out.println("Masukkan Input dengan Benar!");
                    }
                }
                break;
            } else if(logOrReg == 2){
                System.out.println("Register sebagai :\n1. Pasien\n2. Dokter");
                System.out.print("Pilihan : ");
                userType = input.nextInt();
                input.nextLine();
                break;
            } else{
                System.out.println("Masukkan input dengan benar!");
            }
        }   
    }

    public boolean Login(){
        int coba = 0;
        while(coba < 3){
            
            System.out.print("Silahkan Masukkan Username : ");
            String nameLogin = input.nextLine();
            System.out.print("Silahkan Masukkan Password : ");
            String passLogin = input.nextLine();
            
            if(userType == 1){
                if(checkLoginPasien(nameLogin, passLogin)){
                    this.userName = nameLogin;
                    userPatient = patientList.getPatientByName(userName);
                    System.out.println("Berhasil Login!");
                    Sleep(1500);
                    clear();
                    return true;
                } else {
                    System.out.println("Username atau Password salah!");
                }
            } else if (userType == 2){
                if(checkLoginDokter(nameLogin, passLogin)){
                    this.userName = nameLogin;
                    userDokter = getUserDokter(nameLogin);
                    reqAppDokter = mapQueueDokterReq.get(userName);
                    appDokter = mapQueueDokter.get(userName);
                    if (appDokter == null) {
                        appDokter = new AppointmentQueue();
                        mapQueueDokter.put(userName, appDokter);
                    }
                    System.out.println("Berhasil Login!");
                    Sleep(1500);
                    clear();
                    return true;
                } else {
                    System.out.println("Username atau Password salah!");
                }
            } else if (userType == 3){
                if(nameLogin.equals("Admin") && passLogin.equals("123")){
                    this.userName = nameLogin;
                    System.out.println("Berhasil Login!");
                    Sleep(1500);
                    clear();
                    return true;
                } else {
                    System.out.println("Username atau Password Salah!");
                }
            }
            coba++;
        }
        return false;
    }

    public void Register(){
        if(userType == 1){
            int generateID;
            String inpNama;
            int inpUmur;
            String inpAlamat;
            String inpNomor;
            String inpRiwayat;
            String inpPass;

            while (true) { 
                generateID = patientList.getLastPatientID();
                System.out.print("Silahkan Masukkan Nama Anda : ");
                inpNama = input.nextLine();
                if(inpNama.trim().isEmpty() || inpNama.trim().matches("\\d+")){
                    System.out.println("Nama tidak boleh kosong atau hanya diisi angka!");
                } else {
                    clear();
                    break;
                }
            }
            while (true) { 
                System.out.print("Silahkan Masukkan Umur Anda : ");
                inpUmur = input.nextInt();
                input.nextLine();
                if(inpUmur <= 0){
                    System.out.println("Masukkan umur dengan benar!");
                } else {
                    clear();
                    break;
                }  
            }
            while (true) { 
               System.out.print("Silahkan Masukkan Alamat Anda : ");
                inpAlamat = input.nextLine();
                if(inpAlamat.trim().isEmpty() || inpAlamat.trim().matches("\\d+")){
                    System.out.println("Alamat tidak boleh kosong atau hanya diisi angka!");
                } else {
                    clear();
                    break;
                }
            }
            while (true) { 
                System.out.print("Silahkan Masukkan Nomor HP Anda : ");
                inpNomor = input.nextLine();
                if(inpNomor.trim().isEmpty() || inpNomor.trim().matches(".*[a-zA-Z].*")){
                    System.out.println("Input tidak boleh kosong atau diisi huruf!");
                } else {
                    clear();
                    break;
                }
            }
            while(true) {
                System.out.print("Silahkan Masukkan Riwayat Penyakit Anda : ");
                inpRiwayat = input.nextLine();
                if(inpRiwayat.trim().isEmpty()){
                    System.out.println("Riwayat penyakit tidak boleh kosong! Apabila anda tidak memiliki riwayat penyakit maka isi tidak ada");
                } else {
                    clear();
                    break;
                }
            }
            while (true) { 
                System.out.print("Silahkan Masukkan Password Anda : ");
                inpPass = input.nextLine();
                if(inpPass.trim().isEmpty()) {
                    System.out.println("Password tidak boleh kosong!");
                } else {
                    clear();
                    break;
                }
            }
            
            patientList.addPatient(generateID + 1, inpNama, inpUmur, inpAlamat, inpNomor, inpRiwayat);
            userPatient = new Patient(generateID + 1, inpNama, inpUmur, inpAlamat, inpNomor, inpRiwayat);
            writeFilePasien();
            tambahUserPasien(inpNama, inpPass);
            System.out.println("Berhasil Register!");
            Sleep(1500);
            clear();

        } else if(userType == 2){
            int generateID = doctorStack.getLastDoctorID();
            String inpNama;
            String inpSpesialis;
            String inpPass;
            String inpMasuk;
            String inpKeluar;
            
            while(true) {
                System.out.print("Silahkan Masukkan Nama Anda : ");
                inpNama = input.nextLine();
                if(inpNama.trim().isEmpty() || inpNama.trim().matches("\\d+")) {
                    System.out.println("Nama tidak boleh kosong dan tidak boleh terdapat angka!");
                } else {
                    break;
                }
            }
            while (true) { 
                System.out.print("Masukkan Keahlian Anda : ");
                inpSpesialis = input.nextLine();
                if(inpSpesialis.trim().isEmpty()) {
                    System.out.println("Keahlian tidak boleh kosong!");
                } else {
                    break;
                }
            }
            while (true) { 
                System.out.print("Masukkan Password Anda : ");
                inpPass = input.nextLine();  
                if(inpPass.trim().isEmpty()) {
                    System.out.println("Password tidak boleh kosong!");
                } else {
                    break;
                } 
            }
            while (true) { 
                System.out.print("Masukkan Jam Masuk Anda : ");
                inpMasuk = input.nextLine();   
                if(inpMasuk.trim().isEmpty()) {
                    System.out.println("Jam Masuk tidak boleh kosong!");
                } else {
                    break;
                }
            }

            while (true) { 
                System.out.print("Masukkan Jam Keluar Anda : ");
                inpKeluar = input.nextLine();  
                if(inpKeluar.trim().isEmpty()) {
                    System.out.println("Jam Keluar tidak boleh kosong!");
                } else {
                    break;
                }
            } 
            
            userDokter = new Doctor(generateID + 1, inpNama, inpSpesialis, inpMasuk, inpKeluar);
            doctorStack.addDoctor(userDokter);
            tambahUserDokter(generateID + 1, inpNama, inpSpesialis, inpPass, inpMasuk, inpKeluar);
            System.out.println("Berhasil Register!");
            Sleep(1500);
            clear();
        }
    }

    public void lihatMenu(){

        bstPasien.insertPatientList(patientList);
        clear();
        System.err.println("Selamat Datang di Program Rumah Sakit Daisuke!");
        Sleep(2000);
        clear();
        
        if(userType == 1){

            int PilPasien = 0;
            while(PilPasien != 4){
                System.out.println("Silahkan Pilih Apa yang Ingin Anda Lakukan!");
                System.out.println("1. Minta Jadwalkan Appointment\n2. Lihat List Dokter\n3. Lihat Dokter yang Tersedia\n4. Keluar");
                System.out.print("Pilihan : ");
                PilPasien = input.nextInt();
                input.nextLine();
                if(PilPasien != 1 && PilPasien != 2 && PilPasien != 3 && PilPasien != 4 && PilPasien != 5){
                    System.out.println("Masukkan Input dengan Benar!");
                } else {
                    pilPasien(PilPasien);
                }
            }


        } else if(userType == 2){
            int PilDokter = 0;
            
            while(PilDokter != 6){
                System.out.println("Silahkan Pilih Apa yang Ingin Anda Lakukan!");
                System.out.println("1. Absen\n2. List Permintaan Appointment\n3. Lihat Appointment yang Akan Datang\n4. Proses Appointment\n5. Absen Keluar\n6. Keluar");
                System.out.print("Pilihan : ");
                PilDokter = input.nextInt();
                input.nextLine();
                if(PilDokter != 1 && PilDokter != 2 && PilDokter != 3 && PilDokter != 4 && PilDokter != 5 && PilDokter != 6){
                    System.out.println("Masukkan Input dengan Benar!");
                } else {
                    pilDokter(PilDokter);
                }
            }


        } else if(userType == 3){
            int PilAdmin = 0;
            while(PilAdmin != 6){
                System.out.println("Silahkan Pilih Apa yang Ingin Anda Lakukan!");
                System.out.println("1. Lihat Dokter yang Hadir Hari Ini\n2. Lihat Dokter yang Terakhir Absen\n3. Cari Pasien Berdasarkan ID" + 
                "\n4. Lihat List Pasien\n5. Bersihkan Absen Hari Ini\n6. Keluar");
                System.out.print("Pilihan : ");
                PilAdmin = input.nextInt();
                input.nextLine();
                
                if(PilAdmin != 1 && PilAdmin != 2 && PilAdmin != 3 && PilAdmin != 4 && PilAdmin != 5 && PilAdmin != 6){
                    System.out.println("Masukkan Input dengan Benar!");
                } else {
                    pilAdmin(PilAdmin);
                }
            }
            
        }
    }

    void pilPasien(int num){
        switch (num) {
            case 1:
                System.out.print("Silahkan Masukkan Nama Dokter : ");
                String inpNamaDokter = input.nextLine();
                Doctor dokter = doctorStack.getDoctor(inpNamaDokter);
                if(dokter == null) {
                    System.out.println("Nama Dokter tidak ditemukan!");
                } else {
                    AppointmentQueue appQue = mapQueueDokterReq.get(inpNamaDokter);
                    if(appQue == null) {
                        appQue = new AppointmentQueue();
                        mapQueueDokterReq.put(dokter.getName(), appQue);
                    }
                    System.out.print("Silahkan Masukkan Jam Appointment : ");
                    String jamApp = input.nextLine();
                    if(jamApp.compareTo(dokter.getJamMasuk()) < 0 || jamApp.compareTo(dokter.getJamSelesai()) > 0) {
                        System.out.println("Dilarang melakukan appointment diluar jam kerja dokter!");
                    } else {
                        Appointment app = new Appointment();
                        int ID = appQue.getLastAppID(); 
                        app.setAll(userPatient, dokter, jamApp, ID + 1);
                        appQue.enqueue(app);
                        writeFileAppointmentReq(mapQueueDokterReq);
                    }
                    
                }
                break;

            case 2:
                doctorStack.listDokter();
                break;
            case 3:
                listDokterAbsen();
                break;
        }
    }

    void pilDokter(int num){
        switch (num) {
            case 1:
                if(userDokter.absen) {
                    System.out.println("Anda Sudah Absen!");
                } else {
                    userDokter.addTime();
                    absenDokter.loginDoctor(userDokter);
                    writeFileAbsen();
                    System.out.println("Berhasil absen!");
                    userDokter.doneAbsen();
                }
                break;
            case 2:
                
                if(reqAppDokter == null){
                    System.out.println("Tidak ada permintaan Appointment!");
                } else {
                    reqAppDokter.listAntrian();
                    Sleep(1500);
                    System.out.println("Apakah anda ingin menerima permintaan antrian teratas?\n1. Ya\n2.Tidak");
                    System.out.print("Pilihan : ");
                    int pilAntri = input.nextInt();
                    input.nextLine();
                    if(pilAntri == 1) {
                        Appointment reqTop = reqAppDokter.getFrontQueue();
                        if (reqTop != null) {
                            Appointment copy = new Appointment();
                            int ID = appDokter.getLastAppID() + 1;
                            copy.setAll(reqTop.pasien, reqTop.dokter, reqTop.waktu, ID);
                            appDokter.enqueue(copy);
                            reqAppDokter.dequeue();
                            writeFileAppointmentReq(mapQueueDokterReq);
                            writeFileAppointment(mapQueueDokter);
                        }
                    }
                }
                break;
            case 3:
                
                if(appDokter == null) {
                    System.out.println("Tidak ada appointment yang akan datang!");
                } else {
                    appDokter.listAntrian();
                    Sleep(1500);
                }
                break;
            case 4 :
            if(appDokter == null) {
                System.out.println("Appointment masih kosong!");
            } else {
                appDokter.dequeue();
                writeFileAppointment(mapQueueDokter);
            }
            break;
            case 5 : 
            absenDokter.setLogOut(userName);
            writeFileAbsen();
        }
    }

    void pilAdmin(int num){
        switch (num) {
            case 1:
                absenDokter.getAllLoggedInDoctors();
                break;
            case 2: 
                absenDokter.viewLastLoggedDoctor();
                break;
            case 3:
                System.out.print("Silahkan Masukkan ID Pasien : ");
                int inpID = input.nextInt();
                bstPasien.searchPatient(inpID);
                break;
            case 4:
                patientList.displayPatient();
                break;
                
            case 5: 
                clearFileAbsen();
                absenDokter = bacaFileAbsen();
                System.out.println("List Absen Hari Ini Berhasil di Kosongkan!");
                break;
        }
    }

    void artHospital(){
        System.out.println("             .----. ");
        System.out.println("            ===(_)===");
        System.out.println("           // 6  6 \\\\  /");
        System.out.println("           (    7   )");
        System.out.println("            \\ '--' /");     
        System.out.println("             \\_ ._/");
        System.out.println("            __)  (__");
        System.out.println("         /\"`/`\\`V/`\\`\\");
        System.out.println("        /   \\  `Y _/_ \\");
        System.out.println("       / [DR]\\_ |/ / /\\");
        System.out.println("       |     ( \\/ / / /");
        System.out.println("        \\  \\  \\      /");
        System.out.println("         \\  `-/`  _.`");
        System.out.println("          `=._`=./");
    }
}