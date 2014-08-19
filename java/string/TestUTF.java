public class TestUTF{
    public static void main(String[] args) throws Exception{
        String str = "你好";
        byte[] bStr = str.getBytes("GBK");
        System.out.println(new String(bStr,"GBK"));        
    }
}
