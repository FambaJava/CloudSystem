package cloudsystem.command;


import cloudsystem.command.commands.ServerListCommand;
import cloudsystem.command.commands.StopCommand;
import cloudsystem.command.commands.TestCommand;
import cloudsystem.command.listener.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private Map<String, Command> commands = new HashMap<String, Command>();

    private Thread thread;

    private boolean running;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private String line;

    private void registerCommand(String name, Command commandClazz) {
        commands.put(name, commandClazz);
    }

    private Command getCommand(String name) {
        return commands.get(name);
    }

    private boolean executeCommand(String[] args) throws SQLException, InterruptedException {
        try {
            getCommand(args[0]).execute(args);
            return true;
        } catch (NullPointerException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException ex) {
            return false;
        }
    }

    public void start() {
        registerCommands();
        running = true;
        thread = new Thread(() -> {
            while (running) {
                try {
                    line = reader.readLine();
                    if (!executeCommand(line.split(" ")))
                        System.out.println("Das Kommando existiert nicht!");
                } catch (IOException | SQLException | InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }

        }, "commandTool");
        thread.start();
    }


    private void registerCommands() {
        registerCommand("test", new TestCommand());
        registerCommand("las", new ServerListCommand());
        registerCommand("listallserver", new ServerListCommand());
        registerCommand("stop", new StopCommand());
    }

    public void stop() {
        thread.stop();
    }
}
