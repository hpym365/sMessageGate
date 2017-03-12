def convert(orgin,tempData,resultData) {
	def returnparam = [:];
	
	def namelist = [];
	def reslist = [];
	def devname = orgin.get("DeviceTypeName");

	def dyclist = tempData.getAt("student");//取出来第1查询的
	def declist = tempData.getAt("studyinfo");//取出来第2查询的
	for(int i=0;i<declist.size;i++){
			def res = [:];
			def name = declist[i].get("PATIENTNAME");
			def studyid = declist[i].get("STUDYID");
			def xeguid = declist[i].get("XEGUID");
			def age = null;
			for(int j=0;j<dyclist.size;j++){
				if(dyclist[i].get("STUID")==studyid){
					age = dyclist[i].get("AGE");
				}
			}
			
			res.put("name",name);
			res.put("age",age);
			res.put("devname",devname);
			res.put("xeguid",xeguid);
			res.put("studyid",studyid);
			reslist.add(res);
			println "123123"
	}
	returnparam.put("resultData",reslist);
	return returnparam
}