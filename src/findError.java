package work_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class findError {
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = null;
		String line = "";
		FileReader fr = null;
		
		fr = new FileReader("pill_full_TR1__0.2.csv");
		br = new BufferedReader(fr);
		int index =0;
		while((line=br.readLine())!=null){
			
			String[] arr = line.split(",");
			//if(!arr[arr.length-1].equals("rest")&&!arr[arr.length-1].equals("pull down bottle")&&!arr[arr.length-1].equals("unscrew lid")&&!arr[arr.length-1].equals("dump pills out")&&!arr[arr.length-1].equals("screw lid on")&&!arr[arr.length-1].equals("put bottle away")){
			//	System.out.println(line);
		//	}
			if(arr.length!=60){
				System.out.println(line);
			}
			index++;
			
		}
		
	}

}
