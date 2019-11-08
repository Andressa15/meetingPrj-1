package com.interview.prj.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.prj.room.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

}
