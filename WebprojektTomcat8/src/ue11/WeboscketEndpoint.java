package ue11;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.PathParam;

@ServerEndpoint("/websocket/numericalchat/{name}")
public class WeboscketEndpoint {
	private Session session;
	private String name;

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig,@PathParam("name") String name) {
		this.session = session;
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
		session.getAsyncRemote().sendText(name+ " disconnected from server "+closeReason);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		try{
			for(Session s : session.getOpenSessions()){
				if(s.isOpen()){
					s.getAsyncRemote().sendText(message);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
}
