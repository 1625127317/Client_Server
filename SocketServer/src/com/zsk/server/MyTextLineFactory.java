package com.zsk.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MyTextLineFactory implements ProtocolCodecFactory {
	
	private MyTextLineDecoder mDecoder;
	private MyTextLineEncoder mEncoder;
	private MyTextLineCumulativeDecoder mCumulativeDecoder;
	
	public MyTextLineFactory(){
		//mDecoder = new MyTextLineDecoder();
		mEncoder = new MyTextLineEncoder();
		mCumulativeDecoder = new MyTextLineCumulativeDecoder();
	}
	
	//½âÂë
	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return mCumulativeDecoder;
	}
	
	//±àÂë
	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return mEncoder;
	}

}
