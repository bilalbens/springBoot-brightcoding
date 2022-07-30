package com.bbenslimane.app.ws.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bbenslimane.app.ws.entities.UserEntity;


@Repository

//public interface UserRepository extends CrudRepository<UserEntity, Long> {
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId);

}
