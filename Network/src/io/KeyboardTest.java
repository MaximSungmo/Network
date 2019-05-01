package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class KeyboardTest {
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			//기반스트림(표준입력, 키보드, System.in)
			
			//보조스트림1
			// byte|byte|byte -> char
			InputStreamReader isr = new InputStreamReader(System.in, "utf-8");

			//보조스트림2
			// char1|char2|char3|\n -> "char1char2char3"
			br = new BufferedReader(isr);
			
			//read
			String line = null;
			while((line = br.readLine()) != null) {
				if("exit".equals(line)) {
					break;
				}
				System.out.println(">>" + line);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
