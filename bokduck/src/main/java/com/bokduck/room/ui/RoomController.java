package com.bokduck.room.ui;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Room Controller")
@Validated
@RequiredArgsConstructor
@RestController
public class RoomController {
	
	
	@GetMapping("/rooms")
	public void findAll() throws Exception{
		
		
	}
	
	@GetMapping("/room")
	public void find() throws Exception{
		
		
	}
	
	@PostMapping("/room")
	public void create() throws Exception{
		
		
	}
	
	@PatchMapping("/room")
	public void edit() throws Exception{
		
		
	}
	
	@DeleteMapping("/room")
	public void delete() throws Exception{
		
		
	}

}
