package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class server {

	public static void main(String[] args) throws InterruptedException {
		String clints[] = new String[10];
		Date[] dates = new Date[10];
		String mass[] = new String[10];
		int i = 0;
		try {
			ServerSocket serverSocket = new ServerSocket(8855);
			Scanner scanner = new Scanner(System.in);
			System.out.println("yahya alwai server");
			while (true) {
				if (i == 9) {
					System.out.println("buffer is full");
					break;
				}
				Socket socket = serverSocket.accept();
				clints[i] = socket.getInetAddress().getHostName();
				dates[i] = new Date();
				InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String string = bufferedReader.readLine();
				mass[i] = string;
				System.out.println(string);
				String cl = scanner.nextLine();
				if (cl.equals("all")) {
					for (int j = i; j >=0; j--) {
						System.out.println(
								j+1 + ":- " + clints[j] + ": " + mass[j] + " - at - " + dates[j].toString() + "\n");
					}
					PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
					printWriter.println("closed conversation by yahya alawi!");
					printWriter.flush();
					break;
				}
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
				printWriter.println("yahya alwi : " + cl);
				printWriter.flush();
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
