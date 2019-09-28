package com.example.demo.stream;

import java.util.*;

class Graph
{
    public static Integer t = 0;
    static {
        System.out.println(t);
        System.out.println("static block");
        t = 1;
        System.out.println(t);
    }
    private LinkedList[] adjLists;
    private boolean[] visited;

    private Graph(int vertices)
    {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<Integer>();
    }

    void addEdge(int src, int dest)
    {
        adjLists[src].add(dest);
    }

    void DFS(int vertex)
    {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator ite = adjLists[vertex].listIterator();
        while (ite.hasNext())
        {
            int adj = (int) ite.next();
            if (!visited[adj])
                DFS(adj);
        }
    }


    public static void main(String args[])
    {
        Graph g = new Graph(5);

        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(2, 3);

        System.out.println("Following is Depth First Traversal");

        g.DFS(0);
    }
}