/**
 * Created by Anthony on 9/9/2014.
 */
public class HashTest {
	private static int hash(String str) {
		int ret = 27;
		for(int i=0;i<str.length();i++) {
			ret = ret*7 * str.charAt(i);
		}
		return ret;
	}
	public static void main(String[] args) {
		System.out.println(hash("Anthony"));
		System.out.println(hash("Anhtony"));
		System.out.println(hash("Brittany"));
		System.out.println(hash("Yi"));
		System.out.println(hash("Darrius"));
		System.out.println(hash("Ansel"));
		System.out.println(hash("Brian"));
		System.out.println(hash("Bastien"));
		System.out.println(hash("Dale"));
		System.out.println(hash("Bob"));
		System.out.println(hash("ansel"));
		System.out.println(hash("tyler"));
		System.out.println(hash("bob"));
		System.out.println(hash("josh"));
		System.out.println(hash("claire"));
		System.out.println(hash("brian"));
	}
}
