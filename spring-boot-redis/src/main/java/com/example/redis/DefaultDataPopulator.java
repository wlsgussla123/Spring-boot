package com.example.redis;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.redis.domain.Meeting;
import com.example.redis.repository.MeetingRepository;

@Component
public class DefaultDataPopulator implements ApplicationRunner {
	@Autowired
	MeetingRepository meetingRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Meeting meeting = new Meeting();
		meeting.setTitle("new meeting");
		meeting.setStartAt(new Date());
		meetingRepository.save(meeting);
		
		meetingRepository.findAll().forEach(m -> {
			System.out.println("============");
			System.out.println(m.getTitle() + " " + m.getStartAt());
		});
	}
}
