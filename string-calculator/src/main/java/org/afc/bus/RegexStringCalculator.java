package org.afc.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.afc.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegexStringCalculator implements StringCalculator {

	private static final Logger logger = LoggerFactory.getLogger(RegexStringCalculator.class);
	
	private static final String DEFAULT_DELIMITER = ",|\\\\n";
	
	private static final Pattern DELIMITER_PATTERN = Pattern.compile("(?<=^//)(.*)(?=\\n)|(?<=\\n)(.*)(?=$)");
	
	private static final String DELIMITER = "DELIMITER";

	private static final String PATTERN_TEMPLATE = "(?<=DELIMITER|^)([-+]?[0-9]+)(?=DELIMITER|$)";

	
	/* do not escape | + - to support multi delimiter and +/- ve sign */
	private static final String ALL_ESCAPE = "[\\<\\(\\[\\{\\\\\\^\\=\\$\\!\\]\\}\\)\\?\\*\\.\\>]";

	private static final String ESCAPED = "\\\\\\\\$0";

	private List<String> numbers;
	
	private String delimiter;
	
	public RegexStringCalculator() {
		this.numbers = new ArrayList<>();
		this.delimiter = DEFAULT_DELIMITER;
	}
	
	@Override
	public int add(String input) {
		logger.debug("raw input : [{}]", input);

		Matcher delMatcher = DELIMITER_PATTERN.matcher(input);
		if (delMatcher.find()) {
			String group = delMatcher.group();
			if (delMatcher.find()) {
				input = delMatcher.group();
				delimiter = group.replaceAll(ALL_ESCAPE, ESCAPED);
			}
		}
		
		String pattern = PATTERN_TEMPLATE.replaceAll(DELIMITER, delimiter);
		logger.info("delimiter : [{}], input : [{}], pattern : [{}]", delimiter, input, pattern);
		
		Matcher matcher = Pattern.compile(pattern).matcher(input);
		while (matcher.find()) {
			String group = matcher.group();
			logger.debug("group     : [{}]", group);
			numbers.add(group);
		}		
		
		List<Integer> negetives = numbers.stream().map(Integer::valueOf).filter(i -> i < 0).collect(Collectors.toList());
		if (negetives.size() > 0) {
			throw new ArithmeticException("negative number is not allowed : " + negetives);
		}
		return numbers.stream().mapToInt(Integer::valueOf).filter(i -> i < 1000).sum();
	}
}
