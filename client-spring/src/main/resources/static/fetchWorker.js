self.importScripts("preJQuery.js");
self.importScripts("https://code.jquery.com/jquery-3.3.1.js");

function logerror(jqXHR,textStatus,errorThrown){
	console.log("textStatus="+textStatus);
	console.log("errorThrown="+errorThrown);
}

function display(data,textStatus,jqXHR){
	
}

function fetch() {
	
	date = new Date();
	data = date;
	$.ajax({
		type:"GET",
		crossDomain:true,
		url:server+"/get",
		data:data,
		dataType:"json",
		success:display,
		error:logerror
	});
}

while (true) {
	fetch();
}