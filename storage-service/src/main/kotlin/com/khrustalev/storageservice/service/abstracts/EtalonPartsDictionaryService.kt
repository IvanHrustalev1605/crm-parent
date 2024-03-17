package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.schems.dictionary.EtalonPartsDictionary

interface EtalonPartsDictionaryService {
    fun findById(etalonPartsDictionaryId: Long): EtalonPartsDictionary?
}