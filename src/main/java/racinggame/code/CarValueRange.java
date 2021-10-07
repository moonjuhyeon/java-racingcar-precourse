package racinggame.code;

public enum CarValueRange {
	MIN_NAME_LENGTH(1),
	MAX_NAME_LENGTH(5);

	private int carValueRange;

	CarValueRange(int carValueRange) {
		this.carValueRange = carValueRange;
	}

	public int getCarValueRange() {
		return carValueRange;
	}
}
