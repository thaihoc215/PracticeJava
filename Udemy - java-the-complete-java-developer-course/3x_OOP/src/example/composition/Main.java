package example.composition;

public class Main {
    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(200, 20, 5);
        Case aCase = new Case("220B", "Dell", "240", dimensions);

        Monitor monitor = new Monitor("27 inch BA", "ACER", 27, new Resolution(1920, 1080));

        MotherBoard motherBoard = new MotherBoard("BJ-200", "ASUS", 4, 6, "v2.44");

        PC pc = new PC(aCase, monitor, motherBoard);
//        pc.getMonitor().drawPixelAt(1500,1200,"red");
//        pc.getMotherBoard().loadProgram("Thai hoc windows");
//        pc.getTheCase().pressPowerButton();
        pc.powerUp();
    }
}
