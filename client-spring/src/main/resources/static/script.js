function logerror(jqXHR,textStatus,errorThrown){
	console.log("textStatus="+textStatus);
	console.log("errorThrown="+errorThrown);
}

function submit() {
	server = $("#server")[0].value;
	content = $("#input")[0].value;
	
	date = new Date();
	
	timestamp = dateFormat(date,"dd-mmm-yyyy-HH-MM-ss-l")
	
	data ={message:content,timestamp:timestamp};
	
	$.ajax({
		type:"POST",
		crossDomain:true,
		url:server+"/post",
		data:data,
		dataType:"json",
		success:null,
		error:logerror
	});
	
}

//worker = new Worker("fetchWorker.js");

function display(data,textStatus,jqXHR){
	console.log(data);
}

function fetch() {
	
	date = new Date();
	data = dateFormat(date,"dd-mmm-yyyy-HH-MM-ss-l");
	$.ajax({
		type:"GET",
		crossDomain:true,
		url:server+"/get?timestamp="+data,
		dataType:"text",
		success:display,
		error:logerror
	});
}