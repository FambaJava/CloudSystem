package net.mika.cloudsystem.ts3bot;

import net.mika.cloudsystem.ts3bot.connection.NettyServer;
import net.mika.cloudsystem.ts3bot.manager.TeamspeakManager;

public class Main {


    private static final TeamspeakManager teamSpeakManager = new TeamspeakManager("localhost", 5016);

    private static NettyServer nettyServer = new NettyServer(5003);

    public static void main(String[] args) throws InterruptedException {
        nettyServer.connect();
    }

    public static TeamspeakManager getTeamSpeakManager() {
        return teamSpeakManager;
    }
}
