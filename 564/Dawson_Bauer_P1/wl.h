/* 
DAWSON BAUER
9078157287
djbauer2@wisc.edu
PURPOSE: Class and Method definitions
*/
#include <algorithm>
#include <cctype>
#include <string>
#include <iostream>
#include <sstream>
using namespace std;


class BST
{   
    public:
        typedef struct node {
            string key;
            node* left;
            node* right;
            int occurrence;
            int wordCount;
            node(string word) {key = word; right = nullptr; left = nullptr; occurrence = 0; wordCount = 0;}
        };
        node* root;
        
    public:

        //constructor
        BST() 
        {
            root = nullptr;
        }
        
        //destructor
        ~BST() 
        { 
            clear(root);//empties BST
            root = nullptr; //sets root to null ptr
        }

        /*
        Clears and nulls BST
        */
        void clear(node* root){
            if (root == nullptr)
                return;
            clear(root-> left);
            clear(root->right);
            root = nullptr;
        }

        /*
        Inserts new node into BST
        */
        void insert(string key, int wordCount){

            //creates new node with key
            node* newNode = new node(key);
            newNode->left = nullptr;
            newNode->right = nullptr;
            if (root == nullptr)
                root = newNode;
            //create placeholder
            node* current = nullptr;
            node* start = root;
            //counter
            //traverses tree to find last node
            while (start != nullptr){ //https://www.geeksforgeeks.org/insert-a-node-in-binary-search-tree-iteratively/
                current = start;
                if(key <= start->key)
                    start = start->left;
                else
                    start = start->right;
            }
            //if current is the same word as key, take occurrences of current
            if (current->key == key)
                newNode->occurrence = current->occurrence;

            //inserts left
            else if (key < current->key)
                current->left = newNode;
            //inserts right
            else
                current->right = newNode;
            //adds one to occurrence for the new node
            newNode->occurrence += 1;
            newNode->wordCount = wordCount;
        }

       
        /*
        Finds the number of words for desired word and returns key
        */
        int locate(string key, int n){
            transform(key.begin(), key.end(), key.begin(), ::tolower);//changes key to case insenitive
            if (root == nullptr)//root is null
                return 0;
            node* start = root;
            node* current = nullptr;
            //tree traversal until find first occurrence
            while(start->key != key){
                current = start;
                if(key < start->key)
                    start = start->left;
                else
                    start = start->right;
                if (start == nullptr)
                    return 0;
            }
            // if (start->key == key)
            //     current = start;
            //while loop when on the same word but not the same occurrence
            while(start->key == key){
                current = start;
                if (start->key == key && start-> occurrence == n)
                    return start->wordCount;
                start = start->left;
                }
            return current->wordCount;
        }

         /*
         Creates words from lines of input from txt file
         */
        void createWords(string input) {
            for (int i = 0; i < input.length(); i++){
                if(input[i]>='A' && input[i]<='Z')
                    input[i]=(input[i]-'A')+'a'; //change to lower case

                if(input[i]<'a' || input[i]>'z') 
                    input[i]=' '; //remove non-letters
            }
            //remove start and end spaces https://github.com/Zj-H/Word-Counting-Using-a-Binary-Search-Tree/blob/master/CountWords.cpp
            if(input.length()>0) 
                input.erase(0, input.find_first_not_of(" "));
            if(input.length()>0) 
                input.erase(input.find_last_not_of(" ") + 1);
             //split the string into words by space    
            int wordCount = 0;
            while(input.length()>0)
            {
                int i = input.find_first_of(" ");
                //end of the first word
                if(i==-1)//only one word 
                {
                    wordCount +=1;
                    insert(input, wordCount);
                    input.erase(0,i);//remove first word from line
                    break;
                }
                //get the substring of the line to get word
                else
                {
                    string word=input.substr(0, i);
                    wordCount += 1;
                    insert(word, wordCount);
                    input.erase(0,i);
                    //continue remove all spaces at start and end
                    if(input.length()>0) 
                        input.erase(0,input.find_first_not_of(" "));
                    if(input.length()>0) 
                        input.erase(input.find_last_not_of(" ") + 1);
                }
            }   
        } 
};