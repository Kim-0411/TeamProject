package kr.or.ksmart37.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@DeleteMapping("/member")
	public int memberDelete(@RequestParam("memberId") String memberId) {
		return 0;
	}
	
	@GetMapping("/member")
	public Map<String,Object> memberRead(@RequestParam("memberId") String memberId) {
		Map<String,Object> map = new HashMap<String,Object>();
		return map;
	}
}
