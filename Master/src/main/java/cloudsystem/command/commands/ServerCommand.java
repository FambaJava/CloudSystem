package cloudsystem.command.commands;

import cloudsystem.command.listener.Command;


public class ServerCommand implements Command {

    //server start type id
    @Override
    public void execute(String[] args){
        switch (args.length){
            case 1:
                System.out.println("args1");
                break;
            case 2:
                System.out.println("args2");
                break;
            case 4:
                if(args[1].equalsIgnoreCase("start")){
                    String type = args[2];
                    int id = Integer.parseInt(args[3]);
                    if(type.equalsIgnoreCase("MINECRAFT") || !type.equalsIgnoreCase("TEAMSPEAK")){
                        System.out.println("Starte " + type + " server with id: " + id);
                    }else{
                        System.out.println("Your Type need to be <MINECRAFT> or <TEAMSPEAK>");
                    }
                }
            default: break;
        }
    }
}
