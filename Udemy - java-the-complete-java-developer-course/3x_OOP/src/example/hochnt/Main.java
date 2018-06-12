package example.hochnt;

public class Main {
    public static void main(String[] args){
//        Car porche = new Car();
//        Car holden = new Car();
//
//        porche.setModel("Carrera");
//        System.out.println("Model is: " + porche.getModel());

        Account bobsAccount = new Account();
        bobsAccount.withdrawal(100.0);

        bobsAccount.deposit(50.0);
        bobsAccount.withdrawal(100.0);
    }
}
