


import java.io.*;
import java.net.*;
public class TCPClient {
	public static final String IP="157.253.209.229";
	public static final int PORT=3000;
	//path where are all the server files
	public final static String PATH = "./data/server";

public static void main(String[] args)throws Exception {
		

		String sentence, modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket(IP, PORT);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
		sentence = inFromUser.readLine();
		
		outToServer.writeBytes(sentence+ '\n');
		
		modifiedSentence = inFromServer.readLine();
		System.out.println("FROM SERVER STAT: "+ modifiedSentence);
		String files = inFromServer.readLine();
		System.out.println("FROM SERVER FILES: "+ files);
		String iFile = inFromUser.readLine();
		outToServer.writeBytes(iFile + '\n');
		int num= Integer.parseInt(iFile)+1;		
		
		  byte[] contents = new byte[10000];
		 //Initialize the FileOutputStream to the output file's full path.
        FileOutputStream fos = new FileOutputStream(new File(PATH , "test" + num + ".txt"));
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        
        //No of bytes read in one read() call
        int bytesRead = 0; 
        
        while((bytesRead=clientSocket.getInputStream().read(contents))!=-1)
            bos.write(contents, 0, bytesRead); 
        
        bos.flush(); 
        System.out.println("terminó");
		clientSocket.close();
	}
}