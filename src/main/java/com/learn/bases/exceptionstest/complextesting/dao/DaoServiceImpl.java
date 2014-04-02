package com.learn.bases.exceptionstest.complextesting.dao;

import com.learn.bases.exceptionstest.complextesting.exception.SaveException;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class DaoServiceImpl implements DaoService {
    Logger logger = Logger.getLogger(DaoServiceImpl.class);
    @Override
    public void save(int i) throws SaveException{
        if(i>40){
            throw new SaveException();
        }
        System.out.println("saved");
    }
}
