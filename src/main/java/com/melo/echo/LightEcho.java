package com.melo.echo;

import com.sun.xml.internal.ws.api.pipe.Fiber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by 76009 on 2019/10/2.
 */
public class LightEcho {

    public static void main(String[] args) throws IOException {
        final ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(9090));
        try {
            while (true) {
                //fiber
                SocketChannel sc = ssc.accept();
                new Thread(()->{
                    ByteBuffer bf = ByteBuffer.allocateDirect(1024);
                    try {
                        sc.read(bf);
                        TimeUnit.SECONDS.sleep(1);
                        ByteBuffer bw = (ByteBuffer)bf.flip();
                        sc.write(bw);
                        sc.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }finally {
            ssc.close();
        }
    }
}
