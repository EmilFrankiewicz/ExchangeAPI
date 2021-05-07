package pl.emilfrankiewicz.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.emilfrankiewicz.model.ResultJSON;
import pl.emilfrankiewicz.service.MailService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class StockControllerTests {

	@InjectMocks
	StockController stockController; // make sure all details provided in stockController e.g. KEY is correct

	@Mock
	MailService mailService; // make sure all details provided in mailService e.g. email is correct

	@Mock
	private RestTemplate restTemplate;

	@Test
	public void stockExchangeReturnResultsWhenDateAndCompanyNameIsCorrect() throws Exception {
		// given
		ResultJSON resultJSON = new ResultJSON("6437810", "IBM", "146.59", "144", "144.33", "2021-05-04", "145.7",
				"145.75", "144.41", "OK");

		// when
		Mockito.when(
				restTemplate.getForEntity("http://localhost:8080/api/ExchangeAPI/2021-05-04/IBM", ResultJSON.class))
				.thenReturn(new ResponseEntity(resultJSON, HttpStatus.OK));

		// then
		ResultJSON testJSON = stockController.currentStockByName("2021-05-04", "IBM");
		Assert.assertEquals(resultJSON, testJSON);
	}

	@Test
	public void stockExchangeWillNotReturnResultsWhenDateAndCompanyNameAreNotCorrect() throws Exception {
		// given
		ResultJSON resultJSON = new ResultJSON("6437810", "IBM", "146.59", "144", "144.33", "2021-05-04", "145.7",
				"145.75", "144.41", "OK");

		// when
		Mockito.when(
				restTemplate.getForEntity("http://localhost:8080/api/ExchangeAPI/2021-05-/qwerty", ResultJSON.class))
				.thenReturn(new ResponseEntity(resultJSON, HttpStatus.INTERNAL_SERVER_ERROR));

		// then
		ResultJSON testJSON = stockController.currentStockByName("2021-05-04", "IBM");
		Assert.assertNotEquals(resultJSON, testJSON);
	}

	@Test
	public void stockExchangeSendEmailWithResultsWhenDateAndCompanyNameAndEmailAreCorrect() throws Exception {
		// given
		String resultString = "Was send correct!";

		// when
		Mockito.when(restTemplate.getForEntity(
				"http://localhost:8080/api/ExchangeAPI/2021-05-04/IBM/correctemail@gmail.com", String.class))
				.thenReturn(new ResponseEntity(resultString, HttpStatus.OK));

		// then
		String testJSON = stockController.sendEmail("correctemail@gmail.com", "2021-05-04", "IBM");
		Assert.assertEquals("Was send correct!", testJSON);
	}

	@Test
	public void stockExchangeWillNotSendEmailWithResultsWhenEmailIsNotCorrect() throws Exception {
		// given
		String resultJSON = "Was send correct!";

		// when
		Mockito.when(
				restTemplate.getForEntity("http://localhost:8080/api/ExchangeAPI/2021-05-04/IBM/qwerty", String.class))
				.thenReturn(new ResponseEntity(resultJSON, HttpStatus.INTERNAL_SERVER_ERROR));

		// then
		String testJSON = stockController.sendEmail("qwerty", "2021-05-04", "IBM");
		Assert.assertNotEquals("Was send correct!", testJSON);
	}

}
