package twitterImp;

public class ReplaceString {
	public String replaceSpa(String str){
	StringBuffer strBuffer = new StringBuffer();
	for (int i = 0; i < str.length(); i++) {
		if (str.charAt(i) == ' ') {
			strBuffer.append("%20");
		}else {
			strBuffer.append(str.charAt(i));
		}
	}
	return strBuffer.toString();
	}
}