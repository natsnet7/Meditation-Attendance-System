package edu.mum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.mum.entities.Country;
import edu.mum.repositories.CountryRepository;

@Controller
public class CountryController {

	@Autowired
	private CountryRepository countryRepo;
	
	@GetMapping(value= {"/"})
	public String showPage(Model model, @RequestParam(defaultValue ="0") int page){
		model.addAttribute("data", countryRepo.findAll(new PageRequest(page, 4)));
		model.addAttribute("currentPage", page);
		return "index";
	}
	
	@PostMapping(value="/save")
	public String saveCountry(Country c){
		countryRepo.save(c);
		return "redirect:/";
	}
	
	@GetMapping(value="/delete")
	public String deleteCountry(Integer cid){
		countryRepo.deleteById(cid);
		return "redirect:/";
	}
	
	@GetMapping(value="/findOne")
	@ResponseBody
	public String findOne(Integer cid){
		countryRepo.findById(cid);
		return "redirect:/";
	}
}
