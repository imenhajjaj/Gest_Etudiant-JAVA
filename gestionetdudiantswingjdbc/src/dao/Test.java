package dao;

import java.sql.Connection;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection cx1=SingletonConnection.getInstance();
		Connection cx2=SingletonConnection.getInstance();
		Connection cx3=SingletonConnection.getInstance();
		System.out.println(cx1+"\n"+cx2+"\n"+cx3);
		

	}

}
