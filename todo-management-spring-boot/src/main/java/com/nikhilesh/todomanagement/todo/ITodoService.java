package com.nikhilesh.todomanagement.todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITodoService {
	List<Todo> getTodosByUser(String user);
	
	Optional<Todo> getTodosById(int id);
	
	void updateTodo(Todo todo);
	
	void addTodo(String name , String description, Date targetDate);
	
	void deleteTodo(int id);
	
	void saveTodo(Todo todo);
}
