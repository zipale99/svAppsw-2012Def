package testing;

import java.awt.List;
import java.util.ArrayList;

import resources.ServiceDB;

public class ServiceDBTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("transfer : "+ServiceDB.transferList("torino", "roma").get(0).toString());
	
		ArrayList<Integer> ar1 = new ArrayList<Integer>();
		ar1.add(23);
		ar1.add(45);
		ar1.add(984);
		
		ArrayList<Integer> ar2 = new ArrayList<Integer>();
		ar2.add(77);
		ar2.add(245);
		ar2.add(876);
		
		ar1.addAll(ar2);
		//int i =0;
		for(Integer a : ar1)
			System.out.println(a);		
		
	}
}