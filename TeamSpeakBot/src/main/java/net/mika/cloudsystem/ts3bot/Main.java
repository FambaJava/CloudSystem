package net.mika.cloudsystem.ts3bot;

import net.mika.cloudsystem.ts3bot.manager.TeamspeakManager;

public class Main {


    private static final TeamspeakManager teamSpeakManager = new TeamspeakManager("localhost", 5016);

    public static void main(String[] args) {
        teamSpeakManager.connect();
    }

    public static TeamspeakManager getTeamSpeakManager() {
        return teamSpeakManager;
    }
}
