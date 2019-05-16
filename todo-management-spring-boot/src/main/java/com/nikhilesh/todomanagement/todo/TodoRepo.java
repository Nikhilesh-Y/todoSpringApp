package com.nikhilesh.todomanagement.todo;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<Todo , Integer>{
	
}
