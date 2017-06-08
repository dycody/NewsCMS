var confirmObj = {		
		fn : function(){},
		args : null,
		notFn : function(){},
		notArgs : null
};

$("#systemConfirm").on('hidden.bs.modal', function (e) {
	$("#systemConfirm").find("#systemConfirmBody").html("");
	confirmObj = {		
			fn : function(){},
			args : null,
			notFn : function(){},
			notArgs : null
	};
	$("body").css("padding-right","");//去除body因多个模态框连续弹出造成的padding-right
});

function doSystemConfirm(){
	$("#systemConfirm").modal("hide");
	$("#systemConfirm").find("#systemConfirmBody").html("");
	if(confirmObj.fn){		
		confirmObj.fn(confirmObj.args);
	}
};

function cancelSystemConfirm(){
	$("#systemConfirm").modal("hide");
	$("#systemConfirm").find("#systemConfirmBody").html("");
	if(confirmObj.notFn){
		confirmObj.notFn(confirmObj.notArgs);
	}
};

function systemConfirm(msg,fn,args,notFn,notArgs) {
	if(!msg){
		msg = "";
	}
    $("#systemConfirm").modal("show").find("#systemConfirmBody").html("&nbsp;&nbsp;"+msg);
    confirmObj = {		
			fn : fn,
			args : args,
			notFn : notFn,
			notArgs : notArgs
	};
};

//当提示模态框关闭时检查是否有其他模态框打开，有则添加open class
$('.modal').on('hidden.bs.modal', function() {
    if ($('.modal:visible').length) {
        $('body').addClass('modal-open');
    }
});

$("#systemAlert").on('hidden.bs.modal', function (e) {
	$("#systemAlert").find("#systemAlertBody").html("");
	$("#systemAlert").find("#specialAlertBody").html("");
	$("body").css("padding-right","");//去除body因多个模态框连续弹出造成的padding-right
});

function systemAlert(alertbody){
	$("#systemAlert").modal('show');
	$("#systemAlertBody").show();
	$("#specialAlertBody").hide();
	$("#systemAlertBody").html(alertbody?alertbody:"您的会话可能已经过期，请刷新页面重试！");
};

function specialSystemAlert(specialBody){
	$("#systemAlert").modal('show');
	$("#specialAlertBody").show();
	$("#systemAlertBody").hide();
	$("#specialAlertBody").html(specialBody);
};

function resetValue(params){
	for(v in params){
		var ele = params[v];
		if(ele instanceof Object){
			resetValue(ele);
		}else{
			if($("input[name='"+v+"']").length>0){
				$("input[name='"+v+"']").val(ele);
			}else if($("select[name='"+v+"']").length>0){
				$("select[name='"+v+"']").val(ele);
			}
		}
	}
};

function dateTimeToStr(dt) {
	if(!dt){
		return "";
	}
    var dd = dt.getDate()<10?"0"+dt.getDate():dt.getDate()+"";  
    var m = dt.getMonth()+1;
    var mm = m<10?"0"+m:m+"";
    var yyyy = dt.getFullYear()+""; 
    
    var hour = dt.getHours()<10?"0"+dt.getHours():dt.getHours()+"";
    var mi = dt.getMinutes()<10?"0"+dt.getMinutes():dt.getMinutes()+"";
    var se = dt.getSeconds()<10?"0"+dt.getSeconds():dt.getSeconds()+"";
    
    return yyyy+"-"+mm+"-"+dd+" "+hour+":"+mi+":"+se;  
};

function dateToStr(dt) {
	if(!dt){
		return "";
	}
    var dd = dt.getDate()<10?"0"+dt.getDate():dt.getDate()+"";  
    var m = dt.getMonth()+1;
    var mm = m<10?"0"+m:m+"";
    var yyyy = dt.getFullYear()+"";
    return yyyy+"-"+mm+"-"+dd; 
};

function nDateTimeToStr(ori,n) {
	if(!ori){
		return "";
	}
	var dt = new Date(ori.getTime()+n*86400000);
	var dd = dt.getDate()<10?"0"+dt.getDate():dt.getDate()+"";
	var m = dt.getMonth()+1;
    var mm = m<10?"0"+m:m+"";
    var yyyy = dt.getFullYear()+"";
    
    var hour = dt.getHours()<10?"0"+dt.getHours():dt.getHours()+"";
    var mi = dt.getMinutes()<10?"0"+dt.getMinutes():dt.getMinutes()+"";
    var se = dt.getSeconds()<10?"0"+dt.getSeconds():dt.getSeconds()+"";
    
    return yyyy+"-"+mm+"-"+dd+" "+hour+":"+mi+":"+se;  
};

function nDateToStr(ori,n) {
	if(!ori){
		return "";
	}
	var dt = new Date(ori.getTime()+n*86400000);
    var dd = dt.getDate()<10?"0"+dt.getDate():dt.getDate()+"";  
    var m = dt.getMonth()+1;
    var mm = m<10?"0"+m:m+"";
    var yyyy = dt.getFullYear()+"";
    return yyyy+"-"+mm+"-"+dd;  
};

//截取字符串根据长度
function customSubString(str, len){
	var strlen = 0;
	var substr = "";
	for(var i = 0;i< str.length; i++){
		if(str.charCodeAt(i) > 255 || str.charCodeAt(i) < 0){
			strlen += 2;
		}
		else{
			strlen++;
		}
		substr += str.charAt(i);
		if(strlen >= len){
			
			return substr + '...';
		}
	}
	return substr;
};

//计算字符串长度 包含中文
function getStrLen(str){
	var len = 0;
	for(var i = 0; i < str.length; i++){
		if(str.charCodeAt(i) > 255 || str.charCodeAt(i) < 0){
			len += 2;
		}
		else{
			len++;
		}
	}
	return len;
};

function excuteWhenEnter(element, fun){
	element.bind('keyup', function(event) {
		if (event.keyCode == "13") {
			//回车执行查询
			fun();
		}
	});
};









var stompClient = null;  

function connect() {  
    var socket = new SockJS('/pst-web/actions/socketServer');  
    stompClient = Stomp.over(socket);              
    stompClient.connect({}, function(frame) { 
        
    	console.log('Connected: ' + frame);
        
        stompClient.subscribe('/topic/greeting', function(greeting){  
        	console.log(JSON.parse(greeting.body));  
        },{"Content-Type":"application/json"});
        
        stompClient.subscribe('/queue/'+curUserAccount, function(greeting){  
        	console.log(JSON.parse(greeting.body));  
        },{"Content-Type":"application/json"});
        
    });
}  
  
function disconnect() {  
    if (stompClient != null) {  
        stompClient.disconnect();  
        console.log("Disconnected");  
    }  
}  
  
function sendName(name) {    
	//JSON.stringify({"name":name})
    stompClient.send("/app/topic", {'Content-Type' : 'application/json'}, JSON.stringify({"constantId":122,"name":name}));
    stompClient.send("/app/queue", {'Content-Type' : 'application/json'}, JSON.stringify({"constantId":133,"name":name}));
}

window.onbeforeunload = function () {  
	disconnect();
};

/*connect();
setTimeout(function(){
    sendName("送哥哥色哥个");
},3000);*/
