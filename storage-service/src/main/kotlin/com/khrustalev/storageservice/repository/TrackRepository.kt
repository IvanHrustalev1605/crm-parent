package com.khrustalev.storageservice.repository;

import com.khrustalev.storageservice.entity.schems.storage.Track
import org.springframework.data.jpa.repository.JpaRepository

interface TrackRepository : JpaRepository<Track, Long> {
}