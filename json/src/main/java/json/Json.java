package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import json.Data_class;

public class Json {
	public static void main(String[] args) throws Exception {

		Pattern pattern;
		Matcher matcher;

		String json = "{"
				+ "\"data\":\"title='title',button='button'\""
				+ "}";

		ObjectMapper mapper = new ObjectMapper();
		Data_class data_class = mapper.readValue(json, Data_class.class);

		System.out.println(data_class.data);

		pattern = Pattern.compile("title='(.*?)'");
		matcher = pattern.matcher(data_class.data);
		if (matcher.find()) {
			System.out.println("title:" + matcher.group(1));
		}

		pattern = Pattern.compile("button='(.*?)'");
		matcher = pattern.matcher(data_class.data);
		if (matcher.find()) {
			System.out.println("button:" + matcher.group(1));
		}

	}
}
