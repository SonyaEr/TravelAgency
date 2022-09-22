package com.silentium.repository;

import com.silentium.model.City;
import com.silentium.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hotelRepository")
public interface HotelRepository extends JpaRepository<Hotel, Long>, PagingAndSortingRepository<Hotel, Long> {

	@Query("select h from Hotel h where h.name = :name")
	Hotel findByName(@Param("name") String name);

	@Query("select h from Hotel h where h.city.id=:city_id")
	Page<Hotel> findByCity(@Param("city_id") String city_id, Pageable pageReq);

	@Query("select c from Hotel c order by c.name")
	List<Hotel> findAll();

//	default Page<Hotel> findByCity(City city, Pageable pageReq) {return findByCity(city.getName(), pageReq);	}

}