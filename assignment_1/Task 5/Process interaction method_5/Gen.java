
import java.util.*;

//Denna klass ärver Proc, det går att man kan använda time och signalnamn utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation 
class Gen extends Proc {

    //Slumptalsgeneratorn startas:
    //The random number generator is started:
    Random slump = new Random();

    //Generatorn har två parametrar:
    //There are two parameters:
    public Proc sendTo;    //Anger till vilken process de genererade kunderna ska skickas //Where to send customers
    public double lambda;  //Hur många per sekund som ska generas //How many to generate per second
    public QS queues[] = new QS[5];
    private int counter = 0;
    private Dispatcher strategy = Dispatcher.SMALLEST;

    public enum Dispatcher {
        RANDOM, ROUND_ROBIN, SMALLEST;
    }
    //Här nedan anger man vad som ska göras när en signal kommer //What to do when a signal arrives

    public void TreatSignal(Signal x) {
        switch (x.signalType) {
            case READY: {
                QS chosen = null;
                switch (strategy) {
                    case RANDOM:
                        chosen = queues[slump.nextInt(5)];
                        break;
                    case ROUND_ROBIN:
                        chosen = queues[counter];
                        counter = (counter + 1) % 5;
                        break;
                    case SMALLEST:
                        int compare = Integer.MAX_VALUE;
                        QS currentQueue = null;
                        for (int i = 0; i < 5; i++) {
                            if (queues[i].numberInQueue < compare) {
                                compare = queues[i].numberInQueue;
                                currentQueue = queues[i];
                            }
                        }
                        chosen = currentQueue;
                        break;
                }
                SignalList.SendSignal(ARRIVAL, chosen, time);
                SignalList.SendSignal(READY, this, time + 2);
            }
            break;
        }

    }
}
