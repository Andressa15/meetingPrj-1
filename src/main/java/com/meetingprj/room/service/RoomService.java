package com.meetingprj.room.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingprj.room.model.Room;
import com.meetingprj.room.repository.RoomRepository;

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
    public Room updateRoom(Room room ){
//    	Optional<Room> opt = repository.findById(id);
//    	if(opt.isPresent()) {
//    		System.out.println("Sala não encontrada");
//    		return;
//    	}
    	return repository.save(room);
    }
    public void deleteRoom( Long id ) {
    	repository.deleteById(id);
    	
    }
}
