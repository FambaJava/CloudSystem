package net.mika.cloudsystem.connection.manager;

import net.mika.cloudsystem.connection.NettyClientConnection;

import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {


    private Map<ConnectionType, Map<String, Integer>> conDatas = new HashMap<>();

    public void init(){
        Map<String, Integer> inner = new HashMap<>();
        inner.put("localhost", 5013);
        conDatas.put(ConnectionType.TeamSpeak, inner);

        Map<String, Integer> innerMap = new HashMap<>();
        inner.put("localhost", 5003);
        conDatas.put(ConnectionType.Master, innerMap);
    }

    public String getHost(){
        return "localhost";
    }

    public boolean connect(ConnectionType connectionType, String host){
        try {
            int port = conDatas.get(connectionType).get(host);

            NettyClientConnection netty = new NettyClientConnection(getHost(), port);
            return true;
        }catch (Exception ex){
            return false;
        }
    }


}
