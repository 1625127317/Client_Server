package com.zsk.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args) {
		SocketClient client = new SocketClient();
		client.start();
	}

	private void start() {
		BufferedReader inputReader = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		Socket socket = null;

		try {
			// 连接目标
			socket = new Socket("192.168.1.101", 9898);
			// 读取socket的内容
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// 写入socket的内容
			writer = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));

			// 获取控制台输入
			inputReader = new BufferedReader(new InputStreamReader(System.in));

			// 对服务器的监听，当收到来自服务器的内容
			startServerReplyListener(reader);

			// 控制台输入
			String inputContent;
			int count = 0;
			while (!(inputContent = inputReader.readLine()).equals("bye")) {
				writer.write(inputContent);
				if (count % 2 == 0) {
					writer.write("\n");
				}
				count++;
				writer.flush();
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
				inputReader.close();
				socket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void startServerReplyListener(final BufferedReader reader) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String response;
					while ((response = reader.readLine()) != null) {
						System.out.println(response);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

	}
}
