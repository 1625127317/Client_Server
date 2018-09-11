package com.zsk.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		SocketServer socketServer = new SocketServer();
		socketServer.startServer();
	}
	
	private void startServer(){
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9898);
			System.out.println("服务已开启...");
			while(true){
				//当没有Socket连接时，阻塞
				socket = serverSocket.accept();
				//管理连接
				manageConnection(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void manageConnection(final Socket socket){
		new Thread(new Runnable() {
			@Override
			public void run() {
				BufferedReader reader = null;
				BufferedWriter writer = null;
				try {
					System.out.println("client:"+socket.hashCode()+"已连接");
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					String receivedMsg;
					while((receivedMsg = reader.readLine()) != null) {
						System.out.println("client"+socket.hashCode()+receivedMsg);
						writer.write("server reply: " + receivedMsg + "\n");
						writer.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					try {
						reader.close();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}	
		}).start();
	}
}
