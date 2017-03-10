function test(map){
	var data = map;
	var list = data.Testlist;
	var arr = [];
	for(var i=0;i<list.test.length;i++){
		arr[i]="select * from student where name='"+list.test[i]+"'";
	}
//	data.DiagID
//	
//	arr[0]="select * from student where name='"+"13579"+"'";
//	arr[1]="select * from student where name='"+"24680"+"'";
	return arr;
}
