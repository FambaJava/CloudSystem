package cloudsystem.command.commands;


import cloudsystem.Main;
import cloudsystem.command.listener.Command;

import java.sql.SQLException;
import java.util.List;

public class ServerCommand implements Command {
    
    @Override
    public void execute(String[] args){
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("TeamSpeak") || args[1].equalsIgnoreCase("ts")) {
                listAllTeamSpeakServers();
            } else {
                System.out.println("Command: las || listallserver");
            }
        } else if (args.length == 1) {
            listAllTeamSpeakServers();
        } else {
            System.out.println("Command: server ...");
        }
    }


    private void listAllTeamSpeakServers() {
        System.out.println("All TeamSpeak server...");
        System.out.println();
        try {
            List.of(Main.getSqlManager().getTeamSpeakServer(1), Main.getSqlManager().getTeamSpeakServer(2))
                    .forEach(teamSpeakServer -> System.out.println(teamSpeakServer.getFullInfo()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
