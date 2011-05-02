package edu.mccc.cos210.cmwar;
import java.util.List;
import java.util.ArrayList;

/**
* CMConnection for Circuit Maker with AutoRouter
* A Connection is what can connect two Connectables / Components
* @author Rohit Kumar
* @version 0.1
*/
public class CMConnection implements Connection {
	/**
	*Array of size 2 holding connection points
	*/
	private ConnectionPoint[] ends;
	public CMConnection() {
		ends = new ConnectionPoint[2];
	}

	/**
	* Method to get the components connected by this connection
	* @return An array of size 2 with the ConnectionPoints
	* @test.status ok
	*/
	public ConnectionPoint[] getEnds() {
		return ends;
	}

	/**
	* Method to set the ends of a connection.
	* @param a A Component to be connected.
	* @param b Opposite Component to be connected to A, and vice versa.
	* @test.status ok
	*/
	public void setEnds(ConnectionPoint a, ConnectionPoint b) {
		ends[0] = a;
		ends[1] = b;
	}
	public static void main(String[] sa) throws Exception {
		ConnectionPoint cp1 = new CMConnectionPoint(1, 1, 5, 5);
		ConnectionPoint cp2 = new CMConnectionPoint(3, 3, 5, 5);
		CMConnection myConnection = new CMConnection();
		myConnection.setEnds(cp1, cp2);
	}
}