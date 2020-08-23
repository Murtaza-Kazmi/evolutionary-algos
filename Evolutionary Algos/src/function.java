import java.util.*;

public class function {
    private double[][] array = new double[5][2];
    public function(){
        for(int i= 0; i < array.length; i++){
            do {
                array[i][0] = -5 + Math.random() *10;
                array[i][1] = -5 + Math.random()*10;
                System.out.println("Parent " + (i+1) + ": (" + array[i][0] + ", " + array[i][1] + ")");
            } while(not_in_it(array, array[i][0]) && not_in_it(array, array[i][1]));
        }
    }
    public void get_parents(){
        for(int i= 0; i < array.length; i++)
        System.out.println("Parent " + (i+1) + ": (" + array[i][0] + ", " + array[i][1] + ")");
    }

    private boolean not_in_it(double[][] array, double v) {
        for(int i= 0; i < array.length; i++){
            if(array[i][0] == v || array[i][1] == v){
                return false;
            }
        }
        return true;
    }

    private double[] fitness_vector = new double[array.length];
    public double[] compute_fitness() {
        for(int i = 0; i< array.length; i++){
            fitness_vector[i] = func(array[i][0], array[i][1]);
        }
        return fitness_vector;
    }

    private double func(double v, double v1) {
        return v*v + v1*v1;
    }
    private double[] temp= new double[fitness_vector.length];
    private double[][] selected_parents = new double[2][2];
    public double[][] select_parents_for_offspring() {
        for(int i =0; i < fitness_vector.length; i++){
            temp[i] = fitness_vector[i];
        }
        Arrays.sort(fitness_vector);
        double max = fitness_vector[fitness_vector.length-1];
        double pen_max = fitness_vector[fitness_vector.length-2];
        int index_max = 0;
        int index_pen = 0;
        for(int i =0; i < temp.length; i++){
            if(temp[i] == max) index_max = i;
            if(temp[i] == pen_max) index_pen = i;
        }
        selected_parents[0][0] = array[index_max][0];
        selected_parents[0][1] = array[index_max][1];
        selected_parents[1][0] = array[index_pen][0];
        selected_parents[1][1] = array[index_pen][1];
        return selected_parents;

    }
    private double[][] selected_parents2 = new double[2][2];
    public double[][] two_select_parents_for_offspring() {
        double max = fitness_vector[fitness_vector.length-3];
        double pen_max = fitness_vector[fitness_vector.length-4];
        int index_max = 0;
        int index_pen = 0;
        for(int i =0; i < temp.length; i++){
            if(temp[i] == max) index_max = i;
            if(temp[i] == pen_max) index_pen = i;
        }
        selected_parents2[0][0] = array[index_max][0];
        selected_parents2[0][1] = array[index_max][1];
        selected_parents2[1][0] = array[index_pen][0];
        selected_parents2[1][1] = array[index_pen][1];
        return selected_parents2;

    }
    private double[] off_spring1 = new double[2];
    private double[] off_spring2 = new double[2];
    private double[] off_spring3 = new double[2];
    private double[] off_spring4 = new double[2];
    private double[][] off_springs = new double[4][2];
    public void create_offspring() {
        off_spring1[0] = selected_parents[0][0];
        off_spring1[1] = selected_parents[1][1];
        off_spring2[0] = selected_parents[1][0];
        off_spring2[1] = selected_parents[0][1];
        off_spring3[0] = selected_parents2[0][0];
        off_spring3[1] = selected_parents2[1][1];
        off_spring4[0] = selected_parents2[1][0];
        off_spring4[1] = selected_parents2[0][1];
        off_springs[0] = off_spring1;
        off_springs[1] = off_spring2;
        off_springs[2] = off_spring3;
        off_springs[3] = off_spring4;
    }
    public double[][] mutate() {
        double temp_var;
        for(int i= 0 ; i < off_springs.length; i++){
            if(Math.random() > 0.5){
                temp_var = off_springs[i][0];
                off_springs[i][0] = off_springs[i][1];
                off_springs[i][1] = temp_var;
            }
        }
        return off_springs;
    }
    private double[][] final_array = new double[9][2];
    private double[] final_fitness = new double[9];
    private double[][] finalized_array = new double[5][2];
    public void compute_fitness_of_offspring_and_choose_next_5() {
        for(int i =0; i < array.length; i++){
            final_array[i] = array[i];
            final_fitness[i] = fitness_vector[i];
        }
        for(int i = 0; i < off_springs.length;i++){
            final_array[i+5] = off_springs[i];
            final_fitness[i+5] = func(off_springs[i][0], off_springs[i][1]);
        }
        Arrays.sort(final_fitness);
        function.reverse(final_fitness);

        for(int i =  0; i <= 4; i++){
            for(int j = 0; j < final_array.length; j++){
                if(func(final_array[j][0], final_array[j][1]) == final_fitness[i]){
                    finalized_array[i] = final_array[j];
                    break;
                }
            }
        }

        array = finalized_array.clone();
    }

    public double best_value() {
        double max = 0;
        for(int i =0; i < final_fitness.length; i++){
            max = Math.max(final_fitness[i], max);
        }
        return max;
    }
    public double[] getFinal_fitness(){
        return final_fitness;
    }



    public static void reverse(double[] input) { int last = input.length - 1; int middle = input.length / 2; for (int i = 0; i <= middle; i++) { double temp = input[i]; input[i] = input[last - i]; input[last - i] = temp; } }

    public void reinitialize_for_next_iter(){
        selected_parents = new double[2][2];
        selected_parents2 = new double[2][2];
        off_spring1 = new double[2];
        off_spring2 = new double[2];
        off_spring3 = new double[2];
        off_spring4 = new double[2];
        off_springs = new double[4][2];
        final_array = new double[9][2];
        final_fitness = new double[9];
        finalized_array = new double[5][2];
    }
}
