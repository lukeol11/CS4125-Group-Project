package com.cs4125.shop.model;

<<<<<<< HEAD
import java.util.List;

public class GraphicsCard extends Component {
=======
import com.cs4125.shop.model.factory.GraphicsCardFactory;

public class GraphicsCard extends Component implements GraphicsCardFactory {
>>>>>>> master
    private int memory;
    private int baseClock;
    private int GPULength;

    public GraphicsCard(String name, double price, Integer wattage, int memory, int baseClock, int GPULength) {
        super(name, price, wattage);
        this.memory = memory;
        this.baseClock = baseClock;
        this.GPULength = GPULength;
    }

    public int getMemory() {
        return memory;
    }

    public int getBaseClock() {
        return baseClock;
    }

    public int getGPULength() {
        return GPULength;
    }

    //Check if the graphics card can fit in the case
    public boolean isCompatibleWith(List<Component> dimension) {
        if (dimension instanceof Case) {
            int dime = ((Case) dimension).getDime();
            return this.GPULength <= dime;
        }
        System.out.println("Run GPU");
        return false;
    }
}
