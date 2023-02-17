package ch19.practice7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductClient {
	private static Scanner scanner = new Scanner(System.in);
	private static Socket socket = null;
	private static DataInputStream dataInputStream = null;
	private static DataOutputStream dataOutputStream = null;
	
	public static void main(String[] args) {
		try {
			socket = new Socket("localhost", 50001);
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
			boolean isStop = false;
			while (true) {
				String choiceString = choice();
				
				switch (choiceString) {
					case "1": 
						create();
						break;
					case "2": 
						update();
						break;
					case "3" :
						delete();
						break;
					case "4" :
						isStop = true;
						break;
				}
				
				if (isStop) {
					break;
				}
			}
			
			shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static String choice() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("choice", "list");
		
		System.out.println(jsonObject.toString());
		
		try {
			dataOutputStream.writeUTF(jsonObject.toString());
			String data = dataInputStream.readUTF();
			JSONArray jsonArray = new JSONArray(data);
			
			System.out.println("[상품목록]");
			System.out.println("-------------------------------------------------");
			System.out.printf("%5s %5s %20s %10s \n",
					"no", "name", "price", "stock");
			System.out.println("-------------------------------------------------");
			// 반복...
			for (Object object : jsonArray) {
				JSONObject json = new JSONObject(object.toString());
				System.out.printf("%5d %5s %20d %10d \n", 
						json.getInt("no"), json.getString("name"), json.getInt("price"), json.getInt("stock"));
			}
			
			System.out.println("-------------------------------------------------");
			System.out.println("메뉴: 1. Create | 2. Update | 3. Delete | 4. Exit");
			System.out.print("선택: ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		return scanner.nextLine();
	}
	
	private static void create() {
		System.out.println("[상품 생성]");
		
		System.out.print("상품 이름: ");
		String name = scanner.nextLine();
		
		System.out.print("상품 가격: ");
		int price = Integer.parseInt(scanner.nextLine());
		
		System.out.print("상품 재고: ");
		int stock = Integer.parseInt(scanner.nextLine());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("choice", "Create");
		jsonObject.put("name", name);
		jsonObject.put("price", price);
		jsonObject.put("stock", stock);
		
		System.out.println(jsonObject.toString());
		
		try {
			dataOutputStream.writeUTF(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void update() {
		System.out.println("[상품 수정]");
		
		System.out.print("상품 번호: ");
		int no = Integer.parseInt(scanner.nextLine());
		
		System.out.print("상품 이름: ");
		String name = scanner.nextLine();
		
		System.out.print("상품 가격: ");
		int price = Integer.parseInt(scanner.nextLine());
		
		System.out.print("상품 재고: ");
		int stock = Integer.parseInt(scanner.nextLine());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("choice", "Update");
		jsonObject.put("no", no);
		jsonObject.put("name", name);
		jsonObject.put("price", price);
		jsonObject.put("stock", stock);
		
		System.out.println(jsonObject.toString());
		
		try {
			dataOutputStream.writeUTF(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void delete() {
		System.out.println("[상품 삭제]");
		
		System.out.print("상품 번호: ");
		int no = Integer.parseInt(scanner.nextLine());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("choice", "Delete");
		jsonObject.put("no", no);
		
		System.out.println(jsonObject.toString());
		
		try {
			dataOutputStream.writeUTF(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void shutdown() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("choice", "shutdown");
		try {
			dataOutputStream.writeUTF(jsonObject.toString());
			scanner.close();
			dataInputStream.close();
			dataOutputStream.close();
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
