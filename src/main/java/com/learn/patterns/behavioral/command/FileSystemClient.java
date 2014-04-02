package com.learn.patterns.behavioral.command;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 07.11.13
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */

//Command Pattern is one of the Behavioral Design Pattern and it’s used to
// implement lose coupling in a request-response model. In command pattern,
// the request is send to the invoker and invoker pass it to the encapsulated
// command object. Command object passes the request to the appropriate
// method of Receiver to perform the specific action. The client program
// create the receiver object and then attach it to the Command. Then it
// creates the invoker object and attach the command object to perform an
// action. Now when client program executes the action, it’s processed based
// on the command and receiver object.
public class FileSystemClient {

    public static void main(String[] args) {
        //Creating the receiver object
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();

        //creating command and associating with receiver
        OpenFileCommand openFileCommand = new OpenFileCommand(fs);

        //Creating invoker and associating with Command
        FileInvoker file = new FileInvoker(openFileCommand);

        //perform action on invoker object
        file.execute();

        WriteFileCommand writeFileCommand = new WriteFileCommand(fs);
        file = new FileInvoker(writeFileCommand);
        file.execute();

        CloseFileCommand closeFileCommand = new CloseFileCommand(fs);
        file = new FileInvoker(closeFileCommand);
        file.execute();
    }

}
