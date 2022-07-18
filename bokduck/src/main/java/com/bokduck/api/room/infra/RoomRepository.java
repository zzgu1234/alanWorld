package com.bokduck.api.room.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bokduck.api.room.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

}
