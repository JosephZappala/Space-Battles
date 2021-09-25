// Client Side

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    // input IP number of servers machine
    String hostIp = "172.24.198.139";
    public void send(int player) {
        try {
            int serverPort = 5000;

            InetAddress host = InetAddress.getByName(hostIp);
            System.out.println("Connecting to server on port " + serverPort);

            Socket socket = new Socket(host,serverPort);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());


            PrintWriter toServer =
                    new PrintWriter(socket.getOutputStream(),true);
            BufferedReader fromServer =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            toServer.println("Connected from Player " + player);
            String line = fromServer.readLine();
            System.out.println();
            System.out.println(line);

            fromServer =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            toServer.close();
            fromServer.close();
            socket.close();
            System.out.println();
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void run(String numArray) {
        try {
            int serverPort = 5000;
            InetAddress host = InetAddress.getByName(hostIp);

            Socket socket = new Socket(host,serverPort);

            PrintWriter toServer =
                    new PrintWriter(socket.getOutputStream(),true);
            BufferedReader fromServer =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            toServer.println(numArray);
            String line = fromServer.readLine();
            char[] lineArray = line.toCharArray();
            for (int i = 0; i < lineArray.length; i ++) {
                if (lineArray[i] == '@') {
                    System.out.println();
                }
                else {
                    System.out.print(lineArray[i]);
                }
            }

            toServer.close();

            fromServer.close();

            socket.close();
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        GameClient client = new GameClient();
        int x, y, player;
        String inputToSend;

        System.out.print("Insert Player 1 or 2: ");
        player = stdin.nextInt();

        client.send(player);

        do {
            System.out.print("Input X of missile: ");
            x = stdin.nextInt();
            System.out.print("Input Y of missile: ");
            y = stdin.nextInt();
            inputToSend = player + "," + x + "," + y;

            client.run(inputToSend);

        }while (x >= 0);

        System.out.println("Disconnecting");
    }
}