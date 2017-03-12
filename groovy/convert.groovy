//println "Welcome to $language"
//String a = "hello"
//def map =  "$map"
//println a
//println map.getAt("java")
//return "The End"

def convert(orgin,tempData,resultData) {
	def returnparam = [:];
	def tempDataInit = [:];
	def stuidlist = [];
	def devname = orgin.get("DeviceTypeName");

	def studentlist = tempData.getAt("student");//第一次放进去的list
//	for(int i=0;i<selectlist.size;i++){
//		def teplist = selectlist[i];
//		for(int j=0;j<teplist.size;j++){
//			def xeguid = teplist[j].get("STUID");
//			stuidlist.add(xeguid);
//			println "123123"
//		}
//	}
	for(int i=0;i<studentlist.size;i++){
			def xeguid = studentlist[i].get("STUID");
			stuidlist.add(xeguid);
			println "123123"
	}
	tempDataInit.put("stuidlist",stuidlist);
	tempDataInit.put("devname",devname);
	returnparam.put("tempData",tempDataInit);
	return returnparam
}