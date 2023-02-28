import java.util.Scanner;  // Import the Scanner class




class Furtherest {

static boolean in_cache (int [] cache, int page){
    boolean flag = false;
    for (int i = 0; i < cache.length; i++){
        if (page != cache[i]){
            flag = false;
        }
        else {
            flag = true;
        }
    }
    return flag;
}

static int find_fartherest (int [] cache, int [] pages, int index) {
    int location = 0;
    int [] measurements = new int [cache.length];

    for (int i = index; i < cache.length; i++){
        int iterations = 0;

        for (int j =index; j <pages.length; j++){
            if (cache [i] == pages[j]){
                measurements[i] = iterations;
                break;
            }
            else {iterations += 1;}
        }
    }
    //have measurements of all elements in cache
    int max = measurements[0];
    for (int i = 1; i < measurements.length; i++){

        if (measurements[i] > max || measurements[i] == pages.length-1){
            max = measurements[i];
            location = i;
        }
    }
    //now have max of the measurements array and location
    return location;
}

public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    int instances = input.nextInt();
    for (int i = 0; i < instances; i++){
        int pages_amount = input.nextInt();
        int faults = 0;
        int [] cache = new int [pages_amount];
        int requests = input.nextInt();
        int [] pages = new int [requests];
        //add values to array
        for (int j = 0; j < requests; j++){
                pages [j] = input.nextInt();
        }
        //initialize cache with first values
       for (int j = 0; j < cache.length; j++ ){
            cache [j] = pages[j];
            faults += 1;
       }
       //replace fartherest value if needed
       for (int j = cache.length; j < pages.length; j++){
           if (!in_cache(cache, pages[j])) {
               cache[find_fartherest(cache, pages, j)] = pages[j];
               faults +=1;
           }
       }
       System.out.println(faults);
    }
    input.close();
  }
}