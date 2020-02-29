package com.meetingprj.meeting.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingprj.exception.InvalidMeetingSubject;
import com.meetingprj.exception.PessoaNaoEncontradaErro;
import com.meetingprj.exception.SalaExisteErro;
import com.meetingprj.meeting.exception.TipoDeReuniaoNaoExisteErro;
import com.meetingprj.meeting.model.Meeting;
import com.meetingprj.meeting.model.MeetingType;
import com.meetingprj.meeting.repository.MeetingRepository;
import com.meetingprj.meeting.repository.MeetingTypeRepository;
import com.meetingprj.person.model.Person;
import com.meetingprj.person.repository.PersonRepository;
import com.meetingprj.room.model.Room;
import com.meetingprj.room.repository.RoomRepository;

@Service
public class MeetingService {

	@Autowired
	private MeetingRepository meetingRepository;

	@Autowired
	private MeetingTypeRepository meetingTypeRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private PersonRepository personRepository;

	public Meeting saveMeeting(Meeting meeting) {

		if (meeting.getSubject().length() == 0)
			throw new InvalidMeetingSubject("Assunto da reunião não pode estar vazio.");

		verificarPessoaExistente(meeting);

		isExistRoom(meeting);

		calculateEndTime(meeting);

		return meetingRepository.save(meeting);
	}

	private void verificarPessoaExistente(Meeting meeting) {
		Collection<Person> guests = meeting.getGuests();
		if (guests.isEmpty()) {
			throw new PessoaNaoEncontradaErro("Lista de convidado não pode ser vazia");
		}
		for (Person person : guests) {
			Optional<Person> opt = personRepository.findById(person.getId());
			if (!opt.isPresent()) {
				throw new PessoaNaoEncontradaErro("Pessoa não encontrada");
			}
		}
	}

	private void calculateEndTime(Meeting meeting) {
//		Calendar dataAtual = Calendar.getInstance();

		Calendar dataASerAgendada = meeting.getDate();
		
//		if (dataASerAgendada == null || dataASerAgendada.before(dataAtual)) {
//			throw new DataInvalida("Data não pode ser menor que a data atual");
//		}
		
		System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataASerAgendada.getTime()));

		Calendar endTime = Calendar.getInstance();
		endTime.setTime(dataASerAgendada.getTime());

		System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(endTime.getTime()));

		MeetingType meetingType = isExistMeetingType(meeting);

		endTime.add(Calendar.MINUTE, meetingType.getTime().getMinute());

		System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(endTime.getTime()));

		meeting.setEndTime(endTime);
	}

	private MeetingType isExistMeetingType(Meeting meeting) {
		Optional<MeetingType> opt = meetingTypeRepository.findById(meeting.getMeetingType().getId());
		if (!opt.isPresent()) {
			throw new TipoDeReuniaoNaoExisteErro("Tipo de reuniao nao encontrada");
		}

		MeetingType meetingType = opt.get();
		return meetingType;
	}

	private Room isExistRoom(Meeting meeting) {
		Optional<Room> opt = roomRepository.findById(meeting.getRoom().getId());
		if (!opt.isPresent()) {
			throw new SalaExisteErro("Sala nao encontrada");
		}
		return opt.get();
	}

	public Optional<Meeting> findMeetingById(long id) {
		return meetingRepository.findById(id);
	}

	public List<Meeting> findAll() {
		return meetingRepository.findAll();
	}

	public void deleteByMeetingId(Long id) {
		meetingRepository.deleteById(id);
	}

	@Transactional
	public Meeting updateMeeting(Meeting meeting) {
		Optional<Meeting> meetingFound = meetingRepository.findById(meeting.getId());
		if (meetingFound.isPresent()) {
			Meeting meetingObj = meetingFound.get();
			meetingObj.setDate(meeting.getDate());
			meetingObj.setEndTime(meeting.getEndTime());
			meetingObj.setGuests(meeting.getGuests());
			meetingObj.setMeetingType(meeting.getMeetingType());
			meetingObj.setRoom(meeting.getRoom());
			meetingObj.setSubject(meeting.getSubject());
			return meetingObj;
		}
		return null;
	}
}
