
public class AppointmentQueue {
    Appointment head;


    String peek(){
        return head.pasien.nama;
    }

    public void enqueue(Appointment a){
        if(head == null){
            head = a;
        } else {
            Appointment current = head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = a;
        }
        
    }

    public void dequeue(){
        if(head == null) {
            System.out.println("Appointment masih kosong!");
        } else {
            head = head.next;
        }
    }

    public void listAntrian(){
        if(head == null){
            System.out.println("Appointment masih kosong!");
        } else {
            Appointment current = head;
            while(current != null){
                System.out.println("ID Appointment : " + current.AppointmentID + "\nNama Pasien : " + current.pasien.nama + "\nNama Dokter : " + current.dokter.name + "\nJam : " + current.waktu + "\n");
                current = current.next;
            }
        }
    }

    public Appointment getFrontQueue(){
        return head;
    }

    public int getLastAppID(){
        if(head == null) return 999;
        Appointment current = head;
        while(current.next!=null) {
            current = current.next;
        }
        return current.AppointmentID;
    }
}
