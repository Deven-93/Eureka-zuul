package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.Employee;
import com.example.demo.repository.MyJpaRepo;
//

@RestController
@RequestMapping("/home")
public class HomeController {
	
	
	@Autowired
	MyJpaRepo repo;
	

	
	@GetMapping("/index")
	public ModelAndView getIndex()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	
	// ---- Add employee
	@PostMapping("/add")
	public ModelAndView add(@ModelAttribute("emp") Employee emp)
	{
		ModelAndView mv = new ModelAndView();
		repo.save(emp);
		List<Employee> employees= repo.getEmployee();
		mv.addObject("lists",employees );
		mv.setViewName("index");
		return mv;
	}
	
	
	@GetMapping("/fetch")
	public ModelAndView getHome()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		return mv;
	}
	
	@GetMapping("get")
	//@ResponseBody
	public ModelAndView getSalary(@RequestParam("salary") double salary,@RequestParam("category") String category)
	{
		ModelAndView mv = new ModelAndView();
		List<Employee> emp=null; 
		if(category.equals("great"))
		{
		emp= repo.getSalaryGreater(salary);
		}
		if(category.equals("less"))
		{ 
			emp= repo.getSalaryLess(salary);
			
		}
		mv.addObject("emp", emp);
		mv.setViewName("result");
		
		return mv;
	}
	
	@PostMapping(path="employee")
	public Employee addEmployee(@RequestBody Employee e)
	{
		repo.save(e);
		return e;
	}
	
	@PostMapping("getall")
	public List<Employee> getAll()
	{
		
		return repo.findAll();
	}
}
	

