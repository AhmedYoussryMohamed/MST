import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class GraphGenerator {
	
	private int V = 1000;
	private int E;
	private ArrayList<Edge>[] adjList;
	private double[][] adjMatrix;
	
	public void generateDenseGraph(){
		
		E = 0;
		adjList = new ArrayList[V+1];
		adjMatrix = new double[V+1][V+1];
		
		for(int i=1; i<=V; i++){ 
			adjList[i] = new ArrayList<Edge>();
		}
		Random r = new Random();
		
		for( int i = 1; i <= V ;i++){
			for( int j = i+1; j <= V ;j++){
				double w = 1 + r.nextInt(1000);
				adjList[i].add( new Edge( i, j, w ) );
				adjList[j].add( new Edge( j, i, w ) );
				adjMatrix[i][j] = w;
				adjMatrix[j][i] = w;
				E += 1;
			}//end for j.
		}//end for i.
		
		System.out.println( "Dense Graph generated.\n" );
	}//end method.
	
	public void generateSparseGraph() throws IOException{
		
		int bound = ( V * (V-1) ) / 4;
		
		E = 0;
		adjList = new ArrayList[V+1];
		adjMatrix = new double[V+1][V+1];
		boolean[] visited = new boolean[V+1];
		
		for(int i=1; i<=V; i++){ 
			adjList[i] = new ArrayList<Edge>();
		}
		Random r = new Random();
		// To make sure that the graph is connected.
		
		for( int i = 1; i < V; i++ ){
			double w = 1 + r.nextInt(1000);
			adjList[i].add( new Edge( i , i+1, w) );
			adjList[i+1].add( new Edge( i+1 , i, w) );
			
			adjMatrix[i][i+1] = w;
			adjMatrix[i+1][i] = w;
			
			E += 1;
		}//end for i.
		
		Random r2 = new Random();
		while( E < bound ){
			double w = 1 + r2.nextInt(1000);
			int from = 1 + r2.nextInt( V );
			int to = 1 + r2.nextInt( V );
			
			if( from != to && adjMatrix[from][to] == 0 ){
				adjList[ from ].add( new Edge( from, to, w )  );
				adjList[ to ].add( new Edge( to, from, w )  );
				
				adjMatrix[from][to] = w;
				adjMatrix[to][from] = w;
				E += 1;
			}
			
		}//end While.
		
		System.out.println( "Sparse Graph generated.\n" );
	}//end method.
	
	public void printAdjList(){
		
		for( int i = 1;i < adjList.length; i++){
			for( int j = 0;j < adjList[i].size(); j++){
				Edge e = adjList[i].get(j);
				System.out.println( e.from + " ----> " + e.to + "  W:  " + e.weight );
			}
		}
		
	}//end method.
	
	public void printAdjMatrix(){
		for( int i = 1;i < adjMatrix.length; i++){
			for( int j = 1;j < adjMatrix[i].length; j++){
				if( adjMatrix[i][j] != 0 ){
					System.out.println( i + " ----> " + j + "  W:  " + adjMatrix[i][j] );
				}
			}
		}
		
	}//end method.
	
	public void setV(int v){
		this.V = v;
	}
	
	public ArrayList<Edge>[] getAdjList(){
		return adjList;
	}
	
	public double[][] getAdjMatrix(){
		return adjMatrix;
	}
	
	public int getV(){
		return V;
	}
	
	public int getE(){
		return E;
	}
	
	
}//end class.
