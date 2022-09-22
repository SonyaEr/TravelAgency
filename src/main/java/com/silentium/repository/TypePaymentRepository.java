package com.silentium.repository;

import com.silentium.model.TypeFood;
import com.silentium.model.TypePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("typePaymentRepository")
public interface TypePaymentRepository extends JpaRepository<TypePayment, Long> {
    @Query("select t from TypePayment t where t.id=:id")
    TypePayment findById(@Param("id") int id);

    @Query("select t from TypePayment t where t.name=:name")
    TypePayment findByName(@Param("name") String name);

    @Query("select c from TypePayment c order by c.name")
    List<TypePayment> findAll();
}
