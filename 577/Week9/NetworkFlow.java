
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.LinkedList;
 
class NetworkFlow {
    //Method based from: https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
    boolean BFS(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array
        boolean visited[] = new boolean[t+1];
        for (int i = 0; i <= t; ++i)
            visited[i] = false; //mark all as unvisited
 
        // Create queue
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        //mark source as visited
        visited[s] = true;
        //parent of s is -1
        parent[s] = -1;
 
        //BFS
        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v <= t; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    // Check if we are at t
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    //create parent [] by checking if an edge cap exists between 
                    //numbers and set u as last v
                    //create visit[] to see if we have been at a current node
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
 
        // return false if didn't reach end
        return false;
    }
    //Method based from: https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
    // Returns the maximum flow from s to t in the graph
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;
        // Create a residual graph

        int rGraph [][] = graph.clone();
        //parent[] to be filled by BFS
        int parent[] = new int[t+1];
        //initial flow is 0
        int max_flow = 0; 
 
        // Augment the flow while there is path from source to end
        while (BFS(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edges along the path filled by BFS.
            int path_flow = Integer.MAX_VALUE;

            for (v = t; v != s; v = parent[v]) {
                //parent = the queue.poll--(head) of queue which is s at first
                u = parent[v];
                //path flow is the min of the existing path flow or the rGraph
                //path flow would be rGraph at first
                //want to find bottleneck of path via min capacity
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }
            
            // Add path flow to max flow
            max_flow += path_flow;

            // update residual capacities of the edges and
            // reverse edges along the path for rGraph
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
        }
 
        // Return the max flow
        return max_flow;
    }
 
    public static void main(String[] args) throws java.lang.Exception {
        
        Scanner input = new Scanner(System.in);
        int instances = input.nextInt();
        int [] returns = new int [instances];
        for (int i = 0; i < instances; i++){
            int V = input.nextInt();// Number of vertices in graph
            int E = input.nextInt(); //Number of edges in graph
            int [][] graph = new int [V+1][V+1];

            for (int j = 0; j < E; j++){
                int origin = input.nextInt();
                int dest = input.nextInt();
                int capacity = input.nextInt();
                graph[origin][dest] += capacity;
            }

            NetworkFlow network = new NetworkFlow();

            returns[i] = network.fordFulkerson(graph, 1, V);
        }

        for (int i = 0; i < instances; i++){
            System.out.println(returns[i]);
        }
        input.close();
    }
}