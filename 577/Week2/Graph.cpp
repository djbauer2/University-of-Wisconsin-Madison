#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <vector>
#include <algorithm>

using namespace std;

class Tree {
    public:
        typedef struct node {
           string value;
           vector<string> leaves;
           node(string input){value = input;}
        };
    
    node* createNode (vector<string> input){
        node* root;
        root->value= input[0];
        for (int i =1; i < input.size(); i++){
            root->leaves.push_back(input[i]);
        }
        return root;
    }
    void transveral (node* root){
        for (int i = 1; i < root->leaves.size(); i++){
            node* leaf;
            leaf -> value = root->leaves[i];
            while (!leaf->leaves[i].empty()){
                print(leaf->leaves, leaf->leaves[i]);
                transveral(leaf);
            }
        }
    }
    void print (vector<string> vec, string output){
        sort( vec.begin(), vec.end());
        vec.erase( unique( vec.begin(), vec.end() ), vec.end() );
        cout << output;
    }
};
bool isNumber(string s)
{
    for (int i = 0; i < s.length(); i++)
        if (isdigit(s[i]) == false)
            return false;
 
    return true;
}

int main(int argc, char* argv[]){
    Tree graph;
    
    vector<string> input_list;
    string input;
    ifstream file ("GivenTest.txt");
    string instances;
    getline(file, instances);
    stoi(instances);
    getline (file, input);
    istringstream ss(input);
    string word;
    while(getline(ss, word, ' ')){
        if(!word.empty())
            input_list.push_back(word);
        graph.transveral(graph.createNode(input_list));

    }
    
    

}