package gdrone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketManager {
	private Socket socket = null;

	public SocketManager(String ip, int port) throws UnknownHostException, IOException {
		System.out.println("[Connecting to socket...]");
		this.socket = new Socket(ip, port);
	}

	// writes and receives the full message int the socket (String)
	public String echo(String message) {
		try {
			// out &amp; in
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					getSocket().getInputStream()));

			// writes str in the socket and read
			out.println(message);
			String returnStr = in.readLine();
			return returnStr;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	// get the socket instance
	private Socket getSocket() {
		return socket;
	}
}
