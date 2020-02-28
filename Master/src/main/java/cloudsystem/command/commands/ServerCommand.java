package cloudsystem.command.commands;

import cloudsystem.Main;
import cloudsystem.command.listener.Command;

import java.io.IOException;
import java.sql.SQLException;


public class ServerCommand implements Command {

    //server create TYPE NAME HOST
    @Override
    public void execute(String[] args) throws SQLException {
        switch (args.length){
            case 1:
                System.out.println("args1");
                break;
            case 2:
                System.out.println("args2");
                break;
            case 4:
                if(args[1].equalsIgnoreCase("start")){
                    String type = args[2];
                    int id = Integer.parseInt(args[3]);
                    startServer(type, id);
                }
            case 5:
                if(args[1].equalsIgnoreCase("create")){
                    String type = args[2];
                    String name = args[3];
                    String host = args[4];
                    if(type.equalsIgnoreCase("teamspeak")){
                        Main.getSqlManager().setUpTeamSpeak(name, host, 3000, "youNeedToStartTheTeamSpeak");
                    }else{
                        System.out.println("Try server create minecraft NAME TYPE minRam");
                    }
                }
            case 7:
                if(args[1].equalsIgnoreCase("create")) {
                    String result = args[2];
                    String type = args[5];
                    String name = args[3];
                    String host = args[4];
                    String maxRam = args[6];
                    int mr = 0;
                    try{
                        mr = Integer.parseInt(maxRam);
                    }catch (NumberFormatException ex){
                        System.out.println("You maxRam have to be a Number!");
                    }
                    if (result.equalsIgnoreCase("minecraft")) {
                        Main.getSqlManager().setUpMinecraft(name, host, 3000, type, mr);
                    } else {
                        System.out.println("Try server create teamspeak NAME HOST");
                    }
                }

            default: break;
        }
    }

    private void startServer(String type, int id){
        if(type.equalsIgnoreCase("MINECRAFT") || type.equalsIgnoreCase("TEAMSPEAK")){
            System.out.println(type + " server is starting with id: " + id);
            try {
                switch (type){
                    case "MINECRAFT":
                        Main.getSqlManager().getMinecraftServerPerID(id).start(1);
                        break;
                    case "TEAMSPEAK":
                        Main.getSqlManager().getTeamSpeakServerPerID(id).start(1);
                        break;
                    default:
                        break;
                }
            }catch (SQLException | IOException ex){
                System.out.println("Can not find " + type + " with id: " + id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Your Type need to be <MINECRAFT> or <TEAMSPEAK>");
        }
    }
}
