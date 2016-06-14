package ue11;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.PathParam;

@ServerEndpoint(value="/websocket/numericalchat", encoders = {ChatMessageEncoder.class}, decoders = {ChatMessageDecoder.class})
public class WeboscketEndpoint {
	private static Set <Session> session = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		this.session.add(session);
//		session.getOpenSessions().add(session);
//		System.out.println(session.getOpenSessions().size()+"");
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		this.session.remove(session);
//		session.getAsyncRemote().sendText( " disconnected from server "+closeReason);
	}
	
	@OnMessage
	public void onMessage(String message) {
		try{
			for(Session s : session){
				if(s.isOpen()){
//					s.getAsyncRemote().sendText(message);
					s.getBasicRemote().sendText(message);
					System.out.println(message);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
