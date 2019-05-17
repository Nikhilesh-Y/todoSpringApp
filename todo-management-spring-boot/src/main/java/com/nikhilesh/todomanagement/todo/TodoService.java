package com.nikhilesh.todomanagement.todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService implements ITodoService {
	
	@Autowired
	private TodoRepo repo;

	@Override
	public List<Todo> getTodosByUser(String user) {
		// TODO Auto-generated method stub
		return repo.findByUserName(user);
	}

	@Override
	public Optional<Todo> getTodosById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void updateTodo(Todo todo) {
		repo.save(todo);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTodo(String name, String description, Date targetDate) {
		// TODO Auto-generated method stub
		repo.save(new Todo(name,description,targetDate));
		
	}

	@Override
	public void deleteTodo(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public void saveTodo(Todo todo) {
		// TODO Auto-generated method stub
		repo.save(todo);
		
	}

}
