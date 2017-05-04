package com.senyint.test.httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.senyint.component.HttpUtils;

public class Test {

	public void sendRequest() throws ClientProtocolException, IOException{
		
		String url = "http://localhost:8888/query/zscs";
		
		String msg = "<ROOT><LOGIC><LOGICNAME>ConfirmRequisition</LOGICNAME></LOGIC><DATA><MESSAGE>testmessage</MESSAGE><PatientTypeID>2</PatientTypeID><Testlist><test>13579</test><test>24680</test></Testlist><DiagID>73193</DiagID><StudyID></StudyID><ExecutiveDoctor>admin</ExecutiveDoctor><SeparateTime>2016-08-25 17:41:36</SeparateTime><DeviceTypeName>DR</DeviceTypeName><DeviceName>DR1</DeviceName><PatientName>患者姓名</PatientName><Sex>男</Sex><IDnumber></IDnumber><Phonenumber></Phonenumber><Address></Address><Folk></Folk><EmergencyFlag>0</EmergencyFlag><ReqDepartmentName>部门名称</ReqDepartmentName><ReqDoctor>医生名称</ReqDoctor><Age>29岁</Age><Birthday>1987-02-15 00:00:00</Birthday><ClinicDiagnose></ClinicDiagnose><SampleInfo></SampleInfo><StudyDepartmentID>721</StudyDepartmentID><PatientID>20160629152431</PatientID><AppHospital></AppHospital><OperatorID>admin</OperatorID><WorkingCompany></WorkingCompany><OtherPatientID></OtherPatientID><SocietyID></SocietyID><BackUpField1>jiahe</BackUpField1><BackUpField2></BackUpField2><BackUpField3></BackUpField3></DATA></ROOT>";

		String result = HttpUtils.sendPost(url, msg);
        System.out.println(result);
		
	}
	
	public static void main(String [] args) throws Exception{
		new Test().sendRequest();
	}
}
