package SHA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class SHA256 {
	public SHA256() {
		
	}
	public String getSHA256(String input)
	{
		String output="";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes());
			byte [] c=md.digest();
			output=String.format("%064x", new BigInteger(1,c));
		} catch (Exception e) {
			System.out.println("1");
		}
		return output;
	}
}