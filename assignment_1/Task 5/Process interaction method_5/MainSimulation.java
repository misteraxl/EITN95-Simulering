
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation
public class MainSimulation extends Global {

    public static void main(String[] args) throws IOException {

        //Signallistan startas och actSignal deklareras. actSignal �r den senast utplockade signalen i huvudloopen nedan.
        // The signal list is started and actSignal is declaree. actSignal is the latest signal that has been fetched from the 
        // signal list in the main loop below.
        Signal actSignal;
        new SignalList();

        //H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges v�rden.
        // Here process instances are created (two queues and one generator) and their parameters are given values. 
		

        Gen Generator = new Gen();
		Generator.queues[0] = new QS();
		Generator.queues[1] = new QS();
		Generator.queues[2] = new QS();
		Generator.queues[3] = new QS();
		Generator.queues[4] = new QS();
        // Configure generator: send all arrivals to queue 0 and set arrival rate
		//De genererade kunderna ska skickas till kösystemet QS  // The generated customers shall be sent to Q1
        SignalList.SendSignal(READY, Generator, time);
		SignalList.SendSignal(MEASURE, Generator.queues[0], time);
		SignalList.SendSignal(MEASURE, Generator.queues[1], time);
		SignalList.SendSignal(MEASURE, Generator.queues[2], time);
		SignalList.SendSignal(MEASURE, Generator.queues[3], time);
		SignalList.SendSignal(MEASURE, Generator.queues[4], time);
        //Här nedan skickas de första signalerna för att simuleringen ska komma igång.
        //To start the simulation the first signals are put in the signal list

        // Detta är simuleringsloopen:
        // This is the main loop
        while (time < 100000) {
            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
        }

        //Slutligen skrivs resultatet av simuleringen ut nedan:
        //Finally the result of the simulation is printed below:
        double acc = 0;
        System.out.println("mean Q1 = " + 1.0 * Generator.queues[0].accumulated / Generator.queues[0].noMeasurements);
        for (int i = 0; i < 5; i++) {
            acc += (double) Generator.queues[i].accumulated / (double) Generator.queues[i].noMeasurements;
        }
        acc = acc / 5;
        System.out.println("Mean number of customers in queuing system: " + 1.0 * acc);

    }
}
