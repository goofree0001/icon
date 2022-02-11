package com.example.demo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.example.demo.DemoEntity;

@Repository
public interface DemoRepository extends JpaRepository<DemoEntity, Integer>{

//    @Query("SELECT * FROM tourneys ORDER BY pkid")
//    List<DemoEntity> findAllOrderById();

}
