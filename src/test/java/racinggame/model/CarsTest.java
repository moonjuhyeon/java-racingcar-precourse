package racinggame.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import racinggame.code.ErrorCode;
import racinggame.exception.CarException;

@DisplayName("Cars 테스트")
public class CarsTest {

	@InjectMocks
	private static Cars.CarsBuilder carsBuilder;

	@BeforeEach
	void setup() {
		carsBuilder = new Cars.CarsBuilder();
	}

	@DisplayName("Cars Builder 생성 테스트")
	@Test
	void createBuilderTest() {
		// given // when
		Cars cars = new Cars.CarsBuilder().build();
		// then
		assertAll(
			() -> assertThat(cars).isInstanceOf(Cars.class),
			() -> assertThat(cars.getCarList()).isInstanceOf(List.class)
		);
	}

	@DisplayName("Cars 자동차 이름 input 예외 처리 테스트")
	@Test
	void exceptionCarListInputName() {
		// given // when
		String input = "car1,car2,eweqwe";
		//then
		assertThatThrownBy(() -> {
			carsBuilder.convertInputToCarList(input);
		}).isInstanceOf(CarException.class)
			.hasMessageContaining(new CarException(ErrorCode.INVALID_NAME_RANGE_ERROR.getMessage()).getMessage());
	}

	@DisplayName("Cars 자동차 이름으로 Cars 생성 테스트")
	@Test
	void createCarListInputName() {
		// given
		String input = "car1,car2,car3";
		// when
		List<Car> carList = carsBuilder.convertInputToCarList(input);
		// then
		assertAll(
			() -> assertThat(carList).isInstanceOf(List.class),
			() -> assertThat(carList.size()).isEqualTo(3),
			() -> assertThat(carList).isNotEmpty(),
			() -> assertThat(carList.get(0).getName()).isEqualTo("car1"),
			() -> assertThat(carList.get(1).getName()).isEqualTo("car2"),
			() -> assertThat(carList.get(2).getName()).isEqualTo("car3"),
			() -> assertThat(carList.get(0).getForward()).isZero(),
			() -> assertThat(carList.get(0).getStop()).isZero()
		);
	}
}