package com.zsk.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * ��Ϣ����
 * @author Administrator
 *
 */
public class MyServerHandler extends IoHandlerAdapter{

	//�������ӳ����쳣
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("exceptionCaught");
	}

	//�ر���
	@Override
	public void inputClosed(IoSession session) throws Exception {
		System.out.println("inputClosed");
	}

	//�յ���Ϣ
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String s = (String)message;
		System.out.println("messageReceived: "+s);
		session.write("server Reply: "+s);
	}

	//������Ϣ
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent");
	
	}

	//�ͻ��˺ͷ������ĻỰ�ر�
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
	}

	//�Ự����
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");
	}

	//�Ự����
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("sessionIdle");
	}
	
	//�Ự��
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
	}
	
}
