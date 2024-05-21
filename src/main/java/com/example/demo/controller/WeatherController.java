package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.vo.Weather;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WeatherController {
	//decoding
	// 7gBxrsj7WSHvOZjYdEQXGXuT9pq9L8NMGDZ9hzG7VnyftpPH7IIKkWxq2HkS94X9AsKLEzXCkaOZeH94lv28Bg==
	//encoding
	//7gBxrsj7WSHvOZjYdEQXGXuT9pq9L8NMGDZ9hzG7VnyftpPH7IIKkWxq2HkS94X9AsKLEzXCkaOZeH94lv28Bg%3D%3D
	
	
	@Value("${weather.api.key}")
	private String apiKey;

	private static final String WEATHER_API_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";

	@PostMapping("/getWeather")
	public String getWeather(@RequestParam String city, Model model) {

		RestTemplate restTemplate = new RestTemplate();

		// 현재 날짜와 시간을 구합니다.
		LocalDateTime now = LocalDateTime.now();

		// 날짜를 "yyyyMMdd" 형식으로 포맷합니다.
		String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		// 시간을 "HHmm" 형식으로 포맷합니다.
//		String baseTime = now.format(DateTimeFormatter.ofPattern("HHmm"));
		String baseTime = "0800";

		// 결과 출력
		System.out.println("baseDate: " + baseDate);
		System.out.println("baseTime: " + baseTime);

		// 예시 좌표 (실제 사용 시에는 도시의 좌표를 사용)
		String nx = "60";// 129.0276891748 60
		String ny = "127";// 35.1483453258 127

		String url = UriComponentsBuilder.fromHttpUrl(WEATHER_API_URL).queryParam("serviceKey", apiKey)
				.queryParam("pageNo", 1).queryParam("numOfRows", 10).queryParam("dataType", "JSON")
				.queryParam("base_date", baseDate).queryParam("base_time", baseTime).queryParam("nx", nx)
				.queryParam("ny", ny).toUriString();

		String response = restTemplate.getForObject(url, String.class);
		System.out.println(response);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(response);
			JsonNode items = root.path("response").path("body").path("items").path("item");

			Weather weather = new Weather();
			for (JsonNode item : items) {
				String category = item.path("category").asText();
				String value = item.path("fcstValue").asText();
				switch (category) {
				case "T1H":
					weather.setTemperature(value);
					System.out.println("온도" + value);
					break;
				case "REH":
					weather.setHumidity(value);
					System.out.println("습도" + value);
					break;
				case "WSD":
					weather.setWindSpeed(value);
					System.out.println("풍속" + value);
					break;
				}
			}
			model.addAttribute("weather", weather);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "/usr/home/main";
	}
	
	@GetMapping("/testweather")
	@ResponseBody
	public String getWeather(Model model) {

		RestTemplate restTemplate = new RestTemplate();

		// 현재 날짜와 시간을 구합니다.
		LocalDateTime now = LocalDateTime.now();

		// 날짜를 "yyyyMMdd" 형식으로 포맷합니다.
		String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		// 시간을 "HHmm" 형식으로 포맷합니다.
		String baseTime = now.format(DateTimeFormatter.ofPattern("HHmm"));
//		String baseTime = "0800";

		// 결과 출력
		System.out.println("baseDate: " + baseDate);
		System.out.println("baseTime: " + baseTime);

		// 예시 좌표 (실제 사용 시에는 도시의 좌표를 사용)
		String nx = "60";// 129.0276891748 60
		String ny = "127";// 35.1483453258 127

		String url = UriComponentsBuilder.fromHttpUrl(WEATHER_API_URL).queryParam("serviceKey", apiKey)
				.queryParam("pageNo", 1).queryParam("numOfRows", 10).queryParam("dataType", "JSON")
				.queryParam("base_date", baseDate).queryParam("base_time", baseTime).queryParam("nx", nx)
				.queryParam("ny", ny).toUriString();

		String response = restTemplate.getForObject(url, String.class);
		System.out.println(response);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(response);
			JsonNode items = root.path("response").path("body").path("items").path("item");

			Weather weather = new Weather();
			for (JsonNode item : items) {
				String category = item.path("category").asText();
				String value = item.path("fcstValue").asText();
				switch (category) {
				case "T1H":
					weather.setTemperature(value);
					System.out.println("온도" + value);
					break;
				case "REH":
					weather.setHumidity(value);
					System.out.println("습도" + value);
					break;
				case "WSD":
					weather.setWindSpeed(value);
					System.out.println("풍속" + value);
					break;
				}
			}
			model.addAttribute("weather", weather);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}

}