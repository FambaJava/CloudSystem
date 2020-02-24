package cloudsystem.command.commands;


import cloudsystem.command.listener.Command;
import cloudsystem.datas.AdminKeyHandler;

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
            if(args[1].equalsIgnoreCase("ts3") && args[2].equalsIgnoreCase("list-all")){
                try {
                    adminKey = AdminKeyHandler.getAdminKey();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                System.out.println("Host: " + host);
                System.out.println("Port: " + port);
                System.out.println("Key: " + adminKey);
            }else
                System.out.println("Command: server ts3 list-all");
        }else
            System.out.println("Command: server ...");
    }
}
