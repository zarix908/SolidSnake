package main;

import lombok.val;
import model.game.Game;
import model.game.GameFrame;
import model.utils.Direction;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);

            Socket firstPlayerSocket = serverSocket.accept();
            InputStream firstPlayerInput = firstPlayerSocket.getInputStream();
            OutputStream firstPlayerOutput = firstPlayerSocket.getOutputStream();

            Socket secondPlayerSocket = serverSocket.accept();
            InputStream secondPlayerInput = secondPlayerSocket.getInputStream();
            OutputStream secondPlayerOutput = firstPlayerSocket.getOutputStream();

            val players = new Player[]{new Player(firstPlayerInput, firstPlayerOutput),
                    new Player(secondPlayerInput, secondPlayerOutput)};

            val game = new Game(new GameplaySettings(
                    GameplaySettings.getRandomField(30, 20, 2),
                    true,
                    20,
                    50,
                    40,
                    30,
                    2
            ));

            while (true) {
                for (Player player: players) {
                    player.read();
                }

                GameFrame frame = game.makeTurn(new Direction[]{players[0].currentDirection, players[1].currentDirection});

                for (Player player: players){
                    player.write(frame.toString().getBytes());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}