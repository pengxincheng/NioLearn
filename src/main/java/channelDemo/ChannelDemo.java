package channelDemo;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/7/5
 * @Time 11:50
 */


public class ChannelDemo {

    public static void main(String[] args) {
        try {
            RandomAccessFile aFile = new RandomAccessFile("data.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = inChannel.read(buf);      //写进buffer
            while (bytesRead != -1) {
              //  System.out.println("Read " + bytesRead);
                buf.flip();   //将buffer从写转变为读状态

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());  //读取数据
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
