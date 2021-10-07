package racinggame.model;

import racinggame.code.CarValueRange;
import racinggame.code.ErrorCode;
import racinggame.exception.CarException;

public class Car {
	private String name;
	private int forward;
	private int stop;

	public Car(String name, int forward, int stop) {
		this.name = name;
		this.forward = forward;
		this.stop = stop;
	}

	public Car(String name) {
		this.name = name;
		this.forward = 0;
		this.stop = 0;
	}

	public Car(CarBuilder builder) {
		this.name = builder.name;
		this.forward = builder.forward;
		this.stop = builder.stop;
	}

	public String getName() {
		return name;
	}

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	public String getOutputMove() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.forward; i++) {
			sb.append("-");
		}
		return this.name + " : " + sb.toString();
	}

	public static class CarBuilder {
		private String name;
		private int forward;
		private int stop;

		public CarBuilder name(String name) {
			this.name = name;
			return this;
		}

		public CarBuilder forward(int forward) {
			this.forward = forward;
			return this;
		}

		public CarBuilder stop(int stop) {
			this.stop = stop;
			return this;
		}

		public void validCarNameOverRange() {
			if (this.name.length() < CarValueRange.MIN_NAME_LENGTH.getCarValueRange()
				|| this.name.length() > CarValueRange.MAX_NAME_LENGTH.getCarValueRange()) {
				throw new CarException(ErrorCode.INVALID_NAME_RANGE_ERROR.getMessage());
			}
		}

		public Car build() {
			this.validCarNameOverRange();
			return new Car(this);
		}
	}
}
