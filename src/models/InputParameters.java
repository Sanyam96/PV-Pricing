package models;

/**
 * @author Sanyam Goel created on 5/2/19
 */
public class InputParameters {

    String expiry_date;
    int months_to_buy;
    double monthly_cost;
    double annual_cost;

    public InputParameters(String expiry_date, int months_to_buy, double monthly_cost, double annual_cost) {
        this.expiry_date = expiry_date;
        this.months_to_buy = months_to_buy;
        this.monthly_cost = monthly_cost;
        this.annual_cost = annual_cost;
    }
}
