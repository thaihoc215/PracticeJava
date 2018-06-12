package example.composition;

public class Monitor {
    private String model;
    private String manufacturer;
    private int size;
    private Resolution nativeRevolution;

    public Monitor(String model, String manufacturer, int size, Resolution nativeRevolution) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.size = size;
        this.nativeRevolution = nativeRevolution;
    }

    public void drawPixelAt(int x, int y, String color) {
        System.out.println("Drawing pixel at " + x + "," + y + " in color " + color);
    }

//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public Resolution getNativeRevolution() {
//        return nativeRevolution;
//    }
//
//    public void setNativeRevolution(Resolution nativeRevolution) {
//        this.nativeRevolution = nativeRevolution;
//    }
}
