package command.serverCommand;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import command.Command;
import symbol.SymbolTable;

public class CommandConnect implements Command{

	private Socket socket = null;
	private PrintWriter pw = null;
	private String ip;
	private int port = 0 ;
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
	@Override
	public double docommand(String[] comString, SymbolTable symT) throws Exception {
		ip = comString[1];
		port = Integer.parseInt(comString[2]);
		symT.client = this;
		if(port == 0)
			throw new PortNotExist("Port Error!!!" + port);
		try{
			socket = new Socket(ip, port);
			System.out.println("Connected!!!");
			pw = new PrintWriter(socket.getOutputStream());
		}catch(Exception e) {
			e = new SocketNotOpen("Error!! Socket can't be openned!!!");
		}
		return (double)comString.length;
	}
	public void sendCommand(String s) {
		pw.write(s + "\r\n");
		pw.flush();
	}
	public void sendValue(String s, Double value) {
		pw.write("set "+ s + " " + value.toString() + "\r\n");
		pw.flush();
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
		closeConnection(pw);
		closeConnection(socket);
	}
}
