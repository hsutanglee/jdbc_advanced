package com.iii.cb102stevelee;
import java.io.*;
public class writeFile {
	public static void main(String[] args) {
		File file=new File(args[0]);
		String str;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out=new PrintWriter(new FileWriter(file));
			StringBuilder sb = new StringBuilder(256);
			
			while (!(str=in.readLine()).equals(""))
				sb.append(str).append("\n");
			out.print(sb);
			in.close();out.close();

	}catch(IOException e) {e.printStackTrace();}
	}

}
