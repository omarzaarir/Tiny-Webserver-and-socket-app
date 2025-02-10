package network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demoserver {
	static int port = 9977;

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("server is running on port :" + port);
		while (true) {
			Socket clientSocket = serverSocket.accept();

			System.err.println("Client connection \n");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String request = bufferedReader.readLine();
			System.out.println(request);

			String[] requestparts = request.split(" ");
			String req = requestparts[1];

			OutputStream clintoutput = clientSocket.getOutputStream();
			PrintWriter writer = new PrintWriter(clintoutput, true);

			if (req.equalsIgnoreCase("/") || req.equalsIgnoreCase("/index.html")) {
				try {
					File file = new File("index.html");
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fileInputStream.read(data);
					fileInputStream.close();

					writer.println("HTTP/1.1 200 OK");
					writer.println("Content-Type: text/html");
					writer.println("Content-Length: " + data.length);
					writer.println();
					clintoutput.write(data);
				} catch (IOException e) {
					sendErrorPage(writer, clintoutput);
					System.err.println("index file not found!!!!");
				}
			}
			if (req.equalsIgnoreCase("/cv") || req.equalsIgnoreCase("/cv.html")) {
				try {
					File file = new File("cv.html");
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fileInputStream.read(data);
					fileInputStream.close();

					writer.println("HTTP/1.1 200 OK");
					writer.println("Content-Type: text/html");
					writer.println("Content-Length: " + data.length);
					writer.println();
					clintoutput.write(data);
				} catch (IOException e) {
					sendErrorPage(writer, clintoutput);
					System.err.println("index file not found!!!!");
				}
			} else if (req.equalsIgnoreCase("/en") || req.equalsIgnoreCase("/main_en.html")) {
				try {
					File file = new File("main_en.html");
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fileInputStream.read(data);
					fileInputStream.close();

					writer.println("HTTP/1.1 200 OK");
					writer.println("Content-Type: text/html");
					writer.println("Content-Length: " + data.length);
					writer.println();
					clintoutput.write(data);
				} catch (IOException e) {
					sendErrorPage(writer, clintoutput);
					System.err.println("main file not found!!!!");
				}
			} else if (req.equalsIgnoreCase("/ar") || req.equalsIgnoreCase("/main_ar.html")) {
				try {
					File file = new File("main_ar.html");
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fileInputStream.read(data);
					fileInputStream.close();

					writer.println("HTTP/1.1 200 OK");
					writer.println("Content-Type: text/html; charset=UTF-8");
					writer.println("Content-Length: " + data.length);
					writer.println();
					clintoutput.write(data);
				} catch (IOException e) {
					sendErrorPage(writer, clintoutput);
					System.err.println("main file not found!!!!");
				}
			} else if (req.contains(".jpg")) {
				try {
					File file = new File(req.substring(1));
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fileInputStream.read(data);
					fileInputStream.close();

					writer.println("HTTP/1.1 200 OK");
					writer.println("Content-Type: image/jpeg");
					writer.println("Content-Length: " + data.length);
					writer.println();
					clintoutput.write(data);
				} catch (IOException e) {
					sendErrorPage(writer, clintoutput);
					System.err.println("jpg image file not found!!!! \n" + e.getMessage());
				}

			} else if (req.contains(".png")) {
				try {
					File file = new File(req.substring(1));
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fileInputStream.read(data);
					fileInputStream.close();

					writer.println("HTTP/1.1 200 OK");
					writer.println("Content-Type: image/png");
					writer.println("Content-Length: " + data.length);
					writer.println();
					clintoutput.write(data);
				} catch (IOException e) {
					sendErrorPage(writer, clintoutput);
					System.err.println("png image file not found!!!! \n" + e.getMessage());
				}
			} else if (req.contains(".css")) {
				try {
					File file = new File(req.substring(1));
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fileInputStream.read(data);
					fileInputStream.close();

					writer.println("HTTP/1.1 200 OK");
					writer.println("Content-Type: text/css");
					writer.println("Content-Length: " + data.length);
					writer.println();
					clintoutput.write(data);
				} catch (IOException e) {
					sendErrorPage(writer, clintoutput);
					System.err.println("css file not found!!!! \n" + e.getMessage());
				}
			} else if (req.equalsIgnoreCase("/yt")) {
				writer.println("HTTP/1.1 307 Moved Permanently");
				writer.println("Location: https://www.youtube.com");
				writer.println();
			} else if (req.equalsIgnoreCase("/so")) {
				writer.println("HTTP/1.1 307 Moved Permanently");
				writer.println("Location: https://www.stackoverflow.com");
				writer.println();
			} else if (req.equalsIgnoreCase("/rt")) {

				writer.println("HTTP/1.1 307 Moved Permanently");
				writer.println("Location: https://ritaj.birzeit.edu/");
				writer.println();
//			clintoutput.write(data);

			}

			bufferedReader.close();
			clintoutput.close();

		}

	}

	private static void sendErrorPage(PrintWriter writer, OutputStream outputStream) {
		writer.println("HTTP/1.1 404 Not Found");
		writer.println("Content-Type: text/html");
		writer.println();
		writer.println("<h1 style='color:red;  text-align: center;'>this File is Not Found</h1>");
		writer.println("<div style =' text-align: center;'>omar zaarir:1172754 </br> yahya alawi:1150361 </br>");
		try {
			writer.println("</br>Your IP: <b>" + InetAddress.getLocalHost().getHostAddress() + "</b> on port :<b>"
					+ port + "</b></div>");
		} catch (UnknownHostException e) {
			writer.println("Your IP: Unknown");
		}

		writer.flush();
	}

}
