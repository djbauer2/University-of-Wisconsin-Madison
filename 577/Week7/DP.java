import java.io.Console;
import java.sql.Struct;
import java.util.Scanner;  // Import the Scanner class

class WorkIntervals {
    private int start;
    private int finish;
    private int weight;

    WorkIntervals (int start, int finish, int weight){
        this.start = start;
        this.finish = finish;
        this. weight = weight;
    }
    public int getStart(){
        return start;
    }
    public int getFinish () {
        return finish;
    }
    public int getWeight() {
        return weight;
    } 
    public void zeroOut(){
        this.start = 0;
        this.finish = 0;
        this.weight = 0;
    }
}
public class DP {

    static void sort(WorkIntervals [] schedule){
        for (int i = 0; i < schedule.length; i++){
            for (int j = i +1; j < schedule.length; j++){
                if (schedule[i].getFinish() > schedule[j].getFinish()) {
                    WorkIntervals temp = schedule[i];
                    schedule[i] = schedule[j];
                    schedule[j] = temp;
                }
            }
        }
    }

    static int findIndex(WorkIntervals job1, WorkIntervals job2){
        if (job2.getStart() >= job1.getFinish())
            return 1;
        return 0;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int instances = input.nextInt();
        long [] returns = new long [instances];
        for (int i = 0; i < instances; i++){
            int job = input.nextInt();
            long [] m = new long [job];
            m[0] = 1;
            WorkIntervals [] schedule = new WorkIntervals [job];
            for (int j = 0; j < job; j++){
                WorkIntervals value = new WorkIntervals(input.nextInt(), input.nextInt(), input.nextInt());
                schedule[j] = value;
            }
            sort(schedule);
           // WorkIntervals accepted = schedule[0];
            for (int j = 1; j < schedule.length; j++) {
                int index = 0;
                if (j < schedule.length ) {
                    index = findIndex(schedule[j-1], schedule[j]);
                    // if (index == 1) {
                    //     accepted = schedule[j-1];
                    // }
                }
                m[j] = Math.max(m[j-1], m[index] + schedule[j].getWeight());
            }
            returns[i] = m[schedule.length - 1];
        }
        for (int i = 0; i < returns.length; i ++){
            System.out.println (returns[i]);
        }
        input.close();
    }
}