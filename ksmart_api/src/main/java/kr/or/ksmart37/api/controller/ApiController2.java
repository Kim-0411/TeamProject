package kr.or.ksmart37.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController2 {

	@GetMapping("/apiCall")
	public String apiCall() {
		return "apiCall";
	}
	
	@GetMapping(value="/json", produces = "application/json")
	public @ResponseBody Map<String, String> json() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("test1", "1");
		map.put("test2", "2");
		map.put("test3", "3");
		return map;
	}
	
	@GetMapping("/apiCall3")
	public String apiCall3() throws ParseException {
		URL url;
		try {
			url = new URL("http://localhost/json");			
			URLConnection urlc = url.openConnection();
			InputStream in = urlc.getInputStream();
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);
			String temp;
			String jsonStr ="";
			while((temp = br.readLine()) != null) {
				System.out.println(temp);
				jsonStr += temp;
			}
			System.out.println(urlc.toString());
			
			JSONParser json = new JSONParser(jsonStr);
			LinkedHashMap<String, Object> map = json.parseObject();
			System.out.println(map.toString());
			System.out.println(map.get("test1")+ "<<" + "test1");
			System.out.println(map.get("test2")+ "<<" + "test2");
			System.out.println(map.get("test3")+ "<<" + "test3");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "apiCall";
	}
	
	@GetMapping(value="/apiResult", produces = "application/json")
	public @ResponseBody List<Map<String,String>> apiCall2(@RequestParam("zone") String zone) {
		
		List<Map<String,String>> wList = new ArrayList<Map<String,String>>();
		
		String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp";
		try {
			Document jsoup = Jsoup.connect(url).data("zone", zone).get();
			
			//System.out.println(jsoup.toString());
			
			Elements els = jsoup.select("data");
			for(Element el : els) {
				Map<String,String> map = new HashMap<String,String>();
				String hour = el.select("hour").text();
				String wfKor = el.select("wfKor").text();
				map.put("hour", hour);
				map.put("wfKor", wfKor);
				wList.add(map);
				System.out.println(hour + " -- " + wfKor);
			}
						
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wList;
	}

}
