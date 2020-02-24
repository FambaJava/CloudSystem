package net.mika.cloudsystem.ts3bot.manager;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;

import java.util.logging.Level;

public class TeamspeakManager {

    private String host;
    private int port;

    private final TS3Config config = new TS3Config();
    private final TS3Query query = new TS3Query(config);
    private final TS3Api api = new TS3Api(query);

    private Thread thread;

    public TeamspeakManager(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect(){
        thread = new Thread(()->{
            config.setHost(host);
            config.setQueryPort(port);
            config.setDebugLevel(Level.ALL);
        }, "tsBot");
        thread.start();
    }

    public String getAdminKey(int i){
        return api.getPrivilegeKeys().get(i).getToken();
    }

    public void closeConnection() {
        thread.stop();
    }
}
