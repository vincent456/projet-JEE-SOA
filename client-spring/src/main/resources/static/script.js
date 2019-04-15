var lto;

function logerror(jqXHR,textStatus,errorThrown){
	console.log("textStatus="+textStatus);
	console.log("errorThrown="+errorThrown);
}

function submit() {
	server = $("#server")[0].value;
	content = $("#input")[0].value;
	$("#input")[0].value="";
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
	data = JSON.parse(data);
	console.log(data);
	if(lto === undefined){
		$("#textbox")[0].innerHTML="";
	}
	
	for(item of data){
		$("#textbox")[0].innerHTML +="<div>"+item.message+"</div>";
	}
	
	lto = new Date();
}



function fetch() {
	
	date = lto;
	data="";
	if(date != null){
		try{
			data = dateFormat(date,"dd-mmm-yyyy-HH-MM-ss-l");
		}
		catch(error){
	
		}
	}
	var urlrequest;
	if(date!=null){
		urlrequest = server+"/get?timestamp="+data;
	}
	else{
		urlrequest = server+"/get";
	}
	$.ajax({
		type:"GET",
		crossDomain:true,
		url:server+"/get?timestamp="+data,
		dataType:"text",
		success:display,
		error:logerror
	});
}

