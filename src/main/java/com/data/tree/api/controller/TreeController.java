package com.data.tree.api.controller;

import java.util.Map;

import com.data.tree.api.Model.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.tree.api.service.SearchTreeServiceImpl;

/**
 * @author ashish
 *
 */
@RestController
@RequestMapping("/search")
public class TreeController {
	
	/*
	 * @Autowired private SearchTreeHelper searchTreeHelper;
	 */
	
	@Autowired
	private SearchTreeServiceImpl searchTreeService;
	
	@GetMapping(value = "/tree")
	public Map<String, Integer> findTree(@RequestParam double x, @RequestParam double y, @RequestParam double radius) {

		Map<String, Integer> responseMap= null;	
		try {
			RequestVO req= RequestVO.builder()
					.xCo(x)
					.yCo(y)
					.radius(radius).build();
		responseMap = searchTreeService.searchTree(req);
		} catch (Exception e) {
			System.out.println("Exception occurred in the method findTree: "+e);			
		}
		
		return responseMap;
	}
	
	

}
