// Server Side

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameServer {
    public void testConnect() {
        try {
            int serverPort = 5000;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            //serverSocket.setSoTimeout(10000);
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");

            Socket server = serverSocket.accept();
            System.out.println("Just connected to " + server.getRemoteSocketAddress());

            PrintWriter toClient =
                    new PrintWriter(server.getOutputStream(),true);
            BufferedReader fromClient =
                    new BufferedReader(
                            new InputStreamReader(server.getInputStream()));
            String line = fromClient.readLine();
            System.out.println("Server received: " + line);
            toClient.println("Thank you for connecting. Get ready to " +
                    "play! P.S. You might have to actually talk to who you " +
                    "are playing against lol.");
            server.close();
            serverSocket.close();
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run() {
        int x, y, player;
        SpaceAttackers game1 = new SpaceAttackers();
        // PlanetGame game1 = new PlanetGame();
        try {
            int serverPort = 5000;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            //serverSocket.setSoTimeout(10000);
            while(true) {
                //System.out.println("Waiting for client on port " +
                // serverSocket.getLocalPort() + "...");

                Socket server = serverSocket.accept();
                //System.out.println("Just connected to " + server
                // .getRemoteSocketAddress());

                PrintWriter toClient =
                        new PrintWriter(server.getOutputStream(),true);
                BufferedReader fromClient =
                        new BufferedReader(
                                new InputStreamReader(server.getInputStream()));

                String line = fromClient.readLine();
                x = Integer.parseInt(line.split(",")[1]);
                y = Integer.parseInt(line.split(",")[2]);
                player = Integer.parseInt(line.split(",")[0]);

                String turn = game1.turn(player, x, y);

                toClient.println(turn);
            }
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GameServer srv = new GameServer();
        System.out.println("Waiting for player...");
        srv.testConnect();  // player 1
        System.out.println("Waiting for player...");
        srv.testConnect();  // player 2
        System.out.println("Ready to play!");
        srv.run();
    }
}