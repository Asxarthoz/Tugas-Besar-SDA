class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.clear();
        menu.pilihTipeUser();

        boolean berhasilLogin = false;

        if(menu.logOrReg == 1){
            berhasilLogin = menu.Login();
        } else {
            menu.Register();
            berhasilLogin = true;
        }

        if(berhasilLogin){
            menu.lihatMenu();
        } else {
            System.out.println("Anda gagal login sebanyak 3X!\nProgram otomatis keluar");
            menu.Sleep(1500);
        }
        
    }
}