package com.bokduck.api.room.infra;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bokduck.api.room.infra.dto.RoomListParam;

@Repository(value = "roomMapper")
@Mapper
public interface RoomMapper {

	List<RoomResult> findAll(RoomListParam param) throws Exception;

	Optional<RoomResult> findDetail(Long roomNo);

}
