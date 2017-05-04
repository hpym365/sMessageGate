package com.senyint.test.webservice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Testtxt {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fin = null;
		try {
			fin = new FileInputStream("src\\main\\java\\com\\senyint\\test\\webservice\\res.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(fin));
		String temp = "";
		String res = "";
		while((temp=bf.readLine())!=null){
			res+=temp;
		}
		System.out.println(res);
	}

}
