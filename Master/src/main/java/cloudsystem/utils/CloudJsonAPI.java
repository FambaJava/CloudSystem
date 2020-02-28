package cloudsystem.utils;

import cloudsystem.setup.SetUpManager;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;


public class CloudJsonAPI {

    private BufferedReader bufferedReader;
    private StringBuilder stringBuilder;
    private JSONObject jsonObject;
    private JSONObject json;
    private FileWriter fileWriter;
    private File file;
    private static SetUpManager setUpManager = new SetUpManager();


    public void init() throws IOException, SQLException, JSONException {
        file = new File("Cloud/Master/Configs/Config.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        fileWriter = new FileWriter(file.getAbsoluteFile());
        stringBuilder = new StringBuilder();
        jsonObject = parseJSONFile();
        json = new JSONObject();
    }

    private String read() throws IOException {
        bufferedReader = new BufferedReader(new FileReader(file));
        System.out.println("a");
        int counter;
        while ((counter = bufferedReader.read()) != -1) {
            stringBuilder.append((char) counter);
            System.out.println("b");
        }
        System.out.println("c");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public void end() {
        try {
            json.put("finished", true);
            fileWriter = new FileWriter("Cloud/Master/Configs/Config.json");
            json.write(fileWriter);
            fileWriter.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUser() throws JSONException {
        return jsonObject.getString("username");
    }

    public String getPassword() throws JSONException {
        return jsonObject.getString("password");
    }

    public String getHost() throws JSONException {
        return jsonObject.getString("address");
    }

    public int getPort() throws JSONException {
        return jsonObject.getInt("port");
    }

    public void setUser(String user) throws JSONException {
        json.put("username", user);
    }

    public void setPassword(String password) throws JSONException {
        json.put("password", password);
    }

    public void setHost(String host) throws JSONException {
        json.put("address", host);
    }

    public void setPort(int port) throws JSONException {
        json.put("port", port);
    }

    public boolean isFinished() {
        try {
            return json.getBoolean("finished");
        } catch (JSONException | NullPointerException e) {
            return false;
        }
    }

    private JSONObject parseJSONFile() throws JSONException, IOException, SQLException {
        try {
            return new JSONObject(read());
        } catch (JSONException ex) {
            setUpManager.start();
            return null;
        }
    }

}
