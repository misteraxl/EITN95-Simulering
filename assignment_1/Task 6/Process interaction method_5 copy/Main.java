
public class Main {
    public static void main(String[] args) {
        int sims = 100000;
        double t[] = new double[sims];
        double total = 0;
        for(int i = 0; i < sims; i++) {
            t[i] = run();
            total += t[i];
        }
        System.out.println("All times: ");
        for(int i = 0; i < sims; i++) {
            System.out.println(t[i]);
        }
        
        System.out.println("Average time = " + (total / sims));
    }

    private static double run() {
        Component components[] = new Component[5];
        for (int i = 0; i < 5; i++) {
            components[i] = new Component(i);
            System.out.println("new component " + (i) + " with life length: " + components[i].getLiveLength());
        }
        double t = 0;
        while(!checkIfAllDead(components)) {
            int lowest_index = getMinLiveLength(components);
            t = components[lowest_index].getLiveLength();
            if(lowest_index == 0 || lowest_index == 1 || lowest_index == 4) {
                components[0].setDead();
                components[1].setDead();
                components[4].setDead();
            } else {
                components[2].setDead();
                components[3].setDead();

            }
        }
        return t;
    }

    private static int getMinLiveLength(Component[] components) {
        double min = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < components.length; i++) {
            if (components[i].getLiveLength() < min && components[i].isAlive()) {
                min = components[i].getLiveLength();
                index = i;
            }
        }
        return index;
    }

    private static boolean checkIfAllDead(Component[] components) {
        for (Component component : components) {
            if (component.isAlive()) {
                return false;
            }
        }
        return true;
    }
}
