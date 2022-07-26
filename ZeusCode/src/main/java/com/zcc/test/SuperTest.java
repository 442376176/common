package com.zcc.test;

import com.zcc.entity.User;
import org.junit.Test;

import java.util.*;

public class SuperTest extends Date {
    private static final long serialVersionUID = 1L;
    private void test(){
        System.out.println(super.getClass().getName());
    }

    public static void main(String[]args){
        List<String> listA = new ArrayList<String>();
        List<String> listB = null;
        listA.add("A");
        listA.add("B");
//        listB.add("d");
//        listB.add("C");
        listA.addAll(listB);
        System.out.println(listA);
    }

    @Test
    public void a(){
        User user = new User(1,"z");
        User user1 = new User(1,"z");
        Set<User> set = new HashSet<>();
        set.add(user1);
        set.add(user);
        System.out.println(set);
    }
}
    