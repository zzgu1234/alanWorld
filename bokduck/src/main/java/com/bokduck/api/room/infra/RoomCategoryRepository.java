package com.bokduck.api.room.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bokduck.api.room.domain.RoomCategory;

public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long>{

}
