package controller;

/**
 * @author Sanyam Goel created on 5/2/19
 */

/**
 * Output class to display output -> new Expiry Date and total Cost of Subscription
 */
public class Input {

    private String expiry_date;
    private int months_to_buy;
    private double monthly_cost;
    private double annual_cost;

    public Input(String expiry_date, int months_to_buy, double monthly_cost, double annual_cost) {
        this.expiry_date = expiry_date;
        this.months_to_buy = months_to_buy;
        this.monthly_cost = monthly_cost;
        this.annual_cost = annual_cost;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getMonths_to_buy() {
        return months_to_buy;
    }

    public void setMonths_to_buy(int months_to_buy) {
        this.months_to_buy = months_to_buy;
    }

    public double getMonthly_cost() {
        return monthly_cost;
    }

    public void setMonthly_cost(double monthly_cost) {
        this.monthly_cost = monthly_cost;
    }

    public double getAnnual_cost() {
        return annual_cost;
    }

    public void setAnnual_cost(double annual_cost) {
        this.annual_cost = annual_cost;
    }
}
