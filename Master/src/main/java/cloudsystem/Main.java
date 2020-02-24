package cloudsystem;

import net.mika.cloudsystem.command.CommandManager;
import net.mika.cloudsystem.command.commands.ServerCommand;
import net.mika.cloudsystem.command.commands.TestCommand;
import net.mika.cloudsystem.connection.manager.ConnectionManager;
import net.mika.cloudsystem.connection.manager.ConnectionType;

public class Main {

    private static CommandManager commandManager;

    private static ConnectionManager connectionManager;

    public static void main(String[] args) {

        connectionManager = new ConnectionManager();

        commandManager = new CommandManager();

        commandManager.registerCommand("test", new TestCommand());

        commandManager.registerCommand("server", new ServerCommand());

        connectionManager.init();

        connectionManager.connect(ConnectionType.TeamSpeak, connectionManager.getHost());

    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
