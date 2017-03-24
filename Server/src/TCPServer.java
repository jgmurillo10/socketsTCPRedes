

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class TCPServer extends Thread {
	//port for listen to, same as client port
	public final static int PORT = 3000;
	//timeout in seconds for the server socket
	public final static int TIME_OUT_SECONDS = 60;
	//path where are all the server files
	public final static String PATH = "./data/server";
	//server socket that creates a socket per client
	private ServerSocket serverSocket;

	private static final int CHUNK_SIZE = 1024;


	/**
	 * Constructor of the class - sets a timeout for the server socket, after 10 secs the throws an exception
	 * @throws IOException
	 */
	public TCPServer () throws IOException {
		serverSocket = new ServerSocket(PORT);
		serverSocket.setSoTimeout(TIME_OUT_SECONDS*1000);
	}
	/**
	 * Method that display the files to the client
	 * @param files
	 * @throws IOException 
	 */
	public static String viewFiles(ArrayList<String> files) throws IOException{
		String msg="";
		for (int i = 0; i < files.size(); i++) {
			msg+=(i+" "+ files.get(i))+':';
		}
		System.out.println(msg);
		return msg;
	}
	/**
	 * Returns an ArrayList<String> with the names of the files contained in the folder path
	 * @param path
	 * @return
	 */
	public static ArrayList<String> getFiles(String path){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> files= new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if(listOfFiles[i].isFile()){
				files.add(listOfFiles[i].getName());
			}
		}
		return files;
	}
	/**
	 * Run method for the server thread
	 */
	public void run() {
		String clientSentence, capitalizedSentence;
		while (true) {
			try {

				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket client = serverSocket.accept();
				System.out.println("Just connected to " + client.getRemoteSocketAddress());
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(client.getOutputStream());
				clientSentence = inFromClient.readLine();
				capitalizedSentence = clientSentence.toUpperCase()+ '\n';
				outToClient.writeBytes(capitalizedSentence);

				ArrayList<String> files = getFiles(PATH);
				System.out.println(files.size()+ " size");
				System.out.println(viewFiles(files));
				outToClient.writeBytes(viewFiles(files) + '\n');
				String isFile=inFromClient.readLine();
				int iFile = Integer.parseInt(isFile);
				System.out.println("FROM CLIENT: "+ iFile+ " "+ files.get(iFile));
				int iFile2 = iFile+1;

				File myFile = new File( PATH + "/test" + iFile2 + ".txt" );
				outToClient.writeUTF(myFile.getName());
				outToClient.writeLong(myFile.length());

				System.out.println("Sending " + myFile.getName() + " ("
						+ myFile.length() + " bytes) to server...");
				writeFile(myFile, outToClient);
				System.out.println("Finished sending " + myFile.getName()
						+ " to server");



				client.close();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}


	private static void writeFile(File file, OutputStream outStream) {
		FileInputStream reader = null;
		try {
			reader = new FileInputStream(file);
			byte[] buffer = new byte[CHUNK_SIZE];
			int pos = 0;
			int bytesRead;
			while ((bytesRead = reader.read(buffer, 0, CHUNK_SIZE)) >= 0) {
				outStream.write(buffer, 0, bytesRead);
				outStream.flush();
				pos += bytesRead;
				System.out.println(pos + " bytes (" + bytesRead + " bytes read)");
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error while reading file");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error while writing " + file.toString() + " to output stream");
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Main method that initialize one TCPServer for all the clients
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Thread t = new TCPServer ();
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}