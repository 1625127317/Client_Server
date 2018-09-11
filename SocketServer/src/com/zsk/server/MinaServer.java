package com.zsk.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {

	public static void main(String[] args) {
		try {
			//Mina�����ǽ�����������Ϣ�������
			NioSocketAcceptor acceptor = new NioSocketAcceptor(); 	
			acceptor.setHandler(new MyServerHandler());
			//���������շ���Ϣ֮ǰ���ᾭ���ģ�������������н��ж���ת�������µĶ�ȡ�ı����ݣ����мӽ��룬���������json֮�����Ϣʱ��û�����õĽ����������Զ���
			//acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineFactory()));
			//���ͻ��˺ͷ�����֮����һ��ʱ��֮��û��ͨ����Ϣ������롰����״̬����ִ��sessionIdle����,�����ڼ��ͻ����Ƿ�����
			//acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 3);
			acceptor.bind(new InetSocketAddress(9898));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
