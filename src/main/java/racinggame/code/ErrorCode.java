package racinggame.code;

public enum ErrorCode {
	INVALID_NAME_RANGE_ERROR("자동차의 이름은 5자 이하만 가능합니다.");

	private String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
