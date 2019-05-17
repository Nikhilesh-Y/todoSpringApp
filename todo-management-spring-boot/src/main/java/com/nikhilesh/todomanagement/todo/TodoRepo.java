package com.nikhilesh.todomanagement.todo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<Todo , Integer>{
	List<Todo> findByUserName(String user);
}
