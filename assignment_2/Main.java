package assignment_2;

import java.util.ArrayList;

class Main {

    public static void main(String[] args) {
        SimpleFileWriter w = new SimpleFileWriter("5k_run_1", false);
        ArrayList<Integer> months = new ArrayList<>();
        double ci = 0;
        while (ci >= 1 || ci == 0) {
            Account acc = new Account(10000);
            int month = runMonths(acc);
            months.add(month);
            ci = confidenceInterval(months);
            String s = Integer.toString(month);
            w.println(s);
            System.out.println(ci);
        }
        w.close();
    }

    private static int runMonths(Account acc) {
        int month = 0;
        while (acc.getAmount() < 2000000) {
            acc.doMonth();
            month = acc.getMonth();
        }
        return month;
    }

    private static double mean(ArrayList<Integer> l) {
        double sum = 0;
        for(int i : l)
            sum += i;
        return sum/l.size();
    }

    private static double confidenceInterval(ArrayList<Integer> l) {
        if(l.size() < 100)
            return 0;
        return 1.96 * std(l) / Math.sqrt(l.size());
    }

    private static double std(ArrayList<Integer> l){
        double mean = mean(l);
        double sse = 0;
        for(int i : l)
            sse += Math.pow(i - mean,2);
        return Math.sqrt(sse/(l.size()-1));
    }
}
