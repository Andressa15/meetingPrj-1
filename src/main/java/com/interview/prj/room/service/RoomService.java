package com.interview.prj.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.prj.room.model.Room;
import com.interview.prj.room.repository.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private  RoomRepository repository;
	
	public Room registryRoom(Room room) {
		Room registered = repository.save(room);
		return registered;
	}

	public Optional<Room> findById(long id) {
		Optional<Room> opt = repository.findById(id);
		return opt;
	}

	public List<Room> findAll() {
		return repository.findAll();
	}
    public void updateRoom(Room room, Long id) {
    	room.setId(id);
    	repository.save(room); 
    }
    public void deleteRoom( Long id ) {
    	repository.deleteById(id);
    	
    }
}
