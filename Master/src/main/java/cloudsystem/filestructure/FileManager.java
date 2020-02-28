package cloudsystem.filestructure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private File wrapperMinecraftServerActiveFolder;
    private File wrapperTeamSpeakServerActiveFolder;
    private File wrapperMinecraftServerInactiveFolder;
    private File wrapperTeamSpeakServerInactiveFolder;

    private File masterConfig;
    private File wrapperConfig;

    private List<File> files;

    public FileManager() {
        files = new ArrayList<>();

        wrapperMinecraftServerActiveFolder = new File("Cloud/Wrapper/active/servers/minecraft");

        files.add(wrapperMinecraftServerActiveFolder);

        wrapperTeamSpeakServerActiveFolder = new File("Cloud/Wrapper/active/servers/teamspeak");

        files.add(wrapperTeamSpeakServerActiveFolder);

        wrapperMinecraftServerInactiveFolder = new File("Cloud/Wrapper/inactive/servers/minecraft");

        files.add(wrapperMinecraftServerInactiveFolder);

        wrapperTeamSpeakServerInactiveFolder = new File("Cloud/Wrapper/inactive/servers/teamspeak");

        files.add(wrapperTeamSpeakServerInactiveFolder);

        masterConfig = new File("Cloud/Master/Configs");
        files.add(masterConfig);
        wrapperConfig = new File("Cloud/Wrapper/Configs");
        files.add(wrapperConfig);
    }

    public void createFilesIfNotExists() {
        System.out.println("FileSystem in loading...");
        System.out.println("Files to load: " + files.size());
        try {
            files.forEach(file -> {
                if (!file.exists()) {
                    System.out.println("File is creating...");
                    file.mkdirs();
                    System.out.println("File " + file.getName() + " is created.");
                }
            });
        } catch (NullPointerException ex) {
            System.out.println("There is a file which is loaded with errors! Reason: " + ex.getMessage());
        }

        System.out.println("All files are loaded.");

    }
}
