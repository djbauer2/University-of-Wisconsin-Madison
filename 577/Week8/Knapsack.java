import java.util.Currency;
import java.util.Scanner;

class itemValue {
    private long weight;
    private long value;
    itemValue (long weight, long value){
        this.weight = weight;
        this.value = value;
    }
    public long getWeight() {
        return weight;
    }
    public long getValue() {
        return value;
    }
}

class Knapsack{

    static void sort(itemValue [] items){
        for (int i = 0; i < items.length; i++){
            for (int j = i +1; j < items.length; j++){
                if (items[i].getWeight() > items[j].getWeight()) {
                    itemValue temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                }
            }
        }
    }

    static long Kadanes(itemValue [] entries, long capacity){
        long max_so_far = 0;
        long max_final = 0;
        for (int i = 0; i < entries.length; i++){
            if ((max_final +  entries[i].getWeight()) <= capacity)
                max_final = max_final + entries[i].getValue();
            if (max_so_far < max_final) 
                max_so_far = max_final;
            else if (max_final < 0)
                max_final = 0;
        }
        return max_so_far;
    
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int instances = input.nextInt();
        long [] returns = new long [instances];
        for (int i = 0; i < instances; i++){
            int item_amount = input.nextInt();
            long capacity = input.nextLong();
            itemValue items [] = new itemValue [item_amount];
            for (int j = 0; j < item_amount; j ++){
                items [j] = new itemValue(input.nextLong(), input.nextLong());
            }
            returns[i] = Kadanes(items, capacity);
        }
        for (int i = 0; i < instances; i++){
            System.out.println(returns[i]);
        }
    }
}