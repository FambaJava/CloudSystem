package cloudsystem.model.server;

import cloudsystem.Main;

import java.io.IOException;
import java.net.Socket;

public class CloudServer {

    private String minecraftServerPath;
    private String teamsSpealServerPath;

    String operatingSystem = System.getProperty("os.name");

    private Runtime runtime;

    public CloudServer() {
        runtime = Runtime.getRuntime();
        minecraftServerPath = "Cloud/Wrapper/inactive/servers/minecraft";
        teamsSpealServerPath = "Cloud/Wrapper/inactive/servers/teamspeak";

    }

    public void startServer(String type, int id, int amount, int maxRam) throws Exception {
        for (int i = 0; i < amount; i++) {
            int port = findFreePort(Main.getSqlManager().getMinecraftServerPerID(id).getAdress());
            switch (type) {
                case "MINECRAFT":
                    System.out.println("Server is starting...");
                    runtime.exec("cd " + minecraftServerPath + "/" + Main.getSqlManager().getMinecraftServerPerID(id).getName());
                    runtime.exec("java -Xmx" + maxRam + "M -jar spigot.jar");
                    Main.getSqlManager().setMinecraftStatus(id, true);
                    System.out.println("Server is started...");
                    break;
                case "TEAMSPEAK":
                    startTeamSpeakOnOperatingSystem();
                    break;
                default:
                    break;
            }
        }
    }

    private void startTeamSpeakOnOperatingSystem() throws IOException {
        if (isiUnix()) {
            startTeamSpeakServerOnUnix();
        } else if (isMac()) {
            startTeamSpeakServerOnMac();
        } else if (isWindows()) {
            startTeamSpeakServerOnWindows();
        } else {
            System.out.println("Your operating system is not known us." +
                    " \nPleas write us at Twitter (@CloudWinner) with your " +
                    "operating system and the following id: " + operatingSystem);
        }
    }

    private boolean isiUnix() {
        return (operatingSystem.contains("nix") || operatingSystem.contains("nux") || operatingSystem.contains("aix"));
    }

    private boolean isMac() {
        return (operatingSystem.contains("mac"));
    }

    private boolean isWindows() {
        return (operatingSystem.contains("win"));
    }

    private void startTeamSpeakServerOnMac() throws IOException {
        System.out.println("Server is starting...");
        runtime.exec("cd " + teamsSpealServerPath);
        runtime.exec("/.ts3server");
        System.out.println("Server is started...");
    }

    private void startTeamSpeakServerOnWindows() throws IOException {
        System.out.println("Server is starting...");
        runtime.exec("cd " + teamsSpealServerPath);
        runtime.exec("START C:\\Windows\\ts3server.exe");
        System.out.println("Server is started...");
    }

    private void startTeamSpeakServerOnUnix() throws IOException {
        System.out.println("Server is starting...");
        runtime.exec("cd " + teamsSpealServerPath);
        runtime.exec("systemctl enable teamspeak.service");
        runtime.exec("systemctl start teamspeak.service");
        System.out.println("Server is started...");
    }

    private static int findFreePort(String address) throws IOException {
        Socket socket = null;
        int port = 1;
        try {
            socket = new Socket(address, 0);
            socket.setReuseAddress(true);
            port = socket.getLocalPort();
            socket.close();
            return port;
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (port == 1) {
                System.out.println("Could not find a free port where your server can start.");
            }
        }
    }

}
