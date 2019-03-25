package edu.mum.entities;

import javax.persistence.*;

@Entity
public class Country {

	@Id
	@GeneratedValue
	private Integer Id;
	private String name;
	private String capital;
	
	
	public Country(String name, String capital) {
		this.name = name;
		this.capital = capital;
	}
	
	
	public Country() {
	}

	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	@Override
	public String toString() {
		return "Country [Id=" + Id + ", name=" + name + ", capital=" + capital + "]";
	}

}
