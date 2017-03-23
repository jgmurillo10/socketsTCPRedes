


import java.io.*;
import java.net.*;
public class TCPClient {
	public static final String IP="157.253.209.229";
	public static final int PORT=3000;

	public static void main(String[] args)throws Exception {
		String sentence, modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket(IP, PORT);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		sentence = inFromUser.readLine();
		
		outToServer.writeBytes(sentence+ '\n');
		
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER STAT: "+ modifiedSentence);
		String files = inFromServer.readLine();
		System.out.println("FROM SERVER FILES: "+ files);
		int iFile = inFromUser.read();
		outToServer.writeByte(iFile);
		clientSocket.close();
	}
}
