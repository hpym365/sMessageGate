//println "Welcome to $language"
//String a = "hello"
//def map =  "$map"
//println a
//println map.getAt("java")
//return "The End"

def hello(param1) {
	def map = param1
	
	def sql = 'select * from student where name='+map.get('DiagID')
	return sql
}