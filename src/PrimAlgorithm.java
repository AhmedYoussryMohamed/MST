import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimAlgorithm {
	private PriorityQueue<Edge> pq;
	private ArrayList<Edge>[] adjList;
	private double[][] adjMatrix;
	private ArrayList<Edge> MST;
	private boolean[] visited;
	private double[] distance;
	private int[] parent;
	private int V;
	private int E;
	private double minCost = 0;
	
	public PrimAlgorithm(ArrayList<Edge>[] adjList, double[][] adjMatrix, int V, int E){
		this.adjList = adjList;
		this.adjMatrix = adjMatrix;
		this.V = V;
		this.E = E;
		
		
	}//end method.
	
	// O(V^2)	
	public void primAdjMatrix(){
		MST = new ArrayList<Edge>();
		minCost = 0;
		visited = new boolean[V+1];
		distance = new double[V+1];
		int[] prev = new int[V+1];
		int vertex = 1;
		
		for( int i = 1 ;i <= V; i++ ){
			distance[i] = Integer.MAX_VALUE;
		}
		//distance[1] = 0;
		for( int i = 1 ;i < V; i++){
			
			visited[vertex] = true;
			double minDist = Double.MAX_VALUE;
			int minVertex = -1;
			
			for (int j = 1; j <= V; j++ ){
				if( !visited[j] ){

					double d = adjMatrix[vertex][j];
					if( d > 0 && d < distance[j] ){
						distance[j] = adjMatrix[vertex][j];
						prev[j] = vertex;
 					}
					if( distance[j] < minDist ){
						minDist = distance[j];
						minVertex = j;
					}
				}//end if.
				
			}//end for j.
			
			if( minVertex == -1 ){
				System.out.println("Graph is disconnected.");
				break;
			}
			MST.add( new Edge( prev[minVertex] , minVertex, adjMatrix[ prev[minVertex] ][minVertex] ) );
			vertex = minVertex;
			minCost += distance[vertex];
			
		}//end for i.
		
		System.out.println("\nPrim Adj Matrix:");
		System.out.println("MinCost: " + minCost);
		
	}//end method.
	
	//O((|V| + |E|) log |V|) = O(|E| log |V|)
	public void primAdjList(){
		
		MST = new ArrayList<Edge>();
		minCost = 0;
		visited = new boolean[V+1];
		pq = new PriorityQueue<Edge>();
		pq.add( new Edge( -1 ,1,0 ) );
		
		while( !pq.isEmpty() ){
			Edge e = pq.poll();
			
			if( !visited[ e.to ] ){
				visited[e.to] = true;
				minCost += e.weight;
				if( e.from != -1 ){
					MST.add( e );
				}
				for( int i = 0 ;i < adjList[e.to].size(); i++){
					Edge ne = adjList[e.to].get(i);
					if( !visited[ ne.to ] && ne.weight != 0 ){
						pq.add( ne );
					}
				}//end for.
				
			}//end if.
			
		}//end while.
		
		
		System.out.println("Prim Adj List:");
		System.out.println("MinCost: " + minCost);
	}//end method.
	
	public void printMST(){
//		System.out.println("From -----> To      Weight");
		minCost = 0;
		for( int i = 0 ;i < MST.size(); i++){
			Edge e = MST.get(i);
			if( e.from != -1){
				System.out.println( e.from + " -------> " + e.to +"   W: " + e.weight  );
				minCost += e.weight;
			}
		}//end for i.
		
		System.out.println(minCost);
		
	}//end method.
	
}//end class.
