package com.kabita.testing;

import org.springframework.stereotype.Component;

@Component
public class ServiceClient {
    public ServiceClient(){}

    public String fetchMemberName(int userId){
        System.out.println("FETCHING USER NAME...");
        return "kabita";
    }

    public int fetchMemberAge( int userId){
        System.out.println("FETCHING USER AGE...");
        return 29;
    }

    public void updateMemberName(int userId, String name){
        System.out.println("UPDATING USER NAME...");
    }


}
