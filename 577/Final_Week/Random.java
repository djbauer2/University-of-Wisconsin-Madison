import java.io.*;
import java.lang.*;
import java.util.*;

public class Random {
    public static void main(String[] args) throws java.lang.Exception {
           
            Scanner input = new Scanner(System.in);
            int variables = input.nextInt();
            int m = input.nextInt();
            String returns = "";
            for (int i = 0; i < m; i++){
                int x = input.nextInt();
                int y = input.nextInt();
                int z = input.nextInt();
                returns += FindAssignment(x, y, z);
            }
            System.out.println(returns);
    }

    static String FindAssignment(int x, int y, int z){
        String returns = "";
        int a = 1;
        int b = 1;
        int c = 1;
        if (x < 0)
            a = -1;
        else if (y < 0)
            b = -1;
        else if (z < 0)
            c = -1;
        return returns = a + " " + b + " " + c + " ";
            
    }
    
}
