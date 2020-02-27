package cloudsystem.command.commands;


import cloudsystem.Main;
import cloudsystem.command.listener.Command;

import java.sql.SQLException;

public class ServerListCommand implements Command {

    @Override
    public void execute(String[] args) throws SQLException {
        System.out.println("All servers:");
        listAllTeamSpeakServers();
        listAllMinecraftServers();
    }


    private void listAllTeamSpeakServers() throws SQLException {
        System.out.println();
        System.out.println("NAME - ID | IP-Adress:Port | Password | Online");
        System.out.println();
        Main.getSqlManager().getAllTeamSpeakServers().forEach(teamSpeakServer -> {
            System.out.println(teamSpeakServer.getFullInfo());
        });
    }

    private void listAllMinecraftServers() throws SQLException {
        System.out.println();
        System.out.println("NAME - ID | IP-Adress:Port | Type | Online");
        System.out.println();
        Main.getSqlManager().getAllMinecraftServers().forEach(minecraftServer -> {
            System.out.println(minecraftServer.getFullInfo());
        });
    }


}
