package net.mika.cloudsystem.command.commands;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import net.mika.cloudsystem.command.listener.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ServerCommand implements Command {

    private final List<String> server = new ArrayList<>();

    private final TS3Config config = new TS3Config();

    private final TS3Query ts3Query = new TS3Query(config);

    private TS3Api ts3Api = new TS3Api(ts3Query);

    //Datats
    private String host;
    private int port;
    private String adminKey;

    @Override
    public void execute(String[] args) {
        if(args.length != 1){
            if(args[1].equalsIgnoreCase("list")){
                try {
                    setup();
                }catch (NullPointerException ex){
                    ex.printStackTrace();
                }
                System.out.println("Host: " + host);
                System.out.println("Port: " + port);
                System.out.println("Key: " + adminKey);
            }else
                System.out.println("Command: server list");
        }else
            System.out.println("Command: server ...");
    }


    public void setup(){

        //DB get Host
        host = "localhost";
        config.setHost(host);

        //DB get Port
        port = 5013;
        config.setQueryPort(port);

        config.setDebugLevel(Level.ALL);

        adminKey = ts3Api.getPrivilegeKeys().get(0).getToken();

    }

    private void startServer(){

    }
}
