package com.zsk.server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MyTextLineDecoder implements ProtocolDecoder{

	@Override
	public void decode(IoSession seesion, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		int startPosition = in.position();
		//表示还有字节可以读取
		while(in.hasRemaining()) {
			byte b = in.get();
			if(b == '\n'){
				int currentPosition = in.position();
				//终点
				int limit = in.limit();
				in.position(startPosition);
				in.limit(currentPosition);
				//截取
				IoBuffer buf = in.slice();
				
				byte[] dest = new byte[buf.limit()];
				buf.get(dest);
				String str = new String(dest);
				out.write(str);
				
				//重置位置
				in.position(currentPosition);
				in.limit(limit);
			}
		}
		
	}

	@Override
	public void dispose(IoSession seesion) throws Exception {
		
	}

	@Override
	public void finishDecode(IoSession seesion, ProtocolDecoderOutput out)
			throws Exception {
		
	}

}
