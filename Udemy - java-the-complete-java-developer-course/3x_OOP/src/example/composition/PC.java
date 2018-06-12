package example.composition;

public class PC {
    private Case theCase;
    private Monitor monitor;
    private MotherBoard motherBoard;

    public PC(Case theCase, Monitor monitor, MotherBoard motherBoard) {
        this.theCase = theCase;
        this.monitor = monitor;
        this.motherBoard = motherBoard;
    }

    public void powerUp(){
        theCase.pressPowerButton();
        drawLogo();
        motherBoard.loadProgram("Composistion loading");
    }

    private void drawLogo(){
        //Fancy graphics
        monitor.drawPixelAt(1200,50,"yellow");
    }

//    private Case getTheCase() {
//        return theCase;
//    }
//
//    public void setTheCase(Case theCase) {
//        this.theCase = theCase;
//    }
//
//    private Monitor getMonitor() {
//        return monitor;
//    }
//
//    public void setMonitor(Monitor monitor) {
//        this.monitor = monitor;
//    }
//
//    private MotherBoard getMotherBoard() {
//        return motherBoard;
//    }
//
//    public void setMotherBoard(MotherBoard motherBoard) {
//        this.motherBoard = motherBoard;
//    }
}
