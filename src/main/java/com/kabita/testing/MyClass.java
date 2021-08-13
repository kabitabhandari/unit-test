package com.kabita.testing;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyClass {
    @Autowired
    private final ServiceClient serviceClient;

    public MyClass(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public String getMemberName(int id){
        if(id <= 0){
            throw new RuntimeException();
        }
        return serviceClient.fetchMemberName(id);
    }

    public void updateMemberName(int id, String name){
        if(id <= 0 || name.trim().length() < 1){
            throw new RuntimeException();
        }
         serviceClient.updateMemberName(id, name);
    }

    public List<String> getMemberNames(int... ids){
        List<String> listOfMemberNames = Arrays.stream(ids).mapToObj(this::getMemberName).collect(Collectors.toList());
        List<String> list2 = Arrays.stream(ids).mapToObj(el->this.getMemberName(el)).collect(Collectors.toList());
        return listOfMemberNames;
    }

}
