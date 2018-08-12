package com.example.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.redis.domain.Meeting;


public interface MeetingRepository extends CrudRepository<Meeting, String>  {
	
}