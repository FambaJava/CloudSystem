package cloudsystem.model;

public class MinecraftServer {

    private String name;
    private int id;
    private String adress;
    private int port;
    private String type;

    public MinecraftServer(String name, int id, String adress, int port, String type) {
        this.name = name;
        this.id = id;
        this.adress = adress;
        this.port = port;
        this.type = type;
    }

    public String getFullInfo() {
        return name + " - " + id + " | " + adress + ":" + port + " | " + type;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void start(){

    }

    public void stop(){

    }

    public boolean isRunning(){
        return false;
    }
}
