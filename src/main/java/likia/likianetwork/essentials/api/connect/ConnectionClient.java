package likia.likianetwork.essentials.api.connect;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConnectionClient {
    public static @Nullable JSONObject createSocketConnection(@NotNull JSONObject jsonRequest,@Nullable Integer timeout) throws IOException {
        Socket socket = new Socket();
        if(timeout == null){
            timeout = 3000;
        }
        socket.setSoTimeout(timeout);
        try {
            //Try to connect to the local MultiServerControl
            socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 23366), 3000);
            System.out.println("Connected to MSC. ["+socket.getInetAddress() +":"+socket.getPort()+"]");

            PrintStream input = new PrintStream(socket.getOutputStream());
            BufferedReader output = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            input.println(jsonRequest);
            JSONObject returnObject = (JSONObject) (new JSONParser().parse(output.readLine()));

            input.close();
            output.close();
            socket.close();
            return returnObject;
        }catch (SocketTimeoutException ste){
            System.out.println("Connection timed out.");
        }catch(ParseException pe){
            System.out.println("JSON Parse Failed ("+pe.getCause()+") "+pe.getMessage());
        } catch (Exception e){
            System.out.println("Connection occurred an unknown error("+e.getCause()+") "+e.getMessage());
        }finally {
            socket.close();
        }

        return null;
    }
}
