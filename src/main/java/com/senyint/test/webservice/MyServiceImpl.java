package com.senyint.test.webservice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyServiceImpl implements IMyService {

	@Override
	public Integer add(Integer num1, Integer num2) {
		// TODO Auto-generated method stub
		return num1 + num2;
	}

	@Override
	public Integer minus(Integer num1, Integer num2) {
		// TODO Auto-generated method stub
		return num1 - num2;
	}

	@Override
	public String sayHello(String says) {
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
		try {
			while ((temp = bf.readLine()) != null) {
				res += temp;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("接收到的信息为:" + says);
		return res;
	}

	@Override
	public String sayHello2(String says) {
		// TODO Auto-generated method stub
		FileInputStream fin = null;
		try {
			fin = new FileInputStream("src\\main\\java\\com\\senyint\\test\\webservice\\res2.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader bf = new BufferedReader(new InputStreamReader(fin));
		String temp = "";
		String res = "";
		try {
			while ((temp = bf.readLine()) != null) {
				res += temp;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("接收到的信息为:" + says);
		return res;
	}

}
