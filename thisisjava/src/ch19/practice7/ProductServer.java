package ch19.practice7;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductServer {
	private static ServerSocket serverSocket = null;
	private static ExecutorService executorService = Executors.newFixedThreadPool(100);
	
	public static void main(String[] args) {
		ProductServer productServer = new ProductServer();
		
		System.out.println("----------------------------------------");
		System.out.println("서버를 종료하려면 q를 입력하고 Enter 키를 입력하세요.");
		System.out.println("----------------------------------------");
		
		productServer.startServer();
		
		productServer.serverUI();
	}
	
	private void startServer() {
		
		Thread thread = new Thread(() -> {
			try {
				serverSocket = new ServerSocket(50001);
				System.out.println("[서버] 시작됨");
				
				while (true) {
					System.out.println("[서버] 접속 대기 중");
					Socket socket = serverSocket.accept();
					
					executorService.execute(() -> {
						InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
						System.out.println("[서버] " + socketAddress.getHostName() + "의 연결 요청을 수락함");
						
						SocketClient socketClient = new SocketClient(this, socket);
						socketClient.run();
					});
				}
			} catch (IOException e) {
				System.out.println("[서버] 종료");
			}
		});
		
		thread.start();
	}
	
	private void serverUI() {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			String input = scanner.nextLine();
			if (input.toLowerCase().equals("q")) {
				break;
			}
		}
		
		scanner.close();
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
