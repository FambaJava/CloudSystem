package net.mika.cloudsystem.command.commands;

import net.mika.cloudsystem.command.listener.Command;
import net.mika.cloudsystem.datas.AdminKeyHandler;

import java.util.ArrayList;
import java.util.List;

public class ServerCommand implements Command {

    private final List<String> server = new ArrayList<>();


    //Datats
    private String host;
    private int port;
    private String adminKey;

    @Override
    public void execute(String[] args) throws InterruptedException {
        if(args.length != 1){
            if(args[1].equalsIgnoreCase("list")){
                try {
                    adminKey = AdminKeyHandler.getAdminKey();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                System.out.println("Host: " + host);
                System.out.println("Port: " + port);
                System.out.println("Key: " + adminKey);
            }else
                System.out.println("Command: server list");
        }else
            System.out.println("Command: server ...");
    }
}
