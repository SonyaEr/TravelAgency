package com.silentium.repository;

import com.silentium.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("voucherRepository")
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

	@Query("select v from Voucher v where v.id=:id")
	Voucher findById(@Param("id") int id);

	@Query("select v from Voucher v order by v.voucherDate desc, v.id desc")
	List<Voucher> findAll();

	@Query("select v from Voucher v where v.voucherDate=:date order by v.voucherDate desc, v.id desc")
	List<Voucher> findByVoucherDate(@Param("date") Date date);

	@Query("select v from Voucher v where v.order.client.id=:id order by v.voucherDate desc, v.id desc")
	List<Voucher> findByClientId(@Param("id") int id);

	@Query("select v from Voucher v where v.manager.id=:id order by v.voucherDate desc, v.id desc")
	List<Voucher> findByManagerId(@Param("id") int id);

	@Query(value = " select v.*\n" +
			" from Voucher v \n" +
			" left join `Order` o \n" +
			" on v.fk_order_id = o.order_id \n" +
			" where o.order_id = :id\n" +
			" limit 1",nativeQuery = true)
	Voucher findByOrderId(@Param("id") int id);

}