package cloudsystem.connection.manager;

public class ServerConnections {

    private int port;
    private String host;


    public ServerConnections() {
    }

    private ServerConnections(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public ServerConnections getWrapper() {
        return new ServerConnections(5005, "localhost");
    }

    public ServerConnections getTeamSpeakBot() {
        return new ServerConnections(5005, "localhost");
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }
}
