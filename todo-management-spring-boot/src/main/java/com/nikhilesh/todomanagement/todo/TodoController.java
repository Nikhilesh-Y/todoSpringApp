package com.nikhilesh.todomanagement.todo;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
	
	@Autowired
	private ITodoService service;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataBinder.registerCustomEditor(Date.class , new CustomDateEditor(dateFormat,true));
	}

	//understand this code
	@RequestMapping( value = "/list-todos", method = RequestMethod.GET )
	public String showTodos (ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos" , service.getTodosByUser(name));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return principal.toString();
	}
	
	@RequestMapping(value = "/add-todo" , method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo" , new Todo());
		return "todo";
	}
	
	@RequestMapping(value = "/add-todo" , method = RequestMethod.POST)
	public String addTodo(ModelMap model , @Valid Todo todo , BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todo.setUsername(getLoggedInUserName(model));
		service.saveTodo(todo);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo" , method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id , ModelMap model) {
		Todo todo = service.getTodosById(id).get();
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo" , method = RequestMethod.POST)
	public String updateTodo(ModelMap model , @Valid Todo todo , BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todo.setUsername(getLoggedInUserName(model));
		service.updateTodo(todo);
		return "redirect:/list-todos";
	}
}
