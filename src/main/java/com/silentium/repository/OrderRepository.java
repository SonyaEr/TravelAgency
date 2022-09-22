package com.silentium.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silentium.model.Order;
import com.silentium.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("select o from Order o where o.id=:id")
	Order findById(@Param("id") int id);

	@Query("select o from Order o order by o.orderDate desc, o.id desc")
	List<Order> findAll();

	@Query("select o from Order o where o.orderDate=:date order by o.orderDate desc, o.id desc")
	List<Order> findByOrderDate(@Param("date") Date date);

	@Query("select o from Order o where o.client.id=:id order by o.orderDate desc, o.id desc")
	List<Order> findByClientId(@Param("id") int id);

	@Query("select o from Order o where o.manager.id=:id order by o.orderDate desc, o.id desc")
	List<Order> findByManagerId(@Param("id") int id);

	@Query("select o from Order o where o.manager.id is null order by o.orderDate desc, o.id desc")
	List<Order> findByWithoutManager();
}