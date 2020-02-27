package cloudsystem.command.listener;

import java.io.IOException;
import java.sql.SQLException;

public interface Command {

    void execute(String[] args) throws InterruptedException, SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException;
}
