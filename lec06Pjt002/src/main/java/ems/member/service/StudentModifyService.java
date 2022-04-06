package ems.member.service;

import ems.member.Student;
import ems.member.dao.StudentDao;

public class StudentModifyService {
	
	// 수정하려면 디비에 접근해야 됨 -> Dao
	// 모든 서비스에 Dao 있음 
	private StudentDao studentDao;
	
	public StudentModifyService(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	public void modify(Student student) {
		if(verify(student.getsNum())) {
			studentDao.update(student);
		} else {
			System.out.println("Student information is not available.");
		}
	}
	
	public boolean verify(String sNum){
		Student student = studentDao.select(sNum);
		return student != null ? true : false;
	}
}
