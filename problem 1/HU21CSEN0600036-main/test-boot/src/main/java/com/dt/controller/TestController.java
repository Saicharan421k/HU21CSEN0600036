package com.dt.controller;

import com.dt.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/numbers")
public class TestController {

	private static final int WINDOW_SIZE = 10;
	private final Set<Integer> numberWindow = new ConcurrentSkipListSet<>();
	private final RestTemplate restTemplate = new RestTemplate();


	@GetMapping("/wish")
	public ResponseEntity<String> getData() {
		return new ResponseEntity<>("Test!!", HttpStatus.OK);
	}

	@GetMapping("/{numberId}")
	public Response getNumbers(@PathVariable String numberId) {
		String url = "http://20.244.56.144/test/even/"; // Replace with actual URL
		switch (numberId) {
			case "e":
				List<Integer> newNumbers = fetchNumbers(url);
				if (newNumbers == null) {
					return new Response("Failed to fetch numbers", null, null, 0);
				}

				numberWindow.addAll(newNumbers);
				List<Integer> currentWindow = getCurrentWindow();
				double avg = calculateAverage(currentWindow);

				return new Response(newNumbers, getPreviousWindowState(), currentWindow, avg);
			//break;
			case "p":
				List<Integer> data = Arrays.asList(2,4,6,8,10);
				Response response =  new Response();
				response.setNumbers(response);
				return response ;

		}
		/*List<Integer> newNumbers = fetchNumbers(url);
		if (newNumbers == null) {
			return new Response("Failed to fetch numbers", null, null, 0);
		}

		numberWindow.addAll(newNumbers);
		List<Integer> currentWindow = getCurrentWindow();
		double avg = calculateAverage(currentWindow);

		return new Response(newNumbers, getPreviousWindowState(), currentWindow, avg);*/
	}

	private List<Integer> fetchNumbers(String url) {
		try {
			//String[] response = restTemplate.getForObject(url, String[].class);
			//String[] response = { "1", "2", "3", "4", "5", "6", "7", "8"};
			String[] response = {"2","4", "6", "7", "8",
					"9", "10", "11", "12", "13", "14", "15", "16",
					"17", "18", "19", "20", "21", "22", "23", "24",
					"25", "26", "27", "28", "29", "30"};
			return response == null ? new ArrayList<>() : Arrays.stream(response)               // Create a stream from the array
																.map(Integer::parseInt)
																.filter(num -> num%2==0)
																.collect(Collectors.toList());
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return null;
		}
	}

	private List<Integer> getCurrentWindow() {
		List<Integer> windowList = new ArrayList<>(numberWindow);
		if (windowList.size() > WINDOW_SIZE) {
			windowList = windowList.subList(windowList.size() - WINDOW_SIZE, windowList.size());
		}
		return windowList;
	}

	private List<Integer> getPreviousWindowState() {
		List<Integer> previousState = new ArrayList<>(numberWindow);
		if (previousState.size() > WINDOW_SIZE) {
			previousState = previousState.subList(0, previousState.size() - WINDOW_SIZE);
		}
		return previousState;
	}

	private double calculateAverage(List<Integer> numbers) {
		if (numbers.isEmpty()) return 0;
		return numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
	}
}
