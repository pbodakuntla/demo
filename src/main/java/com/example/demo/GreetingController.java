package com.example.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.contract.User;
import com.example.demo.service.GitHubLookupService;

@RestController
@RequestMapping("/wishApi")
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private GitHubLookupService gitHubLookupService;

	@GetMapping("/greeting")
	public @ResponseBody Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) throws ExecutionException {
		
		// Start the clock
	    long start = System.currentTimeMillis();

	    // Kick of multiple, asynchronous lookups
	    CompletableFuture<User> page1;
	    CompletableFuture<User> page2;
	    CompletableFuture<User> page3;
		try {
			page1 = gitHubLookupService.findUser("PivotalSoftware");
			System.out.println("I ama bac12121k");
		    page2 = gitHubLookupService.findUser("CloudFoundry");
		    page3 = gitHubLookupService.findUser("Spring-Projects");
		    
		 // Wait until they are all done
		    CompletableFuture.allOf(page1,page2,page3).join();

		    // Print results, including elapsed time
		    System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
		    System.out.println("--> " + page1.get());
		    System.out.println("--> " + page2.get());
		    System.out.println("--> " + page3.get());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}