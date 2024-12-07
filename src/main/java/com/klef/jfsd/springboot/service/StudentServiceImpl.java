package com.klef.jfsd.springboot.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.StudentRepository;

@Service

     public class StudentServiceImpl implements StudentService
  {
    @Autowired
    private StudentRepository repository;
    
    
    @Override
    public String addstudent(Student s) {
      repository.save(s);
      return "Student Added Successfully";
    }

    @Override
    public String updatestudent(Student s) 
    {
        
    Optional<Student> object = repository.findById(s.getId());
    
    String msg = null;
    
    if(object.isPresent())
    {
      Student student = object.get();
      student.setAge(((Student) s).getAge());
      student.setContact(s.getContact());
      student.setDepartment(((Student) s).getDepartment());
      student.setEmail(s.getEmail());
      student.setGender(s.getGender());
      student.setName(s.getName());
      
      ((CrudRepository<Student, Integer>) repository).save(student);
      msg = "Student Updated Successfully";
    }
    else
    {
      msg = "Student ID not found";
    }
      return msg;
    }

    public String deletestudent(int sid)
    {
      Optional<Student> object = ((CrudRepository<Student, Integer>) repository).findById(sid);
      
      String msg = null;
      
      if(object.isPresent())
      {
        Student s = object.get();
        repository.delete(s);
        msg = "Student Deleted Successfully";
      }
      else
      {
        msg = "Student ID not found";
      }
      return msg;
    }

    public Student viewstudentbyid(int sid) 
    {
      return ((CrudRepository<Student, Integer>) repository).findById(sid).get();
    }

    @Override
    public List<Student> viewallstudents() {
      return (List<Student>) ((CrudRepository<Student, Integer>) repository).findAll();
    }

  }