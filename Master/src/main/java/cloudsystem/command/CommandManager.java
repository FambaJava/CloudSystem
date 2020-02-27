package cloudsystem.command;


import cloudsystem.command.commands.*;
import cloudsystem.command.listener.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private Map<String, Command> commands;

    private Thread thread;

    private boolean running;

    private static BufferedReader reader;

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
        } catch (NullPointerException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException ex) {
            return false;
        }
    }

    public void start() throws IOException {
        commands = new HashMap<>();
        reader = new BufferedReader(new InputStreamReader(System.in));
        reader.mark(1000);
        registerCommands();
        running = true;
        thread = new Thread(() -> {
            while (running) {
                try {
                    line = reader.readLine();
                    if (!executeCommand(line.split(" ")))
                        System.out.println("This command does not exist!");
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
        registerCommand("restart", new RestartCommand());
        registerCommand("reload", new RestartCommand());
        registerCommand("rl", new RestartCommand());
        registerCommand("server", new ServerCommand());
    }

    private void unRegisterCommands() {
        commands.clear();
    }

    public void stop() throws IOException {
        running = false;
        unRegisterCommands();
        reader.reset();
    }
}
