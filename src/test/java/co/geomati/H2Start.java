package co.geomati;

import org.h2.tools.Server;

public class H2Start {

	public static void main(String[] args) throws Exception {
		Server server = Server.createTcpServer(args);
		if (server.start() == null) {
			throw new Exception("Cannot start server");
		}
		System.out.println("Press ENTER to stop the server");
		System.in.read();
		server.stop();
	}
}
