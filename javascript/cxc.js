function test(orgin,tempdata,resultdata){
	
	var devname = tempdata.devname;
	var arr=[];
	var studentlist = tempdata.student;
	for(var i=0;i<studentlist.length;i++){
		var map = {};
		map.key="studyinfo";
		map.sql="select * from studyinfo where studyid='"+studentlist[i].get("STUID")+"'";
		arr[i]=map;
	}
//	data.DiagID
//	
//	arr[0]="select * from student where name='"+"13579"+"'";
//	arr[1]="select * from student where name='"+"24680"+"'";
	return arr;
}
