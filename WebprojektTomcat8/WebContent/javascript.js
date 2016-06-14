var target = document.querySelector("#uri").value;
var ws = new WebSocket(target);
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