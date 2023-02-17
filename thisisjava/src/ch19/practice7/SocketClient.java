package ch19.practice7;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

public class SocketClient {
	private Product[] products = new Product[100];
	private DataInputStream dataInputStream = null;
	private DataOutputStream dataOutputStream = null;
	
	public SocketClient(ProductServer productServer, Socket socket) {
		try {
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		boolean isStop = false;
		
		while (true) {
			try {
				String inputString = dataInputStream.readUTF();
				JSONObject jsonObject = new JSONObject(inputString);
				String choice = jsonObject.getString("choice");
				
				switch (choice) {
					case "list" :
						JSONArray jsonArray = new JSONArray();
						for (int i = 0; i < products.length; i++) {
							if (products[i] == null) {
								continue;
							}
							int no = products[i].getNo();
							String name = products[i].getName();
							int price = products[i].getPrice();
							int stock = products[i].getStock();
							
							JSONObject json = new JSONObject();
							json.put("no", no);
							json.put("name", name);
							json.put("price", price);
							json.put("stock", stock);
							
							jsonArray.put(json);
						}
						
						dataOutputStream.writeUTF(jsonArray.toString());
						break;
					case "Create":
						create(jsonObject);
						break;
					case "Update" :
						update(jsonObject);
						break;
						
					case "Delete" :
						delete(jsonObject);
						break;
					case "shutdown" :
						isStop = true;
						break;
				}
				
				if (isStop) {
					System.out.println("[Socket] user 로그아웃");
					break;
				}
				
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	}
	
	private void create(JSONObject jsonObject) {
		int no = 0;
		String name = jsonObject.getString("name");
		int price = jsonObject.getInt("price");
		int stock = jsonObject.getInt("stock");
		
		Product product = null;
		for (int i = 0; i < products.length; i++) {
			if (products[i] == null) {
				no = i;
				product = new Product(no, name, price, stock);
				break;
			}
		}
		products[no]= product;
	}
	
	private void update(JSONObject jsonObject) {
		int no = jsonObject.getInt("no");
		String name = jsonObject.getString("name");
		int price = jsonObject.getInt("price");
		int stock = jsonObject.getInt("stock");
		
		Product product = new Product(no, name, price, stock);
		products[no]= product;
	}
	
	private void delete(JSONObject jsonObject) {
		int no = jsonObject.getInt("no");
		
		products[no] = null;
	}
}