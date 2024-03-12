package com.khrustalev.storageservice.service.abstracts

import com.khrustalev.storageservice.entity.schems.storage.Mechanic

interface MechanicService {
        fun getByIds(ids: MutableList<Long>) : MutableList<Mechanic>
}
