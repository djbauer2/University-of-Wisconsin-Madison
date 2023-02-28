#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;
/* Method counts inversions from input array of size n
 * From GeeksforGeeks.org: https://www.geeksforgeeks.org/counting-inversions/
*/

int inverse_counter(int arr [], int n)
{ 
    int inv_count = 0;
    for (int i = 0; i < n - 1; i++)
        for (int j = i + 1; j < n; j++)
            if (arr[i] > arr[j]){
                inv_count++;
            }

    return inv_count;
}
 
int main()
{
    int instances;
    cin >> instances;
    int return_values [instances];

    for (int i = 0; i < instances; i++){
        int elements;
        cin >> elements;
        int array [elements];

        for (int j = 0; j < elements; j++){
            int input;
            cin >> input;
            array[j] = input;
        }

         int n = sizeof(array) / sizeof(array[0]);
        return_values[i] = inverse_counter(array, n);
    }
    for (int j = 0; j < instances; j++){
        cout << return_values[j] << endl;
   }
    return 0;
}