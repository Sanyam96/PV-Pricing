package service;

import controller.Input;
import controller.Output;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Sanyam Goel created on 5/2/19
 */
public class CalculateSubscriptionService {

    Output output = new Output();

    /**
     * This function is use to calculate the new expiry date and the total subscription cost
     * Main business logic implementation
     * @param inputParameters i.e., expiry_date, months_to_buy, monthly_cost, annual_cost
     */
    public void calculate_subscription(Input inputParameters) {
        String expiry_date = inputParameters.getExpiry_date();
        int months_to_buy = inputParameters.getMonths_to_buy();
        double monthly_cost = inputParameters.getMonthly_cost();
        double annual_cost = inputParameters.getAnnual_cost();

        String arg0;
        String arg1;
        String arr[] = new String[2];   // to return NewExpiryDate and Amount   // also do it by passing class object

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date expiryDate = new Date();

//        Exception handling while converting expiry date(String) to the dateFormat in java
        try {
            expiryDate = dateFormat.parse(expiry_date);
        } catch (ParseException e) {
            System.out.println("Exception occured in parsing the date: " + e.toString());
        }

        Calendar newExpiryDate = Calendar.getInstance();
        newExpiryDate.setTime(expiryDate);

        boolean annualOrMonthlySubscriptionFlag = false;    // Boolean flag -> true for annual subscription | false for monthly subscription
        int tempDate = newExpiryDate.get(Calendar.DATE);    // current date of subscription | expiry date entered by user
        int extraDaysSubscription = 0;
        double totalCost = 0;

        // if month = 24 or 36 or any other multiple of 12 but in mentioned case months value can be in between [1, 12]
        if( months_to_buy == 12 || (months_to_buy%12) == 0) {
            annualOrMonthlySubscriptionFlag = true;
        }

        if (annualOrMonthlySubscriptionFlag) {  // Annual Subscription Logic
            int number = months_to_buy/12;  // useful when months value is not restricted to [1, 12]

            newExpiryDate.add(Calendar.YEAR, number);

            if (tempDate > 15) {
                newExpiryDate.set(Calendar.DATE, 1);
                newExpiryDate.set(Calendar.MONTH, (newExpiryDate.get(Calendar.MONTH) + 1));
                newExpiryDate.add(Calendar.DATE, -1);
                extraDaysSubscription = newExpiryDate.get(Calendar.DAY_OF_MONTH) - tempDate + 1;
                newExpiryDate.add(Calendar.DATE, 1);
            } else if (tempDate < 15 ){
                newExpiryDate.set(Calendar.DATE, 15);
                extraDaysSubscription = 15 - tempDate;
            } else {
                extraDaysSubscription = 0;
            }
            totalCost = ((annual_cost*number)+ (extraDaysSubscription*(annual_cost/365)));
            arg0 = dateFormat.format(newExpiryDate.getTime());
            arg1 = "" + totalCost;

        } else {    // monthly Subscription Logic

            Calendar toCalculateNumberOfSubscriptionDays = Calendar.getInstance();
            toCalculateNumberOfSubscriptionDays.setTime(expiryDate);

            newExpiryDate.add(Calendar.MONTH, 1);

            if (tempDate > 15) {
                newExpiryDate.set(Calendar.DATE, 15);
                long end = newExpiryDate.getTimeInMillis();
                long start = toCalculateNumberOfSubscriptionDays.getTimeInMillis();
                extraDaysSubscription = (int) TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
                newExpiryDate.add(Calendar.MONTH, (months_to_buy-1));
                totalCost = (extraDaysSubscription*(monthly_cost/30)) + (monthly_cost*(months_to_buy-1));
            } else if( tempDate < 15) {
                newExpiryDate.set(Calendar.DATE, 1);
                long end = newExpiryDate.getTimeInMillis();
                long start = toCalculateNumberOfSubscriptionDays.getTimeInMillis();
                extraDaysSubscription = (int) TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
                newExpiryDate.add(Calendar.MONTH, (months_to_buy-1));
                totalCost = (extraDaysSubscription*(monthly_cost/30)) + (monthly_cost*(months_to_buy-1));
            } else {
                newExpiryDate.add(Calendar.MONTH, (months_to_buy-1));
                totalCost = monthly_cost*(months_to_buy);
            }
            arg0 = dateFormat.format(newExpiryDate.getTime());
            arg1 = "" + totalCost;
        }

        arr[0] = arg0;
        arr[1] = arg1;
        output.showOutput(arr); // passing the output as a String array(new_expiry_date, total_cost_subscription) to the Output class
    }

}
