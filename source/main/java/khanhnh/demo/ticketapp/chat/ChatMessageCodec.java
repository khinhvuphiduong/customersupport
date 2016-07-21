package khanhnh.demo.ticketapp.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;

public class ChatMessageCodec implements 
						Encoder.BinaryStream<ChatMessage>, 
						Decoder.BinaryStream<ChatMessage> {

	private static final ObjectMapper MAPPER =new ObjectMapper();
	static{
		MAPPER.findAndRegisterModules();
		MAPPER.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChatMessage decode(InputStream inputStream) throws DecodeException, IOException {
		// TODO Auto-generated method stub
		try{
			return ChatMessageCodec.MAPPER.readValue(inputStream, ChatMessage.class);
		}
		catch(JsonParseException | JsonMappingException e){
			throw new DecodeException((ByteBuffer)null, e.getMessage(),e);
		}
	}

	@Override
	public void encode(ChatMessage chatMessage, OutputStream outputStream) throws EncodeException, IOException {
		// TODO Auto-generated method stub
		try{
			ChatMessageCodec.MAPPER.writeValue(outputStream, chatMessage);
		}
		catch(JsonGenerationException | JsonMappingException e){
			throw new EncodeException((ByteBuffer)null, e.getMessage(), e);
		}
	}

}
