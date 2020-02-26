package cloudsystem.command.commands;


import cloudsystem.Main;
import cloudsystem.command.listener.Command;

import java.sql.SQLException;

public class ServerListCommand implements Command {

    @Override
    public void execute(String[] args) throws SQLException {
        listAllTeamSpeakServers();
    }


    private void listAllTeamSpeakServers() throws SQLException {
        System.out.println("All TeamSpeak server...");
        System.out.println();
        System.out.println("NAME - ID | IP-Adress:Port | Password");
        System.out.println();
        Main.getSqlManager().getAllTeamSpeakServers().forEach(teamSpeakServer -> {
            System.out.println(teamSpeakServer.getFullInfo());
        });
    }


}
