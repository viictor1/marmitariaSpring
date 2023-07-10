package com.victortavin.marmitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.victortavin.marmitaria.entities.Add_BalanceEntity;
import com.victortavin.marmitaria.entities.UserEntity;

public interface AddBalanceRepository extends JpaRepository<Add_BalanceEntity, Long>{
	
	
    @Query("SELECT a FROM Add_BalanceEntity a WHERE a.userEntity = :userEntity AND a.id = "
    		+ "(SELECT MAX(a2.id) FROM Add_BalanceEntity a2 WHERE a2.userEntity = :userEntity)")
    Add_BalanceEntity findLatestAddBalanceByUser(UserEntity userEntity);
}
