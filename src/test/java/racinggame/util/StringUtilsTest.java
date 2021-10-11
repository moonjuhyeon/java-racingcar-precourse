package racinggame.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringUtilsTest {

	@ParameterizedTest(name = "{index} ==> input {0}, size {1}")
	@CsvSource(value = {
		"'sds: ', 1",
		"'ssd, dww : , ', 2",

	})
	void convertInputStringToArray(String input, int size) {
		// given // when
		String[] stringArray = StringUtils.convertInputToStringArray(input);
		// then
		assertThat(stringArray).hasSize(size);
	}
}