import java.util.Random;

public class Component {
    private double liveLength;
    private boolean isAlive = true;
    private int index;

    public Component(int index) {
        Random rand = new Random();
        this.liveLength = rand.nextDouble(4)+1;
        this.index = index;
    }

    public double getLiveLength() {
        return liveLength;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setDead() {
        this.isAlive = false;
    }

    public int getIndex() {
        return index;
    }
}
