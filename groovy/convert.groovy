//println "Welcome to $language"
//String a = "hello"
//def map =  "$map"
//println a
//println map.getAt("java")
//return "The End"

def convert(orgin,selectlist,result,temp) {
	def returnparam = [:];
	def tempData = [:];
	def stuidlist = [];
	def devname = orgin.get("DeviceTypeName");

	def dyclist = selectlist[0];//第一次放进去的list
//	for(int i=0;i<selectlist.size;i++){
//		def teplist = selectlist[i];
//		for(int j=0;j<teplist.size;j++){
//			def xeguid = teplist[j].get("STUID");
//			stuidlist.add(xeguid);
//			println "123123"
//		}
//	}
	for(int i=0;i<dyclist.size;i++){
			def xeguid = dyclist[i].get("STUID");
			stuidlist.add(xeguid);
			println "123123"
	}
	tempData.put("stuidlist",stuidlist);
	tempData.put("devname",devname);
	returnparam.put("tempData",tempData);
	return returnparam
}