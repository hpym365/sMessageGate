xmlconfirm = "<ROOT><LOGIC><LOGICNAME>ConfirmRequisition</LOGICNAME></LOGIC><DATA><MESSAGE>testmessage</MESSAGE><PatientTypeID>2</PatientTypeID><Testlist><test>13579</test><test>24680</test></Testlist><DiagID>73193</DiagID><StudyID></StudyID><ExecutiveDoctor>admin</ExecutiveDoctor><SeparateTime>2016-08-25 17:41:36</SeparateTime><DeviceTypeName>DR</DeviceTypeName><DeviceName>DR1</DeviceName><PatientName>����סԺ����</PatientName><Sex>��</Sex><IDnumber></IDnumber><Phonenumber></Phonenumber><Address></Address><Folk></Folk><EmergencyFlag>0</EmergencyFlag><ReqDepartmentName>�ε���</ReqDepartmentName><ReqDoctor>����</ReqDoctor><Age>29 ��</Age><Birthday>1987-02-15 00:00:00</Birthday><ClinicDiagnose></ClinicDiagnose><SampleInfo></SampleInfo><StudyDepartmentID>721</StudyDepartmentID><PatientID>20160629152431</PatientID><AppHospital></AppHospital><OperatorID>admin</OperatorID><WorkingCompany></WorkingCompany><OtherPatientID></OtherPatientID><SocietyID></SocietyID><BackUpField1>jiahe</BackUpField1><BackUpField2></BackUpField2><BackUpField3></BackUpField3></DATA></ROOT>"

xmlcancle = "<ROOT><LOGIC><LOGICNAME>CancelRequisition</LOGICNAME></LOGIC><DATA><PatientTypeID>2</PatientTypeID><DiagID>27051</DiagID><StudyID>DR1651946</StudyID><ExecutiveDoctor>admin</ExecutiveDoctor><SeparateTime>2016-08-25 09:50:59</SeparateTime><DeviceTypeName>DR</DeviceTypeName><DeviceName>DR1</DeviceName><PatientName>�ܿ�</PatientName><Sex>��</Sex><IDnumber/><Phonenumber>18622314231</Phonenumber><Address/><Folk/><EmergencyFlag>0</EmergencyFlag><ReqDepartmentName>�ε���</ReqDepartmentName><ReqDoctor>����</ReqDoctor><Age>38 ��</Age><Birthday>1978-07-21</Birthday><ClinicDiagnose/><SampleInfo/><StudyDepartmentID>721</StudyDepartmentID><PatientID>20160623152721</PatientID><OperatorID>admin</OperatorID><WorkingCompany/><BackUpField1>jiahe</BackUpField1><OtherPatientID/><SocietyID/><eNetPatientID>6688</eNetPatientID></DATA></ROOT>"

xmlreport = "<ROOT><LOGIC><LOGICNAME>ReportVerified</LOGICNAME></LOGIC><DATA><DiagID>27051</DiagID><XEGUID>147A6989333B4726B4CC45F2EB3DBE8E</XEGUID><EmergencyFlag>0</EmergencyFlag><PatientTypeName>סԺ</PatientTypeName><BackUpField1>jiahe</BackUpField1><Description><![CDATA[asdfasdf]]></Description><Diagnose><![CDATA[fdasfdas]]></Diagnose><Advice><![CDATA[]]></Advice><DoctorName>����Ա</DoctorName><VerifyDoctor>����Ա</VerifyDoctor><DeviceType>DR</DeviceType><ReportTime>2016-08-25 14:08:46</ReportTime><RptDoctorID>admin</RptDoctorID><VerifyDoctorID>admin</VerifyDoctorID><PositiveFlag>1</PositiveFlag><DeviceName>DR1</DeviceName><StudyTime>2016-08-24 22:09:42</StudyTime><DangerCode></DangerCode><VerifyTime>2016-08-25 14:08:46</VerifyTime></DATA></ROOT>"

Set xmlhttp = CreateObject("MSXML2.XMLHTTP")
xmlhttp.Open "POST", "http://localhost:8080/query/zscs", False
xmlhttp.send xmlconfirm
Response = xmlHttp.responseText
MsgBox Response
