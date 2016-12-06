package gdrone;

import java.net.MalformedURLException;

import io.socket.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionManager implements IOCallback {
	private SocketIO socket;
	
	private boolean doYear = false;
	private boolean doAll = false;

	public ConnectionManager(String destination) {
		socket = new SocketIO();
		try {
			socket.connect(destination, this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void emitPopulation(Population pop) {
		JSONObject json = new JSONObject();
//		JSONArray ja = new JSONArray();
		try {
			//Add max, mean, min
			json.put("max", pop.getMaxDistance());
			json.put("min", pop.getMinDistance());
			json.put("mean", pop.getMeanDistance());
			
			//Add Fittest Route
//			ja.put(pop.getFittest().getParcelArray());
//			json.put("bestRoute", ja);
			
			socket.emit("generation", json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void emitBestRoute(Population pop) {
		JSONArray ja = new JSONArray();
		
		//Add Fittest Route
		ja.put(pop.getFittest().getParcelArray());
		
		socket.emit("bestRoute", ja);
	}

	@Override
	public void on(String event, IOAcknowledge arg1, Object... arg2) {
		// TODO Auto-generated method stub
		switch (event) {
		case "server:oneYear":
			System.out.println("advance GA by one year");
			doYear = true;
			return;
		case "server:finish": 
			System.out.println("Do all Years");
			doAll = true;
		default:
			return;
		}

	}

	@Override
	public void onConnect() {
		System.out.println("Connected to server");

	}

	@Override
	public void onDisconnect() {
		System.out.println("Disconnected");
	}

	@Override
	public void onError(SocketIOException arg0) {
		System.out.println("ERROR");

	}

	@Override
	public void onMessage(String arg0, IOAcknowledge arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(JSONObject arg0, IOAcknowledge arg1) {
		// TODO Auto-generated method stub

	}
	
	public boolean doAdvance() {
		return doYear;
	}
	
	public void yearFinished() {
		doYear = false;
	}
	
	public boolean doAll() {
		return doAll;
	}

}
