package com.cs4125.shop.model;

public class Motherboard extends Component {
    private String chipset;
    private String formFactor;
    private String socket;
    private String storageType;


    public Motherboard(String name, double price, Integer wattage, String chipset, String formFactor, String socket, String storageType) {
        super(name, price, wattage);
        this.chipset = chipset;
        this.formFactor = formFactor;
        this.socket = socket;
        this.storageType = storageType;
    }

    public String getChipset() {
        return chipset;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public String getSocket() {
        return socket;
    }

    public String getStorageType() {
        return storageType;
    }

    public boolean isCompatibleWith(Component component) {
        if(component instanceof CPU) {
            //Compatibility based on Socket Type
            String socketCPU = ((CPU) component).getSocketCPU();
            return this.socket.equals(socketCPU);
        } else if (component instanceof Storage) {
            //Compatibility based on Storage Types
            String type = ((Storage) component).getType();
            return this.storageType.equals(type);
        }
        //For all other components return true
        System.out.println("Run Mother");
        return true;
    }
}
