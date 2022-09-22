package com.silentium.repository;

import com.silentium.model.StatusVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("statusVoucherRepository")
public interface StatusVoucherRepository extends JpaRepository<StatusVoucher, Long> {
    @Query("select s from StatusVoucher s where s.id=:id")
    StatusVoucher findById(@Param("id") int id);

    @Query("select s from StatusVoucher s where s.name=:name")
    StatusVoucher findByName(@Param("name") String name);

    @Query("select s from StatusVoucher s order by s.name")
    List<StatusVoucher> findAll();
}
