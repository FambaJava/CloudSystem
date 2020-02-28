package cloudsystem.model;

import cloudsystem.model.server.CloudServer;

public class MinecraftServer extends CloudServer {

    private String name;
    private int id;
    private String adress;
    private int port;
    private String type;
    private int maxRam;
    private boolean online;

    public MinecraftServer(String name, int id, String adress, int port, String type, int maxRam, boolean online) {
        this.name = name;
        this.id = id;
        this.adress = adress;
        this.port = port;
        this.type = type;
        this.maxRam = maxRam;
        this.online = online;
    }

    public String getFullInfo() {
        return "MINECRAFT: " + name + " - " + id + " | " + adress + ":" + port + " | " + type + " | " + maxRam + " | " + online;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void start(int amount) throws Exception {
        if (isOnline()) {
            System.out.println("The Server " + name + " (id= " + id + ") is already online!");
        } else {
            //TODO START THE SERVER
            this.startServer( "MINECRAFT", id, amount, maxRam);
        }
    }

    public void stop() {
        if(isOnline()){
            //TODO STOP THE SERVER
        }else{
            System.out.println("The Server " + name + " (id= " + id + ") is currently offline!");
        }
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public boolean isOnline() {
        return online;
    }
}
