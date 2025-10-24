package com.cts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entity.Menu;
import com.cts.repository.MenuRepository;
import com.cts.exception.MenuNotFoundException;

@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;
	
	
	public Menu addMenu(Menu menu) {
		
		Menu addedMenu=menuRepository.save(menu);
		return addedMenu;
		
	}
	
	public List<Menu> getAllMenu()
	{
		List<Menu> allMenu=menuRepository.findAll();
		if(allMenu.size()==0) {
			throw new MenuNotFoundException("Not Menu Found");
		}
		return allMenu;
	}
	
	public Menu getMenuById(Integer Id) {
		Menu menu=menuRepository.findById(Id).orElseThrow(()->new MenuNotFoundException("No Menu Found by Id: "+Id));
		
		return menu;
	}
	
	public List<Menu> getAllMenuByName(String menuName){
		List<Menu> allMenuByName=menuRepository.findByMenuNameContaining(menuName);
		if(allMenuByName.size()==0) {
			throw new MenuNotFoundException("There is no menu for name: "+menuName);
		}
		return allMenuByName;
	}
	
	public List<Menu> getAllMenuByCategory(String category){
		List<Menu> allMenuByName=menuRepository.findByCategoryContaining(category);
		if(allMenuByName.size()==0) {
			throw new MenuNotFoundException("There is no menu for Category: "+category);
		}
		return allMenuByName;
	}
}
