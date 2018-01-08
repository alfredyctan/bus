String Accumulator
OK 	1. Create a simple string calculator with a method int add(String numbers)
OK		a. The method can take 0, 1 or 2 numbers and will return their sum (for an empty string it will return 0) for example “” or “1” or “1,2”
OK		b. Start with the simplest test case of an empty string and move to 1 and 2 numbers.
OK	2. Allow the add method to handle an unknown amount of numbers.
OK	3. Allow the add method to handle new lines between numbers (instead of commas).
OK		a. The following input is ok: “1\n2,3” (will equal 6).
OK		b. The following input is NOT ok: “1,\n” (don’t need to prove it - just clarifying).
OK	4. Support different delimiters.
OK		a. To change a delimiter, the beginning of the string will contain a separate line that looks like this: “//<delimiter>\n<numbers...>”, for example “//;\n1;2” should return 3 where the delimiter is ‘;’.
?		b. The first line is optional, all existing scenarios should still be supported.
	5. Calling add with a negative number will throw an exception with the message “negatives not allowed” - and the negative that was passed.
		a. If there are multiple negatives, show all of them in the exception message.
	6. Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2.
OK	7. Delimiters can be of any length, for example: “//***\n1***2***3” should return 6.
OK	8. Allow multiple delimiters like this: “//delim1|delim2\n” (with a “|” separating delimiters), for example “//*|%\n1*2%3” should return 6.
OK	9. Make sure you can also handle multiple delimiters with length longer than one character.