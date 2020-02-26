package cloudsystem.filestructure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private File wrapperServerActiveFolder;
    private File wrapperServerInactiveFolder;

    private File masterConfig;
    private File wrapperConfig;

    private List<File> files;

    public FileManager() {
        files = new ArrayList<>();

        wrapperServerActiveFolder = new File("Cloud/Wrapper/active/servers");
        files.add(wrapperServerActiveFolder);
        wrapperServerInactiveFolder = new File("Cloud/Wrapper/inactive/servers");
        files.add(wrapperServerInactiveFolder);

        masterConfig = new File("Cloud/Master/Configs");
        files.add(masterConfig);
        wrapperConfig = new File("Cloud/Wrapper/Configs");
        files.add(wrapperConfig);
    }

    public void createFilesIfNotExists(){
        System.out.println("FileSystem in loading...");
        System.out.println("Files to load: " + files.size());
        try {
            files.forEach(file ->{
                if(!file.exists()){
                    System.out.println("File is creating...");
                    file.mkdirs();
                    System.out.println("File " + file.getName() + " is created.");
                }
            });
        }catch (NullPointerException ex){
            System.out.println("There is a file which is loaded with errors! Reason: " + ex.getMessage());
        }

        System.out.println("All files are loaded.");

    }
}
