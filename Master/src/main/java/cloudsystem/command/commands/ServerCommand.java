package cloudsystem.command.commands;


import cloudsystem.command.listener.Command;
import cloudsystem.model.TeamSpeakServer;

import java.util.List;

public class ServerCommand implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            if (args[1].equalsIgnoreCase("teamspeak") && args[2].equalsIgnoreCase("list-all")) {
                listAllTeamSpeakServers();
            } else {
                System.out.println("Command: server ts3 list-all");
            }
        } else {
            System.out.println("Command: server ...");
        }
    }


    private void listAllTeamSpeakServers() {
        System.out.println("All teamspeak servers:");
        System.out.println();
        List.of(new TeamSpeakServer("test", 1, "24.235.23.1", 5001, "safh24mfsdij"))
                .forEach(teamSpeakServer -> System.out.println(teamSpeakServer.getFullInfo()));
    }
}
