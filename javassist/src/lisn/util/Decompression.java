package lisn.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//��ѹ
public class Decompression {

    private static final int BUFFER = 8192;


    public static void uncompress(String jarFilePath, String tarDirPath) {
        File jarFile = new File(jarFilePath);
        File tarDir = new File(tarDirPath);
        if (!jarFile.exists())
            throw new RuntimeException(jarFilePath + "�����ڣ�");
        try {
            JarFile jfInst = new JarFile(jarFile);
            Enumeration<JarEntry> enumEntry = jfInst.entries();
            while (enumEntry.hasMoreElements()) {
                JarEntry jarEntry = enumEntry.nextElement();
                File tarFile = new File(tarDir, jarEntry.getName());
                if (jarEntry.getName().contains("META-INF")) {
                    File miFile = new File(tarDir, "META-INF");
                    if (!miFile.exists()) {
                        miFile.mkdirs();
                    }

                }
                makeFile(jarEntry, tarFile);
                if (jarEntry.isDirectory()) {
                    continue;
                }
                FileChannel fileChannel = new FileOutputStream(tarFile).getChannel();
                InputStream ins = jfInst.getInputStream(jarEntry);
                transferStream(ins, fileChannel);
            }
            System.out.println("��ѹ�ɹ�!");
        } catch (FileNotFoundException e) {
            System.out.println("��ѹ�쳣>>>" + e);
        } catch (IOException e) {
            System.out.println("��ѹ�쳣>>>" + e);
        }
    }

    /**
     * ����������
     *
     * @param ins     ������
     * @param channel �����
     */
    private static void transferStream(InputStream ins, FileChannel channel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER);
        ReadableByteChannel rbcInst = Channels.newChannel(ins);
        try {
            while (-1 != (rbcInst.read(byteBuffer))) {
                byteBuffer.flip();
                channel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (null != rbcInst) {
                try {
                    rbcInst.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != channel) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * �����ļ�
     *
     * @param jarEntry jarʵ��
     * @param fileInst �ļ�ʵ��
     * @throws IOException �׳��쳣
     */
    public static void makeFile(JarEntry jarEntry, File fileInst) {
        if (!fileInst.exists()) {
            if (jarEntry.isDirectory()) {
                fileInst.mkdirs();
            } else {
                try {
                    if (!fileInst.getParentFile().exists()) {
                        fileInst.getParentFile().mkdirs();
                    }
                    fileInst.createNewFile();
                    System.out.println("��ѹ�ļ���".concat(fileInst.getPath()));
                } catch (IOException e) {
                    System.out.println("�����ļ�ʧ��>>>".concat(fileInst.getPath()));
                }
            }
        }
    }

}