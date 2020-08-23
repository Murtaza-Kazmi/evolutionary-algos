import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalDouble;

import static java.lang.System.*;

public class main {
    public static void main(String[] args) {
        function a = new function();
        //Population initialized
        double prev_best = 0;
        double new_best;
        double[] latest = new double[5];
        double[] pre = new double[5];
        ArrayList<Double> best_vals = new ArrayList<Double>();
        for (int i = 1; i <= 50; i++) {
            if (i > 1) {
                a.get_parents();
            }
            out.println("\nFitness vector: " + Arrays.toString(a.compute_fitness()));
            double[][] x = a.select_parents_for_offspring();
            out.println("\nSelected parent no. 1: " + Arrays.toString(x[0]));
            out.println("Selected parent no. 2: " + Arrays.toString(x[1]));
            x = a.two_select_parents_for_offspring();
            out.println("Selected parent no. 3: " + Arrays.toString(x[0]));
            out.println("Selected parent no. 4: " + Arrays.toString(x[1]));
            a.create_offspring();
            x = a.mutate();
            out.println("Child 1: " + Arrays.toString(x[0]));
            out.println("Child 2: " + Arrays.toString(x[1]));
            out.println("Child 3: " + Arrays.toString(x[2]));
            out.println("Child 4: " + Arrays.toString(x[3]));
            a.compute_fitness_of_offspring_and_choose_next_5();
            new_best = a.best_value();
            best_vals.add(new_best);
//            latest = a.getFinal_fitness();
            a.reinitialize_for_next_iter();
            out.println("\nEnd of iteration no. " + i + "\n\n");
            if (i != 1 && new_best-prev_best < 0.00000001) {
                out.println("End of Program.");
                break;
            }
            prev_best = new_best;
        }


        for (int i = 0; i < best_vals.size(); i++) {
            out.println(i + 1 + " " + best_vals.get(i));
        }
    }
}
