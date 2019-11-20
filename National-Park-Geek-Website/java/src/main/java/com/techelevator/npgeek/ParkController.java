package com.techelevator.npgeek;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDao;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.SurveyDao;
import com.techelevator.npgeek.model.SurveyResult;
import com.techelevator.npgeek.model.TemperatureConverter;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherDao;

@Controller
public class ParkController {

	@Autowired
	private ParkDao parkDao;

	@Autowired
	private SurveyDao surveyDao;

	@Autowired
	private WeatherDao weatherDao;

	@RequestMapping(path = { "/", "/home" }, method = RequestMethod.GET)
	public String displayHomePage(ModelMap map, HttpSession session) {
		List<Park> allParks = parkDao.getAllParks();
		map.put("parks", allParks);
		if (session.getAttribute("isFahrenheit") == null) {
			session.setAttribute("isFahrenheit", true);
		}
		return "HomePage";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String displaySurveyForm(ModelMap map) {
		if(! map.containsAttribute("survey")) {
			map.put("survey", new Survey());
		}
		List<Park> allParks = parkDao.getAllParks();
		map.put("parks", allParks);
		return "Survey";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute Survey survey, BindingResult result, RedirectAttributes flash) {
		if(result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			flash.addFlashAttribute("survey", survey);
			return "redirect:/survey";
		}
		surveyDao.saveToDB(survey);
		return "redirect:/surveyResults";
	}

	@RequestMapping(path = "/surveyResults", method = RequestMethod.GET)
	public String displaySurveyResults(ModelMap map) {
		List<SurveyResult> top5Results = surveyDao.getTop5Surveys();
		map.put("surveyResults", top5Results);

		return "SurveyResults";

	}

	@RequestMapping(path = "/parkDetails", method = RequestMethod.GET)
	public String displayParkDetails(@RequestParam String parkCode, ModelMap map, HttpSession session) {
		Park park = parkDao.getParkByParkCode(parkCode);
		List<Weather> weather = weatherDao.getWeatherByParkCode(parkCode);
		if (!(boolean) session.getAttribute("isFahrenheit")) {
			TemperatureConverter converter = new TemperatureConverter();
			// Converts high and low temperature of each day's weather to Celcius
			for (Weather dailyWeather : weather) {
				int highTemperatureInFahrenheit = converter.fahrenheitToCelcius(dailyWeather.getHighTemperature());
				int lowTemperatureInFahrenheit = converter.fahrenheitToCelcius(dailyWeather.getLowTemperature());
				dailyWeather.setHighTemperature(highTemperatureInFahrenheit);
				dailyWeather.setLowTemperature(lowTemperatureInFahrenheit);
			}

		}
		map.put("park", park);
		map.put("weather", weather);

		return "ParkDetails";

	}

	@RequestMapping(path = "/parkDetails", method = RequestMethod.POST)
	public String changeTemperaturePreference(@RequestParam boolean isFahrenheit, @RequestParam String parkCode,
			RedirectAttributes redirectAttributes, HttpSession session) {
		
		session.setAttribute("isFahrenheit", isFahrenheit);
		redirectAttributes.addAttribute("parkCode", parkCode);
		return "redirect:/parkDetails";

	}

}
