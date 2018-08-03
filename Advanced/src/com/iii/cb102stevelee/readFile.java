package com.iii.cb102stevelee;
import java.io.*;
public class readFile {
	public static void main(String[] args) {
		File file=new File(args[0]);
		try {
			BufferedReader in=new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder(256);
			String str;
			while ((str=in.readLine())!=null)
				sb.append(str).append("\n").append("//**");
			System.out.print(sb);
			in.close();

	}catch(IOException e) {e.printStackTrace();}

}
}
