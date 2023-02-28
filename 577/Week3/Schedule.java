
import java.util.Scanner;  // Import the Scanner class




class Schedule {

    static int findIntervals (int [] start, int [] end){
        int intervals = 1;
        for (int i = 0; i < start.length; i++){
            for (int j = 0; j < start.length; j++){
                if (end[i] < start[j])
                    intervals+=1;
            }
        }
        return intervals;
    }
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    int instances = input.nextInt();
    int [] values = new int [instances];
    for (int i = 0; i < instances; i++){
        int jobs = input.nextInt();
        int [] startTimes = new int [jobs];
        int [] endTimes = new int [jobs];
        for (int j = 0; j < jobs; j++){
            startTimes[j] = input.nextInt();
            endTimes[j] = input.nextInt();
        }
       values [i] = findIntervals(startTimes, endTimes);
    }
    for (int x = 0; x < instances; x++){
        System.out.println(values[x]);
    }
   input.close();
  }
}