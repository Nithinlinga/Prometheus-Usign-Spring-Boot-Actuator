package com.cts.controller;

import java.util.List;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.Menu;
import com.cts.exception.MenuNotFoundException;
import com.cts.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	@Timed(value = "slow.request",description = "Slow API Response Time", histogram = true,percentiles = {0.5,0.9,0.99})
	@PostMapping
	public ResponseEntity<?> addMenu(@RequestBody Menu menu) {
	    String category = menu.getCategory();
	    if (category == null || 
	        (!category.contains("Starter") &&
	         !category.contains("Main") &&
	         !category.contains("Drinks")) ||
	        menu.getPrice() < 0) {

	        return new ResponseEntity<>("Menu not added", HttpStatus.BAD_REQUEST);
	    }

	    Menu newMenu = menuService.addMenu(menu);
	    return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
	}

	
	@GetMapping
	public ResponseEntity<List<Menu>> getAllMenu(){
		List<Menu> allMenu=menuService.getAllMenu();
		return new ResponseEntity<List<Menu>>(allMenu,HttpStatus.OK);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Menu> getMenuById(@PathVariable Integer Id){
		Menu menu=menuService.getMenuById(Id);
		return new ResponseEntity<Menu>(menu,HttpStatus.OK);
		
	}
	
	@GetMapping("/menuName/{menuName}")
	public ResponseEntity<List<Menu>> getAllMenuByName(@PathVariable String menuName){
		List<Menu> menus=menuService.getAllMenuByName(menuName);
		return new ResponseEntity<List<Menu>>(menus,HttpStatus.OK);
	}
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Menu>> getAllMenuByCategory(@PathVariable String category){
		List<Menu> menus=menuService.getAllMenuByCategory(category);
		return new ResponseEntity<List<Menu>>(menus,HttpStatus.OK);
	}
}
