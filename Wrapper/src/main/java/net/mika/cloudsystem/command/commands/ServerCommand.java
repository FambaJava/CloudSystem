package net.mika.cloudsystem.command.commands;

import net.mika.cloudsystem.command.listener.Command;

import java.util.ArrayList;
import java.util.List;

public class ServerCommand implements Command {

    private List<String> server = new ArrayList<>();
    @Override
    public void execute(String[] args) {
        if(args.length != 1){
            if(args[1].equalsIgnoreCase("list")){
                String host;
                int port;
            }else
                System.out.println("Command: server list");
        }else
            System.out.println("Command: server ...");
    }
}
