package com.learn.bases.appacheutils.collectionutils;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 09.04.13
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
class MyList extends ArrayList {
    public MyList(Collection select) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public MyList(){
    }

    public static void each(List list1, Closure closure) {
        CollectionUtils.forAllDo(list1, closure);
    }

    public MyList select(Predicate predicate) {
        return new MyList(CollectionUtils.select(this, predicate));
    }

    // ... and so on ... you get the idea
}