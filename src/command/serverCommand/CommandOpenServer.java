package command.serverCommand;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import command.Command;
import symbol.SymbolTable;

public class CommandOpenServer implements Command{

	private Socket socket = null;
	private ServerSocket myServer = null;
	private int port = 0;
	private int time = 0;
	private BufferedReader input;
	private volatile boolean stop = false;
	@SuppressWarnings("serial")
	public static class PortNotExist extends Exception{

		public PortNotExist(String str) {
			super(str);
			// TODO Auto-generated constructor stub
		}
		
	}
	@SuppressWarnings("serial")
	public static class SocketNotOpen extends Exception{

		public SocketNotOpen(String str) {
			super(str);
			// TODO Auto-generated constructor stub
		}
		
	}
	public void stopServer() {
		this.stop = true;
	}
	// openDataServer, 150.10.12 , 540
	@SuppressWarnings("static-access")
	@Override
	public double docommand(String[] comString, SymbolTable symT) throws Exception {
		port = Integer.parseInt(comString[1]);
		time = Integer.parseInt(comString[2]);
		symT.server= this;
		if(port < 0)
			throw new PortNotExist("Error! Port do not Exist" + port);
		try {
			myServer = new ServerSocket(port); // socket that listing
			socket = this.myServer.accept(); // socket that handle the client
			input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			System.out.println("Listening!!");
			Thread t = new Thread(()->{
				String line= null;
				while(!stop) {
					try {
						line = input.readLine();
						System.out.println(line);
						String[] data= line.split(",");
						if(data.length != symT.numOfPaths()) 
						{
							//TODO: throws exception
						}
						symT.setValueToAll(data);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(line==null)
						break;
					try {
						Thread.sleep((long)1000 / time);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			t.start();
			
		}catch(SocketException e) {
			e = new SocketException("Error!! Socket can't be openned!!!");
		};
		
		
		// take input from client
		/*String inp = comString.toString();
		String []in = inp.split("openDataServer");
		input = new BufferedReader(new StringReader(in.toString()));*/
		return (double)comString.length;
	}
	public void closeConnection(Closeable c) {
		try {
			c.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close() {
		closeConnection(input);
		closeConnection(socket);
		closeConnection(myServer);
	}
}
