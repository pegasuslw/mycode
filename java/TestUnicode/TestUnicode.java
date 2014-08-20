public class TestUnicode{
	public static void main(String[] args) throws Exception{
		String str = "ÄãºÃ";
		byte[] b = str.getBytes("UTF8");

		System.out.println(new String(b));
		System.out.println(new String(b, "UTF8"));
	}
}