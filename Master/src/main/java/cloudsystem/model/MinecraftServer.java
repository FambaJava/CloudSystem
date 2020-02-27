package cloudsystem.model;

public class MinecraftServer {

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

    public void start() {

    }

    public void stop() {

    }

    public boolean isOnline() {
        return online;
    }
}
