package com.zsk.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 消息处理
 * @author Administrator
 *
 */
public class MyServerHandler extends IoHandlerAdapter{

	//网络连接出现异常
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("exceptionCaught");
	}

	//关闭流
	@Override
	public void inputClosed(IoSession session) throws Exception {
		System.out.println("inputClosed");
	}

	//收到消息
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String s = (String)message;
		System.out.println("messageReceived: "+s);
		session.write("server Reply: "+s);
	}

	//发出消息
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent");
	
	}

	//客户端和服务器的会话关闭
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
	}

	//会话创建
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");
	}

	//会话空闲
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("sessionIdle");
	}
	
	//会话打开
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
	}
	
}
