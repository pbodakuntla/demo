package com.example.demo;

//public record Greeting(long id, String content) {
//
//}

public class Greeting {
	private final long id;
	private final String content;

	/**
	 * @param id
	 * @param content
	 */
	public Greeting(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

}