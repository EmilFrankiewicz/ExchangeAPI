package pl.emilfrankiewicz.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.emilfrankiewicz.exception.ResourceDoesNotExistException;
import pl.emilfrankiewicz.model.ResultJSON;
import pl.emilfrankiewicz.model.SubjectDTO;
import pl.emilfrankiewicz.service.MailService;

@RestController
public class StockController {

	private final String KEY = "";
	private final String URL = "https://api.polygon.io/v1/open-close/{companyName}/{date}?unadjusted=true&apiKey={KEY}";
	private RestTemplate restTemplate = new RestTemplate();
	private ResultJSON result = new ResultJSON();
	private MailService mailService;
	private SubjectDTO subjectDTO = new SubjectDTO();

	public StockController(MailService mailService) {
		this.mailService = mailService;
	}

	@RequestMapping(value = "/api/ExchangeAPI/{date}/{companyName}", method = RequestMethod.GET)
	public ResultJSON CurrentStockByName(@PathVariable("date") @DateTimeFormat(iso = ISO.DATE) String date,
			@PathVariable("companyName") String company) throws ResourceDoesNotExistException {

		try {
			result = restTemplate.getForObject(URL, ResultJSON.class, company, date, KEY);
		} catch (RestClientException exception) {
			// Make sure that the company was available on the stock exchange on a given day
			// (e.g. holidays)
			throw new ResourceDoesNotExistException("Date or company not found.");
		}
		return result;
	}

	@RequestMapping(value = "/api/ExchangeAPI/{date}/{companyName}/{email}", method = RequestMethod.GET)
	public String sendEmail(@PathVariable("email") String email,
			@PathVariable("date") @DateTimeFormat(iso = ISO.DATE) String date,
			@PathVariable("companyName") String company) throws ResourceDoesNotExistException {

		try {
			result = restTemplate.getForObject(URL, ResultJSON.class, company, date, KEY);
		} catch (RestClientException exception) {
			throw new ResourceDoesNotExistException("Date or company not found.");
		}

		subjectDTO.setDate(date);
		subjectDTO.setCompanyName(company);
		
		try {
			mailService.sendSimpleEmail(email, "Result from: " + subjectDTO.toString(), result.toString());
		} catch (RestClientException exception) {
			throw new ResourceDoesNotExistException("Wrong email address.");
		}
		return "Was send correct!";
	}
}
