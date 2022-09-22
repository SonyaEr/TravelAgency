package com.silentium.repository;

import com.silentium.model.Person;
import com.silentium.model.Voucher;
import com.silentium.model.VoucherClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("voucherClientRepository")
public interface VoucherClientRepository extends JpaRepository<VoucherClient, Long> {

	@Query("select v.person from VoucherClient v where v.voucher.id=:id")
	List<Person> findByVoucherId(@Param("id") int id);

	@Query("select v.voucher from VoucherClient v where v.person.id=:id")
	List<Voucher> findByClientId(@Param("id") int id);

}