package net.mika.cloudsystem.connection.manager;

import net.mika.cloudsystem.connection.NettyClientConnection;

import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {


    private Map<ConnectionType, Map<String, Integer>> conDatas = new HashMap<>();

    private NettyClientConnection netty;

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

    public void connect(ConnectionType connectionType, String host){
        try {
            int port = conDatas.get(connectionType).get(host);

            netty = new NettyClientConnection(getHost(), port);
            netty.connect();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void wakeTSUpAndRenewTheAdminKey(){
        netty.wakeTSUpAndRenewTheAdminKey();
    }


}
