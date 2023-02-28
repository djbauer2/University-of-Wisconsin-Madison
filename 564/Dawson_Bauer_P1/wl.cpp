/* 
DAWSON BAUER
9078157287
djbauer2@wisc.edu
PURPOSE: Implementation of wl.h
*/
#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <vector>
#include <algorithm>
#include "wl.h"
using namespace std;

/*
Checks if string is a number
*/
bool isNumber(string s)
{
    for (int i = 0; i < s.length(); i++)
        if (isdigit(s[i]) == false)
            return false;
 
    return true;
}
/*
Main method to run program
*/
int main(int argc, char* argv[]){
    BST tree;
    bool flag = true;
    
    while(flag){//run while there are command inputs
        vector<string> input_list;
        string input;
        getline (cin, input);
        istringstream ss(input);
        string word;
        while(getline(ss, word, ' ')){
            if(!word.empty())
                input_list.push_back(word);
        }
        //changes key to case insenitive
        for (int i = 0; i < input_list.size(); i++)
            transform(input_list[i].begin(), input_list[i].end(), input_list[i].begin(), ::tolower);

    if (input_list[0] == "new"){
        // if(!input_list[1].empty())
        //    cout<< "ERROR: Invalid command" << endl;
        tree.clear(tree.root);
    }
    else if (input_list[0] == "end"){
        if(!input_list[1].empty())
           cout<< "ERROR: Invalid command" << endl;
        flag = false;
        return 1;
    }
    else if (input_list[0] == "load"){
        if(!input_list[3].empty())
           cout<< "ERROR: Invalid command" << endl;
        tree.clear(tree.root);
        ifstream the_file (input_list[1]);
        if (!the_file.is_open())
            cout<< "Could not find file\n";
        string s =  string((istreambuf_iterator<char>(the_file)), istreambuf_iterator<char>());//wrong
        tree.createWords(s);
    }
    else if (input_list[0] == "locate"){
        if(!input_list[4].empty())
           cout<< "ERROR: Invalid command" << endl;
        if (!isNumber(input_list[2]) || input_list[2].empty())
            std::cout<< "ERROR: Invalid command" << endl;
        else{
            stringstream n(input_list[2]);
            int x = 0;
            n >> x;
            if (tree.locate(input_list[1], x) == 0)
                cout << "No matching entry" << endl;
            else
                cout << tree.locate(input_list[1], x) << endl;
        }
    }
    else {
        cout<< "ERROR: Invalid command" << endl;
    }

}

}
