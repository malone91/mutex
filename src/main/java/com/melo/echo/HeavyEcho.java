package com.melo.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by 76009 on 2019/10/2.
 */
public class HeavyEcho {

    public static void main(String[] args) throws IOException {
        final ServerSocketChannel ssc =
                ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        try {
            while (true) {
                SocketChannel sc = ssc.accept();
                new Thread(() ->{
                    ByteBuffer bf = ByteBuffer.allocateDirect(1024);
                    try {
                        sc.read(bf);
                        TimeUnit.SECONDS.sleep(1);
                        ByteBuffer wb = (ByteBuffer) bf.flip();
                        sc.write(wb);
                        sc.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ).start();
            }
        } finally {
            ssc.close();
        }
    }
}
