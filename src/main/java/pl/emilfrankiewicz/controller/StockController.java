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

@RestController
public class StockController {

	private final String KEY = "enter you KEY here";
	private final String URL = "https://api.polygon.io/v1/open-close/{companyName}/{date}?unadjusted=true&apiKey={KEY}";
	private RestTemplate restTemplate = new RestTemplate();
	private ResultJSON result = new ResultJSON();

	@RequestMapping(value = "/api/ExchangeAPI/{date}/{companyName}", method = RequestMethod.GET)
	public ResultJSON CurrentStockByName(@PathVariable("date") @DateTimeFormat(iso = ISO.DATE) String date,
			@PathVariable("companyName") String company) throws ResourceDoesNotExistException {

		try {
			result = restTemplate.getForObject(URL, ResultJSON.class, company, date, KEY);
		} catch (RestClientException exception) {
			//Make sure that the company was available on the stock exchange on a given day (e.g. holidays)
			throw new ResourceDoesNotExistException("Date or company not found.");
		}
		return result;
	}
}
