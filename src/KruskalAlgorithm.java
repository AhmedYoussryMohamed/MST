import java.util.ArrayList;
import java.util.PriorityQueue;


public class KruskalAlgorithm {
	private PriorityQueue<Edge> pq;
	private ArrayList<Edge>[] adjList;
	private double[][] adjMatrix;
	private ArrayList<Edge> MST;
	private boolean[] visited;
	private int[] distance;
	private int[] parent;
	private int V;
	private int E;
	private double minCost = 0;
	
	public KruskalAlgorithm( ArrayList<Edge>[] adjList, double[][] adjMatrix, int V, int E ){
		this.adjList = adjList;
		this.adjMatrix = adjMatrix;
		this.V = V;
		this.E = E;
		
	}//end const.
	
	public void useAdjList(){
		pq = new PriorityQueue<Edge>();
		
		for( int i = 1 ;i <= V; i++ ){
			for( int j = 0 ;j < adjList[i].size(); j++){
				if( adjList[i].get(j).weight != 0  ){
					pq.add( adjList[i].get(j) );
				}
			}//end for j.
		}//end for i.
		
	}//end method.
	
	public void useAdjMatrix(){
		pq = new PriorityQueue<Edge>();
		for( int i = 1; i <= V; i++){
			for( int j = 1; j <= V; j++){
				if( adjMatrix[i][j] != 0 ){
					pq.add( new Edge( i, j, adjMatrix[i][j]) );
				}
			}//end for j.
		}//end for i.
		
	}//end method.
	
	public void printPriorityQueue(){
		
		while( !pq.isEmpty() ){
			
			Edge e = pq.poll();
			System.out.println( e.from +" ----> " + e.to + "  W:  " + e.weight );
		}//end while.
		
	}//end method.
	
	public void makeUnion(int a, int b) {
		parent[find(a)] = find(b);
	}// end method.
	
	public int find(int a) {

		if (parent[a] == a) {
			return a;
		}

		return parent[a] = find(parent[a]);
	}// end metohd.
	
	// O( E log V )
	public void kruskal(){
		minCost = 0;
		parent = new int[V + 1];
		for( int i = 1 ;i <= V; i++){
			parent[i] = i;
		}//end for i.
		
		MST = new ArrayList<Edge>();
		
		while( !pq.isEmpty() && MST.size() != (V - 1)  ){
			
			Edge e = pq.poll();
			if( find(e.from) != find(e.to) ){
				MST.add( e );
				minCost += e.weight;
				makeUnion( e.from, e.to);
			}
			
		}//end while.
		
		System.out.println("\nKruskal Algorithm:");
		System.out.println("MinCost: " + minCost);
	}//end method.
	
	public void printMST(){
		System.out.println("\nKRUSKAL: ");
		for( int i = 0 ;i < MST.size(); i++){
			Edge e = MST.get(i);
			System.out.println( e.from + "----->" + e.to +"  W: " + e.weight  );
		}//end for i.
		
		System.out.println(minCost);
		
	}//end method.
	
}//end class.
