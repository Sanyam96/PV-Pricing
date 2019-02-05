import controller.Input;
import service.CalculateSubscriptionService;

public class Main {

    /**
     * Main function of the Program
     * Code starts from the main function in Java
     */
    public static void main(String[] args) {

        CalculateSubscriptionService calculateSubscription = new CalculateSubscriptionService();

        Input inputParameters = new Input("19/06/2018", 12, 400, 3650);
        //        User Test Cases
        //        ("19/06/2018", 3, 1000, 8000) -> ("15/09/2018", 2866.58)
        //        ("19/06/2018", 12, 400, 3650) -> ("01/07/2019", 3770)

        /**
         * Calling calculate_subscription method by passing all the parameters as an object
         */
        calculateSubscription.calculate_subscription(inputParameters);
    }
}
