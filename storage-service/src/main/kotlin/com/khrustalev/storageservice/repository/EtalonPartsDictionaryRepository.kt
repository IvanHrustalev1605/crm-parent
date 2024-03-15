package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.dictionary.EtalonPartsDictionary
import org.springframework.data.jpa.repository.JpaRepository

interface EtalonPartsDictionaryRepository : JpaRepository<EtalonPartsDictionary, Long> {
}