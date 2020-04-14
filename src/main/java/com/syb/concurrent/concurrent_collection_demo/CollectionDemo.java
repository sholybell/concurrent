package com.syb.concurrent.concurrent_collection_demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionDemo {

    public static void main(String[] args) {

        List<User> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User(i, "User" + i);
            list.add(user);
        }

        Iterator<User> it = list.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if ("User6".equals(user.getName())) {
                // 模拟了fast-fail场景,其实可以删除的，但是必须使用迭代器的remove，而不是list容器的
                list.remove(user);
            }
        }
    }
}
