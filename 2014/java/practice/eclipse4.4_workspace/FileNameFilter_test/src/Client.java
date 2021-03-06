import java.io.File;
import java.io.FilenameFilter;

public class Client {
	public static void main(String[] args){
		//E:\mycode\java\eclipse4.4_workspace\FileNameFilter_test\src
		File dir = new File(".");
//		String[] files = dir.list();
//		for(String name : files){
//			System.out.println(name);
//		}
		
		String[] javaFiles = dir.list(new JavaFile(".java"));
		for(String name : javaFiles){
			System.out.println(name);
		}
	}
	
	private static class JavaFile implements FilenameFilter{
		private String type;
		
		public JavaFile(String _type){
			type = _type;
		}

		@Override
		public boolean accept(File dir, String name) {
//			System.out.println(dir);
//			System.out.println(name);
//			System.out.println("--------------------------");
			
			if(name.endsWith(type)){
				return true;
			}
			return false;
		}
		
	}
}
