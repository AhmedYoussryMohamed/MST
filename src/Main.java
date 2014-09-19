import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static ArrayList<Edge>[] adjList;
	static double[][] adjMatrix;
	static int V;
	static int E;
	static FileReader fr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		fr = new FileReader("test4.txt");
		
		takeInput();
		GraphGenerator gg = new GraphGenerator();
//		gg.generateDenseGraph();
//		gg.generateSparseGraph();
//		V = gg.getV();
//		E = gg.getE();
//		adjList = gg.getAdjList();
//		adjMatrix = gg.getAdjMatrix();
		
		PrimAlgorithm prim = new PrimAlgorithm(adjList, adjMatrix, V, E);
		KruskalAlgorithm kruskal = new KruskalAlgorithm(adjList, adjMatrix, V, E);
		
		long beginTime=System.nanoTime();
		prim.primAdjMatrix();
		long endTime=System.nanoTime();
		long diff = endTime - beginTime;
		//prim.printMST();
		System.out.println( "Time in Nano: " + diff + "\n");
		
		beginTime=System.nanoTime();
		prim.primAdjList();
		endTime=System.nanoTime();
		//prim.printMST();
		diff = endTime - beginTime;
		System.out.println( "Time in Nano: " + diff +"\n" );
		
		kruskal.useAdjList();
		beginTime=System.nanoTime();
		kruskal.kruskal();
		endTime=System.nanoTime();
		//kruskal.printMST();
		diff = endTime - beginTime;
		System.out.println( "Time in Nano: " + diff + "\n");
		
//		kruskal.useAdjMatrix();
//		beginTime=System.nanoTime();
//		kruskal.kruskal();
//		endTime=System.nanoTime();
//		//kruskal.printMST();
//		diff = endTime - beginTime;
//		System.out.println( "Time in Nano: " + diff + "\n");
		
	}//end main.
	
	public static void takeInput() throws NumberFormatException, IOException{
		//BufferedReader bf = new BufferedReader( new InputStreamReader(System.in) );
		BufferedReader bf = new BufferedReader( fr );
		StringTokenizer st;
		
		V = Integer.parseInt( bf.readLine() );
		E = Integer.parseInt( bf.readLine() );
		
		adjList = new ArrayList[V+1];
		adjMatrix = new double[V+1][V+1];
		for(int i=1; i<=V; i++){ 
			adjList[i] = new ArrayList<Edge>();
		}
		
		for( int i = 1 ;i <= E ;i++){
			st = new StringTokenizer( bf.readLine(), " " );
			int t = Integer.parseInt( st.nextToken() );
			int f = Integer.parseInt( st.nextToken() );
			double w = Double.parseDouble( st.nextToken() );
			t += 1;
			f += 1;
			if( adjMatrix[t][f]==0 || adjMatrix[t][f] > w){
				adjList[t].add( new Edge(t, f, w) );
				adjList[f].add( new Edge(f, t, w) );
				
				adjMatrix[t][f] = w;
				adjMatrix[f][t] = w;
			}
			
		}//end for.
		
	}//end method.
	
}//end class.
