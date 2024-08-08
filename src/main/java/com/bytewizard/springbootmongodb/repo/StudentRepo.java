package com.bytewizard.springbootmongodb.repo;

import com.bytewizard.springbootmongodb.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student,Integer> {

}
