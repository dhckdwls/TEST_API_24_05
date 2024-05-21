package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LocationController {
	
	@RequestMapping("/location")
	@ResponseBody
	public String fetchAndSaveData() {
		String serviceKey = "7gBxrsj7WSHvOZjYdEQXGXuT9pq9L8NMGDZ9hzG7VnyftpPH7IIKkWxq2HkS94X9AsKLEzXCkaOZeH94lv28Bg==";
		String apiUrl = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1";
		int pageNo = 1;
		//int numOfRows = 13040;
		int numOfRows = 50;
		String arrange = "A"; // (A=제목순, C=수정일순, D=생성일순)
		String areaCode1 = ""; // 필요에 따라 변경하세요
		int contentTypeId1 = 12; // 관광 명소에 해당하는 contentTypeId
		String mobileApp = "AppTest";
		String mobileOS = "ETC";

		StringBuilder apiUrlWithParamsBuilder = new StringBuilder(apiUrl);
		apiUrlWithParamsBuilder.append("?serviceKey=").append(serviceKey).append("&pageNo=").append(pageNo)
				.append("&numOfRows=").append(numOfRows).append("&MobileApp=").append(mobileApp).append("&MobileOS=")
				.append(mobileOS).append("&arrange=").append(arrange).append("&areaCode=").append(areaCode1)
				.append("&contentTypeId=").append(contentTypeId1).append("&_type=json");

		String apiUrlWithParams = apiUrlWithParamsBuilder.toString();

		RestTemplate restTemplate = new RestTemplate();
		String responseData = restTemplate.getForObject(apiUrlWithParams, String.class);

		// JSON 데이터 처리
		try {
			// JSON 데이터를 APIarticle 객체로 변환하여 저장
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(responseData);
			JsonNode itemNode = rootNode.path("response").path("body").path("items").path("item");

			for (JsonNode node : itemNode) {
				String title = node.path("title").asText();
				int areaCode = node.path("areacode").asInt();
				int contentTypeId = node.path("contenttypeid").asInt();
				String address = node.path("addr1").asText() + node.path("addr2").asText();
				String mapX = node.path("mapx").asText();
				String mapY = node.path("mapy").asText();
				String firstImage = node.path("firstimage").asText();
				String firstImage2 = node.path("firstimage2").asText();
				String contentId = node.path("contentid").asText();
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseData;
	}

}
