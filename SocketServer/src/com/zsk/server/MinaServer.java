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
			//Mina理念是将网络管理和消息处理分离
			NioSocketAcceptor acceptor = new NioSocketAcceptor(); 	
			acceptor.setHandler(new MyServerHandler());
			//拦截器是收发消息之前都会经过的，可以在这过程中进行对象转换，如下的读取文本数据，进行加解码，当传输的是json之类的消息时，没有内置的解码器，可自定义
			//acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineFactory()));
			//当客户端和服务器之间在一段时间之后没有通过信息，则进入“空闲状态”，执行sessionIdle方法,常用于检测客户端是否在线
			//acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 3);
			acceptor.bind(new InetSocketAddress(9898));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
