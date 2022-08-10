package com.bbenslimane.app.ws.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bbenslimane.app.ws.entities.UserEntity;


@Repository

//public interface UserRepository extends CrudRepository<UserEntity, Long> {
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	
	UserEntity findByUserId(String userId);


//	native SQL
//	@Query(value="select * from users", nativeQuery=true)
//	Page<UserEntity> findAllUsers(Pageable pegeableRequest);


//	JPQL
	@Query(value="select user from UserEntity user ")
	Page<UserEntity> findAllUsers(Pageable pegeableRequest);




//	  Executer des Requetes SQL avec Positional Parameters
//	@Query(value="SELECT * FROM users u WHERE (u.first_name=?1 OR u.second_name=?1) AND u.email_verification_status=?2", nativeQuery=true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pegeableRequest, String search, int status);

//	  Requete SQL avec Named Parameters
//	@Query(value="SELECT * FROM users u WHERE (u.first_name= :search OR u.second_name= :search) AND u.email_verification_status= :status", nativeQuery=true)
//	Page<UserEntity> findAllUserByCriteria(Pageable pegeableRequest, @Param("search") String search,  @Param("status") int status);


//	using LIKE for searching a part of the name
	@Query(value="SELECT * FROM users u WHERE (u.first_name LIKE %:search% OR u.second_name LIKE %:search%) AND u.email_verification_status= :status", nativeQuery=true)
	Page<UserEntity> findAllUserByCriteria(Pageable pegeableRequest, @Param("search") String search,  @Param("status") int status);


}
