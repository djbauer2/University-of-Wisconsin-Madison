import java.util.Scanner;  // Import the Scanner class


public class Pairs {

    static void sortBoth (long [] arr1, long [] arr2) {
        for (int i = 0; i < arr1.length; i++){
            for (int j = i +1; j < arr1.length; j++){
                if (arr1[i] > arr1[j]) {
                    long temp = arr1[i];
                    arr1[i] = arr1[j];
                    arr1[j] = temp;
                    long temp2 = arr2[i];
                    arr2[i] = arr2[j];
                    arr2[j] = temp2;
                }
            }
        }
    }

    static long countIntersections (long [] arr){
        long intersections = 0;
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                if (i < j && arr[i] > arr [j])
                    intersections += 1;
            }
        }
        return intersections;
    }
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int instances = input.nextInt();
        long [] intersections = new long [instances];
        for (int i = 0; i < instances; i++){
            int pairs = input.nextInt();
            long [] q_arr = new long [pairs];
            long [] p_arr = new long [pairs];
            for (int j = 0; j < pairs; j++){
                q_arr[j] = input.nextInt();
            }
            for (int j = 0; j < pairs; j++){
                p_arr[j] = input.nextInt();
            }
            sortBoth(q_arr, p_arr);
            intersections[i] = countIntersections(p_arr);
        }
        for (int i = 0; i < instances; i++)
            System.out.println(intersections[i]);
        input.close();
        
    }
   


}
