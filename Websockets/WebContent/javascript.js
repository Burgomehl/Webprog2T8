var ws = new WebSocket("ws://localhost:8080/Websockets/websocket/numericalchat");
ws.onopen = function(){
	
}
ws.onmessage = function(){
	
}
ws.onclose = function(){
	
}
function generateRandomNumber(){
	var x = Math.round(Math.random() * 100);
	ws.send(x);
}