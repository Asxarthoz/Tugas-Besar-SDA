class Node {
    Patient data;
    Node left, right;

    public Node(Patient P) {
        data = P;
        left = right = null;
    }
}

public class BST {
    Node root;

    public BST() {
        root = null;
    }

    private Node insert(Node root, Patient data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (root.left == null) {
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }

        return root;
    }

    public void insertPatient(Patient p) {
        root = insert(root, p);
    }

    public void insertPatientList(PatientList list){
        Patient current = list.head;
        while(current != null){
            insertPatient(current);
            current = current.next;
        }
    }

    private boolean search(Node root, int id) {

        if (root == null) {
          return false;  
        }
        
        if (root.data.ID == id) {
            System.out.println("Data Pasien dengan ID " + id + " ditemukan: ");            
            System.out.println("ID: " + root.data.ID + "\nNama: " + root.data.nama + "\nUmur: " + root.data.age + "\nAlamat: " + root.data.alamat + "\nNomor Telepon: " + root.data.number + "\nRiwayat Penyakit: " + root.data.riwayat + "\n");
            return true;
        }

        return search(root.left, id) || search(root.right, id);
    }

    public void searchPatient(int id) {
        boolean found = search(root, id);

        if(!found) {
            System.out.println("Pasien dengan ID " + id + " tidak ditemukan.\n");
        }
    }

    private void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println("ID: " + root.data.ID + "\nNama: " + root.data.nama + "\nUmur: " + root.data.age + "\nAlamat: " + root.data.alamat + "\nNomor Telepon: " + root.data.number + "\n");
            inOrder(root.right);
        }
    }

    public void inOrderDisplay() {
        if (root == null) {
            System.out.println("Tidak ada data pasien!");
        } else {
            System.out.println("Data Pasien: ");
            inOrder(root);
        }
    }
}
