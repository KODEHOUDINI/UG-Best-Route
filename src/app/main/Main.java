package app.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import app.algorithms.BFS;
import app.algorithms.Dijkstra;
import app.graph.Edge;
import app.graph.Graph;
import app.graph.Node;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
public class Main {
    static Graph graph = new Graph();
public static void main(String[] args) {

     // Nodes in the graph.
  /*   Node gym = new Node("Gym");
     Node diaspora = new Node("Diaspora");
     Node ish = new Node("ISH");
     Node nightMarket = new Node("Night Market");
     Node sarbahHall = new Node("Sarbah Hall");
     Node commonWealth = new Node("Common Wealth");
     Node greatHall = new Node("Great Hall");        
     Node legonHall = new Node("Legon Hall");
     Node akuafoHall = new Node("Akuafo Hall");
     Node voltaHall = new Node("Volta Hall");
     Node balmeLibrary = new Node("Balme Library");
     Node cbas = new Node("CBAS");
     Node mainGate = new Node("Main Gate");
     Node jqb = new Node("JQ Building");
     Node lawSchool = new Node("Law School");
     Node busSchool = new Node("Business School");
     Node gcb = new Node("GCB");
     Node csdepartment = new Node("CS Department");
     Node polictialScienceDepartment = new Node("Political Science Department");
     Node nb = new Node("NB");
     Node nnb = new Node("NNB");

     graph.addEdge(new Edge(gym, diaspora, 500, 10)); 
     graph.addEdge(new Edge(gym, ish, 415, 8)); 
     graph.addEdge(new Edge(gym, nightMarket, 634, 9)); 
     
     graph.addEdge(new Edge(diaspora, ish, 400, 8)); 
     graph.addEdge(new Edge(ish, nightMarket, 214, 3)); 

     graph.addEdge(new Edge(nightMarket, commonWealth, 1025, 20)); 
     graph.addEdge(new Edge(nightMarket, legonHall, 914, 17)); 
     graph.addEdge(new Edge(nightMarket, sarbahHall, 350, 5)); 

     graph.addEdge(new Edge(commonWealth, greatHall, 515, 9)); 
     graph.addEdge(new Edge(commonWealth, voltaHall, 440, 5)); 
     graph.addEdge(new Edge(commonWealth, legonHall, 460, 5)); 

     graph.addEdge(new Edge(sarbahHall, legonHall, 630, 12)); 
     graph.addEdge(new Edge(sarbahHall, akuafoHall, 460, 8)); 

     graph.addEdge(new Edge(legonHall, akuafoHall, 583, 7)); 
     graph.addEdge(new Edge(legonHall, balmeLibrary, 530, 6)); 
     graph.addEdge(new Edge(legonHall, voltaHall, 260, 3)); 

     graph.addEdge(new Edge(akuafoHall, cbas, 385, 5)); 
     graph.addEdge(new Edge(akuafoHall, csdepartment, 780, 13)); 
     graph.addEdge(new Edge(akuafoHall, balmeLibrary, 580, 7)); 

     graph.addEdge(new Edge(cbas, mainGate, 624, 6)); 
     graph.addEdge(new Edge(cbas, jqb, 610, 9)); 
     graph.addEdge(new Edge(jqb, lawSchool, 466, 5)); 

     graph.addEdge(new Edge(lawSchool, csdepartment, 384, 4)); 

     graph.addEdge(new Edge(balmeLibrary, lawSchool, 960, 18)); 
     graph.addEdge(new Edge(balmeLibrary, busSchool, 203, 4)); 

     graph.addEdge(new Edge(voltaHall, busSchool, 390, 3)); 
     graph.addEdge(new Edge(voltaHall, balmeLibrary, 415, 5)); 

     graph.addEdge(new Edge(voltaHall, balmeLibrary, 415, 5)); 
     
     graph.addEdge(new Edge(busSchool, gcb, 433, 5)); 
     graph.addEdge(new Edge(busSchool, nb, 424, 5)); 
     graph.addEdge(new Edge(busSchool, csdepartment, 389, 4)); 
     
     graph.addEdge(new Edge(polictialScienceDepartment, csdepartment, 386, 4)); 
     graph.addEdge(new Edge(polictialScienceDepartment, nb, 204, 3)); 
     graph.addEdge(new Edge(nb, nnb, 330, 4)); 
     graph.addEdge(new Edge(nnb, gcb, 160, 3)); */
     //File access start
    File file = new File(System.getProperty("user.dir") + "\\app\\main\\locations.txt"); // Change this to your file name

     try {
         BufferedReader bi = new BufferedReader(new FileReader(file));
         String s;
         while ((s = bi.readLine()) != null) {
         String[] fields = s.split(", ");
         graph.addEdge(new Edge(new Node(fields[0]), new  Node(fields[1]), Integer.parseInt(fields[2]), Integer.parseInt(fields[3]))); 
         }
       }
     catch (IOException e) {
       // Handle error...
        System.out.println(e);
     }
     //File access end
     //Albert edits
     graph.printNodes();
     int i = getInput("Select your start point: ");
     Node soucNode = (Node) graph.selectNode(i - 1);
     System.out.println(soucNode.getName());
      i = getInput("Select your destination : ");
     Node destNode = (Node) graph.selectNode(i - 1);
     System.out.println(destNode.getName());
     //Albert edits end
     
     
    // Node soucNode = graph.getNodeByName(souceName);
     //Node destNode = graph.getNodeByName(destName);
     ArrayList<Node> shortestPath = Dijkstra.findShortestPath(graph, soucNode, destNode);

     System.out.println("Shortest Path : " + printPath(shortestPath));
     System.out.println("Total Distance : "+Dijkstra.getDistance(destNode));

     ArrayList<ArrayList<Node>> allPaths = BFS.findAllPaths(graph, soucNode, destNode);

     StringBuilder builder = new StringBuilder();
     for (ArrayList<Node> nodes :allPaths.subList(allPaths.size() - 5, allPaths.size()-1) ){
         String distance = String.format("%.3f",  graph.calculateDistance(nodes)/1000f) + "km";
         builder.append(printPath(nodes) + ", " + distance+ "\n");
     }

     System.out.println(builder.toString());
}

public static int getInput(String k) {
    System.out.println(k);
    int a = 0;
    
    while(true) {
        
        try {
            Scanner s = new Scanner(System.in);
            a = s.nextInt();
                if (a <=0 || a >= graph.getSize() ) {
                    throw new Exception();
//                    System.out.print("Enter a valid input >>>");
//                    continue;
                }
            break;
        }catch(Exception e){
            System.out.println("Sorry invalid input");
            System.out.print(k);        }
    }
    return a;
}
public static String printPath(ArrayList<Node> path){
    boolean start = true;
    StringBuilder pbuilder = new StringBuilder();
    for(Node n : path){
        pbuilder.append( (start && !(start = false) ? "" : " -> " ) + n.getName());
    }
    return pbuilder.toString();
}
}
