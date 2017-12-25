package main;

import model.utils.Direction;

import java.io.InputStream;
import java.io.OutputStream;

public class Player {
    public Direction currentDirection = Direction.None;
    private InputStream inputStream;
    private OutputStream outputStream;

    public Player(InputStream inputStream, OutputStream outputStream){
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void write(byte[] data){
        try {
            outputStream.write(data);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void read(){
        try {
            if (inputStream.available() > 0) {
                int value = inputStream.read();

                switch (value){
                    case 0:{
                        currentDirection = Direction.None;
                        break;
                    }
                    case 1:{
                        currentDirection = Direction.Up;
                        break;
                    }
                    case 2:{
                        currentDirection = Direction.Down;
                        break;
                    }
                    case 3: {
                        currentDirection = Direction.Left;
                        break;
                    }
                    case 4:{
                        currentDirection = Direction.Right;
                        break;
                    }
                }
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
