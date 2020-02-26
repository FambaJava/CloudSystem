package cloudsystem.model;

public class TeamSpeakServer {

    private String name;
    private int id;
    private String ipAddress;
    private int port;
    private String password;

    public TeamSpeakServer(String name, int id, String ipAddress, int port, String password) {
        this.name = name;
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
        this.password = password;
    }

    public String getFullInfo() {
        return name + " - " + id + " | " + ipAddress + ":" + port + " | " + password;
    }

    @Override
    public String toString() {
        return "TeamSpeakServer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                '}';
    }


    public boolean isServerOnline() {
        return false;
    }

    public void start() {

    }

    public void stop() {

    }
}
