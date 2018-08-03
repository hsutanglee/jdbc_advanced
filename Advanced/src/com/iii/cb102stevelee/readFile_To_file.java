package com.iii.cb102stevelee;
import java.io.*;
public class readFile_To_file {
	public static void main(String[] args) {
		File file_in=new File(args[0]);
		File file_out=new File(args[1]);
		try {
			BufferedReader in=new BufferedReader(new FileReader(file_in));
			PrintWriter out=new PrintWriter(new FileWriter(file_out));
			
			StringBuilder sb = new StringBuilder(256);
			String str;
			while ((str=in.readLine())!=null)
				sb.append(str).append("\n").append("//**");
			System.out.print(sb);
			out.print(sb);
			
			
			in.close();out.close();

	}catch(IOException e) {e.printStackTrace();}

}
}
