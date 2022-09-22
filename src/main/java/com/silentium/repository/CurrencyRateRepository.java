package com.silentium.repository;

import com.silentium.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("currencyRateRepository")
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    @Query(value = "select * from currency_rate c where c.date<=:date order by c.date desc limit 1", nativeQuery = true)
    CurrencyRate findByDate(@Param("date") Date date);
}