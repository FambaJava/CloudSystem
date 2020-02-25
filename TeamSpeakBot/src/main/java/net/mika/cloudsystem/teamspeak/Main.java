package net.mika.cloudsystem.teamspeak;

import net.mika.cloudsystem.teamspeak.connection.NettyServer;
import net.mika.cloudsystem.teamspeak.manager.TeamspeakManager;

public class Main {


    private static final TeamspeakManager teamSpeakManager = new TeamspeakManager("127.0.0.1", 5003);

    private static NettyServer nettyServer = new NettyServer(5005);

    public static void main(String[] args) throws InterruptedException {
        nettyServer.connect();
    }

    public static TeamspeakManager getTeamSpeakManager() {
        return teamSpeakManager;
    }
}
