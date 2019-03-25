package edu.mum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.mum.entities.Country;
import edu.mum.repositories.CountryRepository;

@SpringBootApplication
public class CountriesApplication implements CommandLineRunner{

	@Autowired
	private CountryRepository countryRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CountriesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		countryRepo.save(new Country("Cameroon", "Yaounde"));
		countryRepo.save(new Country("Rwanda", "Kigali"));
		countryRepo.save(new Country("Eritrea", "Asmara"));
		countryRepo.save(new Country("Uganda", "Kampala"));
		countryRepo.save(new Country("DRC", "Kinshansa"));
		countryRepo.save(new Country("Burundi", "Bujumbura"));
		countryRepo.save(new Country("Tanzania", "Dodoma"));
		countryRepo.save(new Country("Ethiopia", "Addis ababa"));
		countryRepo.save(new Country("Soudan", "Khartoum"));
		countryRepo.save(new Country("Kenya", "Nairobi"));
		countryRepo.save(new Country("Senegal", "Dakar"));
		countryRepo.save(new Country("Gambia", "Banjul"));
	}

}
