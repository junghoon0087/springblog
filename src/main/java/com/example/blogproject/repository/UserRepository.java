package com.example.blogproject.repository;

import com.example.blogproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//JSP->DAO
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //select * from user where username=? AND password=?;
    //JPA 네이밍 쿼리
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findByUsername(String userName);

//    @Query(value="select * from user where username=?1 AND password=?2", nativeQuery=true)
//    User login(String username, String password);
}
