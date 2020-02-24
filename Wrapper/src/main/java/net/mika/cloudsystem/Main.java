package net.mika.cloudsystem;

import net.mika.cloudsystem.command.CommandManager;
import net.mika.cloudsystem.command.commands.ServerCommand;
import net.mika.cloudsystem.command.commands.TestCommand;

public class Main {

    private static CommandManager commandManager;

    public static void main(String[] args) {

        commandManager = new CommandManager();

        commandManager.registerCommand("test", new TestCommand());

        commandManager.registerCommand("server", new ServerCommand());
        //s
        commandManager.start();

    }
}
