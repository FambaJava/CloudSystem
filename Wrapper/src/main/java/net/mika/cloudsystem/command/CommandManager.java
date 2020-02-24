package net.mika.cloudsystem.command;

import net.mika.cloudsystem.command.listener.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private Map<String, Command> commands = new HashMap<String, Command>();

    private Thread thread;

    private boolean running;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private String line;

    public boolean registerCommand(String name, Command commandClazz){
        try {
            commands.put(name, commandClazz);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    private Command getCommand(String name){
        return commands.get(name);
    }

    private boolean executeCommand(String[] args){
        try {
            getCommand(args[0]).execute(args);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public void start(){
        running = true;
        new Thread(()->{
                    while (running){
                        try {
                            line = reader.readLine();
                            if(!executeCommand(line.split(" ")))
                                System.out.println("Das Kommando existiert nicht!");
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                }, "commandTool").start();
    }
}
