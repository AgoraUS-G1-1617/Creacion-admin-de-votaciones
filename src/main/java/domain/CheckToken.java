package domain;

public class CheckToken {
	private Boolean valid;
	private static final Integer[] tokenAuxList = {120338, 127508, 219240, 231958, 
		264907, 301200, 301415, 318851, 328237, 333555, 366710, 376217, 382413, 
		406463, 409921, 436780, 458841, 461513, 530897, 589116, 590265, 590815, 
		593252, 656720, 746976, 830375, 865247, 869061, 885540, 907197, 909246, 
		961864, 976931, 982612};
private static Integer calculateToken(Integer votationId){
		
		Integer token = 0;
		
		checkId(votationId);
		
		String binaryInteger = Integer.toBinaryString(votationId);
		char[] numberByNumber = binaryInteger.toCharArray();
		
		int j = 0;
		for(int i=numberByNumber.length-1; 0 <= i; i--){
			String binDigit = Character.toString(numberByNumber[i]);
			Integer digit = new Integer(binDigit);
			if(digit > 0){
				token += digit*tokenAuxList[tokenAuxList.length-1-j];
				
			}
			j++;
		}
		
		return token*17;
		
	}

	private static void checkId(Integer votationId) {
		assert votationId <= 999999998;

	}
	public Boolean getValid() {
		return valid;
	}
	
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
}
