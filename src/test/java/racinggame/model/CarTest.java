package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import racinggame.code.ErrorCode;
import racinggame.exception.CarException;

@DisplayName("Car 테스트")
public class CarTest {

	@DisplayName("Car Java Bean 생성 테스트")
	@Test
	void createJavabeanTest() {
		// given
		String name = "name";
		int forward = 5;
		int stop = 5;
		// when
		Car car = new Car(name, forward, stop);
		// then
		assertAll(
			() -> assertThat(car).isInstanceOf(Car.class),
			() -> assertThat(car.getForward()).isEqualTo(forward),
			() -> assertThat(car.getStop()).isEqualTo(stop),
			() -> assertThat(car.getName()).isEqualTo(name)
		);
	}

	@DisplayName("Car Builder 생성 테스트")
	@Test
	void createBuilderTest() {
		// given
		String name = "name";
		int forward = 5;
		int stop = 0;
		// when
		Car car = new Car.CarBuilder()
			.name(name)
			.forward(forward)
			.stop(stop)
			.build();
		// then
		assertAll(
			() -> assertThat(car).isInstanceOf(Car.class),
			() -> assertThat(car.getName()).isEqualTo(name),
			() -> assertThat(car.getForward()).isEqualTo(forward),
			() -> assertThat(car.getStop()).isEqualTo(stop)
		);
	}

	@DisplayName("Car name 최대길이 5자 제한 예외처리 테스트")
	@Test
	void exceptionOverNameRangeTest() {
		// given
		String name = "nameOver";
		assertThatThrownBy(
			() -> {
				// when
				new Car.CarBuilder().name(name).build();
			}
			// then
		).isInstanceOf(CarException.class)
			.hasMessageContaining(ErrorCode.INVALID_NAME_RANGE_ERROR.getMessage());
	}

	@DisplayName("Car name 최대길이 5자 제한 경계값 테스트")
	@ParameterizedTest(name = "{index} ==> name {0}")
	@ValueSource(strings = {"1", "", "ekek", "asvsdw"})
	void createCarNameLengthTest(String name) {
		try {
			// given // when
			Car car = new Car.CarBuilder().name(name).build();
			assertThat(car.getName()).isEqualTo(name);
		} catch (Throwable throwable) {
			// then
			assertThat(throwable).isInstanceOf(CarException.class);
			assertThat(throwable).hasMessageContaining(ErrorCode.INVALID_NAME_RANGE_ERROR.getMessage());
		}

	}

	@DisplayName("Car getOutputMove() 함수 테스트")
	@ParameterizedTest(name = "{index} ==> name {0}, forward {1}, wantResult {2}")
	@CsvSource(value = {
		"tusan, 5, 'tusan : -----'",
		"ace, 3, 'ace : ---'",
		"benz, 0, 'benz : '"
	})
	void createOutputMoveStringTest(String name, int forward, String wantResult) {
		// given
		Car car = new Car.CarBuilder().name(name).forward(forward).build();
		// when
		String outputMoveString = car.getOutputMove();
		// then
		assertThat(outputMoveString).isEqualTo(wantResult);
	}
}