package com.job.react;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReactController {

	@RequestMapping(value = "/hello/testForm")
	public ModelAndView reactPracticeForm() {
		return new ModelAndView("../../react/testForm");
	}

	@RequestMapping(value = "/hello/getData")
	@ResponseBody
	public List<TestData> reactPracticeDate() {
		List<TestData> testDataList = Lists.newArrayList();
		testDataList.add(new TestData("Pete Hunt", "댓글입니다"));
		testDataList.add(new TestData("Jordan Walke", "*또 다른* 댓글입니다"));
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
