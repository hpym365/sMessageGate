def convert(orgin,selectlist,result,temp) {
	def returnparam = [:];
	
	def namelist = [];
	def reslist = [];
	def devname = orgin.get("DeviceTypeName");

	def dyclist = selectlist[0];//取出来第二次要的
	def declist = selectlist[1];//取出来第二次要的
	for(int i=0;i<declist.size;i++){
			def res = [:];
			def name = declist[i].get("PATIENTNAME");
			def studyid = dyclist[i].get("STUID");
			res.put("name",name);
			res.put("devname",devname);
			res.put("studyid",studyid);
			reslist.add(res);
			println "123123"
	}
	returnparam.put("resultData",reslist);
	return returnparam
}