package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class clint {
	private static Socket socket;

	public static void main(String[] args) throws InterruptedException {
		try {
			String first_name = "omar zaarir";
			String secound_name = "yahya alawi";
			Scanner scanner=new Scanner(System.in);
			socket = new Socket("", 8855);
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.println(first_name + " : "+ scanner.nextLine());
			printWriter.flush();
			InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String string = bufferedReader.readLine();
			System.out.println( string);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
