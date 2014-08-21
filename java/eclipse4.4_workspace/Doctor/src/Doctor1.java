import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Doctor1 {

	public static void main(String[] args){
		System.out.println("please start chat:");
		chat();
	}
	
	static void chat(){
		BufferedReader stdIn  = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = null;
		String reply = null;
		do{
			try {
				str = stdIn.readLine();
				if(str.equals("bye")){
					break;
				}
				
				reply = doResponse(str);
				stdOut.write(reply + "\n");
				stdOut.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}while(true);
		
		return;
	}
	
	static String doResponse(String str){
		String reply = "i don't konw what do you mean.";
		if(str.contains("how are you")){
			reply = "i'm fine";
		}
		if(str.contains("i am") || str.contains("i'm")){
			reply = "me too.";
		}
		if(str.contains("how old")){
			reply = "it's a secrte";
		}
		
		return reply;
	}
	
}
