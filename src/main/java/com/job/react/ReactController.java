package com.job.react;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

@Controller
public class ReactController {

	List<TestData> testDataList = Lists.newArrayList(new TestData("Pete Hunt", "댓글입니다"), new TestData("Jordan Walke", "*또 다른* 댓글입니다"));

	@RequestMapping(value = "/hello/testForm")
	public ModelAndView reactPracticeForm() {
		return new ModelAndView("/member/testForm");
	}

	@RequestMapping(value = "/hello/getData")
	@ResponseBody
	public List<TestData> getTestDate() {
		return testDataList;
	}

	@RequestMapping(value = "/hello/saveData", method = RequestMethod.POST)
	@ResponseBody
	public List<TestData> saveTestDate(TestData testData) {
		testDataList.add(testData);
		return testDataList;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TestData {
		private String author;
		private String text;
	}
}
