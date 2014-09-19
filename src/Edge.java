
public class Edge implements Comparable<Edge>{
	
	int from;
	int to;
	double weight;
	
	public Edge( int f, int t, double w ){
		from = f;
		to = t;
		weight = w;
	}//end const.

	@Override
	public int compareTo(Edge obj) {
		return Double.valueOf( weight ).compareTo( obj.weight );
	}
	
}//end class.