package com.learn.patterns.structural.proxy;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class CommandExecutorProxy implements CommandExecutor {

    private boolean isAdmin;
    private CommandExecutor executor;

    public CommandExecutorProxy(String user, String pwd){
        if("dik81".equals(user) && "035645".equals(pwd)) isAdmin=true;
        executor = new CommandExecutorImpl();
    }

    @Override
    public void runCommand(String cmd) throws Exception {
        if(isAdmin){
            executor.runCommand(cmd);
        }else{
                throw new Exception("rm command is not allowed for non-admin users.");
        }
    }

}
