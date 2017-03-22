function test(map){
	var data = map;
	var list = data.Testlist;
	var arr = [];
	for(var i=0;i<list.test.length;i++){
		var map = {};
		map.key = "student";
		map.sql="select * from student where name=?";
		param = [list.test[i]]
		map.param=param;
		arr[i]=map;
		
	}
//	data.DiagID
//	
//	arr[0]="select * from student where name='"+"13579"+"'";
//	arr[1]="select * from student where name='"+"24680"+"'";
	return arr;
}
