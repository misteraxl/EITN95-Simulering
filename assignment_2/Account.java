package assignment_2;

import java.util.Random;

public class Account {

    private double saveAmount;
    private double amount = 0;
    private final double normalInterestRate = Math.pow(1.3, 1 / 12);
    private int month;

    public Account(double saveAmount) {
        this.saveAmount = saveAmount;
        this.month = 0;
    }

    public double getAmount() {
        return amount;
    }

    public double doMonth() {
        if(month % 12 == 0) {
            amount = amount * getInterestRate();
        } else {
            amount = amount * normalInterestRate;
        }
        month++;
        amount += saveAmount;
        return amount;
    }

    private double getInterestRate() {
        // 25% risk of disturbance
        Random rand = new Random();
        if (rand.nextDouble() < 0.25) {
            // Market disturbance
            double distType = rand.nextDouble();
            if (distType < 0.1) {
                return 0.75;
            } else if (distType < 0.35) {
                return 0.5;
            } else if (distType < 0.5) {
                return 0.4;
            } else {
                return 0.9;
            }
        } else {
            // no market disturbance
            return normalInterestRate;
        }
    }

    public int getMonth() {
        return month;
    }
}
