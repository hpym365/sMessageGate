function test(orgin,selectlist,resultdata,tempdata){
	
	var devname = tempdata.devname;
	var arr=[];
	var stuidlist = tempdata.stuidlist;
	for(var i=0;i<stuidlist.length;i++){
		arr[i]="select * from studyinfo where studyid='"+stuidlist[i]+"'";
	}
//	data.DiagID
//	
//	arr[0]="select * from student where name='"+"13579"+"'";
//	arr[1]="select * from student where name='"+"24680"+"'";
	return arr;
}
