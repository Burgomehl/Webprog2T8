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

@ServerEndpoint("/websocket/numericalchat")
public class WeboscketEndpoint {
	private static Set <Session> session = Collections.synchronizedSet(new HashSet<>());
	private String name;

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig,@PathParam("name") String name) {
		this.session.add(session);
		session.getAsyncRemote().sendText(name+ " connected to server");
		this.name = name;
//		session.getOpenSessions().add(session);
		System.out.println(session.getOpenSessions().size()+"");
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		this.session.remove(session);
		session.getAsyncRemote().sendText(name+ " disconnected from server "+closeReason);
	}
	
	@OnMessage
	public void onMessage(String message) {
		try{
			for(Session s : this.session){
				if(s.isOpen()){
					s.getAsyncRemote().sendText(message);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
