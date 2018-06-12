package example.inheritance.challenge;

public class Outlander extends Car {

    private int roadServiceMonths;

    public Outlander(int roadServiceMonths) {
        super("Outlander", "4WD", 5, 5, 6, false);
        this.roadServiceMonths = roadServiceMonths;
    }

    public void accelerate(int rate){
        int newVelocity = getCurrentVelocity() + rate;
        if(newVelocity == 0){
            stop();
            setGears(1);
        }else if(newVelocity > 0 && newVelocity <=10){
            setGears(1);
        }else if(newVelocity > 10 && newVelocity <=20){
            setGears(2);
        }

        if(newVelocity > 0){
            changeVelocity(newVelocity,getCurrentDirection());
        }
    }
}
