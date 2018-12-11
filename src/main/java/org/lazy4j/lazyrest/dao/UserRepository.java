package org.lazy4j.lazyrest.dao;

import org.lazy4j.lazyrest.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：Melon
 * @Date：2017/10/19
 * @Time：下午10:33
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity>,
        Serializable {

     String FIND_USERNAME = "select DISTINCT username from UserEntity u";

     @Query(FIND_USERNAME)
     List<UserEntity> findUsername();


     Page<UserEntity> findAll(Pageable pageable);


     UserEntity findByUsername(String username);

     Page<UserEntity> findByUsername(String username, Pageable pageable);


}
