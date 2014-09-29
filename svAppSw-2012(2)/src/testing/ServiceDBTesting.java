package testing;

import resources.ServiceDB;

public class ServiceDBTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("transfer : "+ServiceDB.transferList("torino", "roma").get(0).toString());
	}

}
