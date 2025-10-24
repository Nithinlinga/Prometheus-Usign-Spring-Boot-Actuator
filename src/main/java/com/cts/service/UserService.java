package com.cts.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entity.User;
import com.cts.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
	
	private final UserRepository repository;
	private final Counter userCounter;
	private final MeterRegistry meterRegistry;


    @Autowired
    public UserService(UserRepository repository, MeterRegistry meterRegistry) {
        this.repository = repository;
		this.userCounter=Counter.builder("user.created.registered")
				.tags("status","registered")
				.description("Total no of users created")
				.register(meterRegistry);
		this.meterRegistry=meterRegistry;
    }
	
	
//	public UserService(UserRepository repository) {
//		this.repository=repository;
//	}
//	
	public User register(User user) {
//	    if (user.getEmail() == null) {
//	        throw new IllegalArgumentException("User or email must not be null");
//	    }

	    User newUser=repository.save(user);
		this.userCounter.increment();
		return  newUser;
	}
	
	public String login(User user) {
		return "token";
	}

	@Timed(value = "slow.request.users",description = "Slow API Response Time", histogram = true,percentiles = {0.5,0.9,0.99})
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public void recordUsers(){
		long totalUsers= repository.findAll().size();
		Tags tags=Tags.of("status","registered");
		meterRegistry.gauge("total.users",tags,totalUsers);
	}
}
