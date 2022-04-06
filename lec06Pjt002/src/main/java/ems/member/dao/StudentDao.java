package ems.member.dao;

import java.util.HashMap;
import java.util.Map;

import ems.member.Student;

// 실제로는 Dao 객체로 디비와 통신하지만
// 아직 안 배웠으므로 map 활용해서 구축 
public class StudentDao {

	// 모든 DB를 하나로 같이 쓴다 
	// 각 서비스들이 각각 Dao 객체를 사용했다면 = 다 다른 DB 쓰는 꼴 
	// 이렇게 하나의 map 사용 x 
	// 같은 객체 = 같은 DB가 되는 것 
	private Map<String, Student> studentDB = new HashMap<String, Student>();
	
	public void insert(Student student) {
		studentDB.put(student.getsNum(), student);
	}
	
	public Student select(String sNum) {
		return studentDB.get(sNum);
	}
	
	public void update(Student student) {
		studentDB.put(student.getsNum(), student);
	}
	
	public void delete(String sNum) {
		studentDB.remove(sNum);
	}
	
	public Map<String, Student> getStudentDB() {
		return studentDB;
	}
	
}
