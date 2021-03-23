package com.example.demo.spring.controller;


import com.example.demo.model.entities.PublisherEntity;
import com.example.demo.services.impl.PublisherService;
import com.example.demo.spring.repository.BookRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Log
public class RedirectController
{
	
	@Autowired
	private PublisherService publisherService;

	@Autowired
	private BookRepository bookRepository;
	
	
	//@RequestMapping(value = "", method = RequestMethod.GET)
	@GetMapping("/name/{name}")
	public Page<PublisherEntity> baseUrlRedirect(@PathVariable(name = "name") String name)
	{




		// List<Author> authorList =authorRepository.findAll();
		
		//  Page<Author> pageList =  authorRepository.findByFioContainingIgnoreCaseOrderByFio("a",

		//Page<Book> pageList = bookRepository.findAllWithoutContent(new PageRequest(0, 10, new Sort(Sort.DEFAULT_DIRECTION, "fio")));
		return publisherService.search(name);
	}
   /* public static Sort by(Direction direction, String... properties ){
    	
    	
    	return Sort.by((String) Arrays.stream(properties)
									  .map(it-> new Order(direction, it))
									  .collect(Collectors.toList()));
	}*/
	
}
