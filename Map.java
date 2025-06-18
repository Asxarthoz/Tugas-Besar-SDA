class Map {
    class Entry {
        String key;
        AppointmentQueue value;
        Entry next; 

        Entry(String key, AppointmentQueue value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    final int SIZE = 100;
    Entry[] table = new Entry[SIZE];

    public Entry[] entryArray() {

        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            Entry current = table[i];
            while (current != null) {
                count++;
                current = current.next;
            }
        }

        Entry[] entries = new Entry[count];
        int index = 0;
        for (int i = 0; i < SIZE; i++) {
            Entry current = table[i];
            while (current != null) {
                entries[index++] = current;
                current = current.next;
            }
        }

        return entries;
    }


    int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31 + key.charAt(i)) % SIZE;
        }
        return hash;
    }

    public void put(String key, AppointmentQueue value) {
        int idx = hash(key);
        Entry head = table[idx];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value; 
                return;
            }
            head = head.next;
        }

        Entry newEntry = new Entry(key, value);
        newEntry.next = table[idx];
        table[idx] = newEntry;
    }

    public AppointmentQueue get(String key) {
        int idx = hash(key);
        Entry head = table[idx];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public Entry[] getAllEntries() {
        return table;
    }

    public void printAll() {
        for (int i = 0; i < SIZE; i++) {
            Entry head = table[i];
            while (head != null) {
                System.out.println("Dokter: " + head.key);
                head.value.listAntrian();
                head = head.next;
            }
        }
    }

    public void printAllAppointments() {
        for (int i = 0; i < SIZE; i++) {
            Entry current = table[i];
            while (current != null) {
                System.out.println("Dokter : " + current.key);
                current.value.listAntrian();
                System.out.println();
                current = current.next;
            }
        }
    }
}
