package lisn.main;

import java.util.UUID;

import lisn.util.Compressor;
import lisn.util.CrackClass;
import lisn.util.Decompression;
import lisn.util.StrongFileUtil;


public class Main {

    // ������
    public static void main(String[] args) throws Exception {
        String oldJarPath = "J:\\CODE\\test\\charles.jar"; //ԭjar·��
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String name = oldJarPath.substring(0, oldJarPath.lastIndexOf("."));
        String outClassPath = name + uuid; //��ѹ��ʱ�ļ�·��
        String newJarPath = name + "_Crack.jar"; //����ѹ�����jar·��

        System.out.println("ԭjar·����" + oldJarPath);
        System.out.println("��ѹ��ʱ�ļ�·����" + outClassPath);
        System.out.println("��jar·����" + newJarPath);

        //��ѹ
        Decompression.uncompress(oldJarPath, outClassPath);

        //���class
        CrackClass.crack(oldJarPath, outClassPath);

        //ѹ��
        Compressor.compress(newJarPath, outClassPath);

        //ɾ��ѹ�����ļ���
        if (StrongFileUtil.deleteDirPath(outClassPath)) {
            System.out.println("ɾ��ѹ����ʱ�ļ��гɹ�");
        }
    }
}