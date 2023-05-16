package com.bankdemoproject.repositories;

import com.bankdemoproject.entities.Represantive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RepresantiveRepository extends JpaRepository<Represantive, Long> {
    Optional<Represantive> findByTcNo(String tcNo);

    Optional<Represantive> findByTcNoAndPassword(String tcNo, String password);

    Optional<Represantive> findByTcNoAndPasswordAndPhoneNo(String tcNo, String password, String phoneNo);

    long deleteByTcNo(String tcNo);

    @Query("select r from Represantive r where r.repId =(select rp.topManager from Represantive  rp where rp.repId=?1)")
    Represantive findByRepIdEqualsAndTopManager(Long repId);



}