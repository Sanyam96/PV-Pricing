package controller;

/**
 * @author Sanyam Goel created on 5/2/19
 */
public class Output {

    public void showOutput(String [] args) {
        // Loop to print output received from the function
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
    }

}
