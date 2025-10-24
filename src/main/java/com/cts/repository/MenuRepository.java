package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
	public List<Menu> findByMenuNameContaining(String menuName);
	public List<Menu> findByCategoryContaining(String category);
	

}
