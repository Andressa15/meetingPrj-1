package com.interview.prj.room.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.interview.prj.room.model.Room;
import com.interview.prj.room.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomRestController {

	@Autowired
	private RoomService service;
	 
	//Se quiser cadastrar chama esse  , o que diferencia cada um , é exatamente as anotacoes @PostMapping ou @GetMapping e POST para esse
	@PostMapping 
	public ResponseEntity<String> registryRoom(@RequestBody Room room, UriComponentsBuilder builder){
		Room registered = service.registryRoom(room);
		URI uri = builder.path("/room/{id}").build(registered.getId());
		return ResponseEntity.created(uri).body("Created");
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Room> registryRoom(@PathVariable("id") long id){
		Optional<Room> optional = service.findById(id);
		if(!optional.isPresent()) {
	          return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);//Vmos descer uma mensagem tambem , ficou confuso , normalmente a gente desce o codigo de erro ,e uma msg
		}
		return new ResponseEntity<Room>(optional.get(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}") //COmendo pão é kk se quiser ir tentando fazer blz tambem to kkk 
	public ResponseEntity<String> deleteRoom(@PathVariable("id") Long idSala){  //URL : /room -> metodo DELETE
		 service.deleteRoom(idSala);
		 
		System.out.println(idSala);
		return ResponseEntity.ok("ok");
	}

    //A gente vai usar este metodo aaqui , ele vai retornar todas salas cadastradas , a uri é http://localhost:8081/room o método http é GET
	@GetMapping
	public ResponseEntity<List<Room>> getAll(){
		List<Room> rooms = service.findAll();
		return ResponseEntity.ok(rooms);
	}
	
	@GetMapping("/teste")
	public ResponseEntity<String> teste(){
		return ResponseEntity.ok("ok");
	}
}
