package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.EtalonPartsStocks
import org.springframework.data.jpa.repository.JpaRepository

interface EtalonPartsStocksRepository : JpaRepository<EtalonPartsStocks, Long> {
}