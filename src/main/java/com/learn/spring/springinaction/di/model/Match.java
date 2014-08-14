package com.learn.spring.springinaction.di.model;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Date;
import java.util.Set;

/**
 * Created by dmitry on 01.05.14.
 */
public class Match implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
        InitializingBean{
    private Date date;
    private int watchersNumber;
    private int additionalSeconds;

   @Resource(name = "teams")
    private Set<Team> teams;

    public Match(Date date, int watchersNumber, int additionalSeconds, Set<Team> teams) {
        this.date = date;
        this.watchersNumber = watchersNumber;
        this.additionalSeconds = additionalSeconds;
        this.teams = teams;
    }

    public Match() {

    }

   public Match(Date date,int watchersNumber, int additionalSeconds)
   {
      this.date = date;
      this.watchersNumber = watchersNumber;
      this.additionalSeconds = additionalSeconds;
   }

   public static Match getInstance() {
        return new Match();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWatchersNumber() {
        return watchersNumber;
    }

    public void setWatchersNumber(int watchersNumber) {
        this.watchersNumber = watchersNumber;
    }

    public int getAdditionalSeconds() {
        return additionalSeconds;
    }

    public void setAdditionalSeconds(int additionalSeconds) {
        this.additionalSeconds = additionalSeconds;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public void referyStarting(){
        System.out.println("Refery is starting the game!");
    }

    public void referyStopping(){
        System.out.println("Refery is stopping the game!");
    }

    private String beanName;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("properties are set successfully!!!!!!!!!");
    }

   @PostConstruct
   public void postConstructorRan(){
      System.out.println("Post constructorrrrr");
   }

}
