package kr.or.ksmart37.api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReadTest {

	public static void main(String[] args) {
		File f = new File("d://filereadtest.txt");
		System.out.println(f.toString());
		System.out.println(f.isFile());
		try {
			Path p = Paths.get("d://filereadtest.txt");
			BufferedReader br = Files.newBufferedReader(p);
			String temp;
			String text ="";
			while((temp = br.readLine()) != null ) {
				text += temp +"\n";
			}
			System.out.println(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
