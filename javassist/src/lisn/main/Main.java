package lisn.main;

import java.util.UUID;

import lisn.util.Compressor;
import lisn.util.CrackClass;
import lisn.util.Decompression;
import lisn.util.StrongFileUtil;


public class Main {

    // 主函数
    public static void main(String[] args) throws Exception {
        String oldJarPath = "J:\\CODE\\test\\charles.jar"; //原jar路径
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String name = oldJarPath.substring(0, oldJarPath.lastIndexOf("."));
        String outClassPath = name + uuid; //解压临时文件路径
        String newJarPath = name + "_Crack.jar"; //重新压缩后的jar路径

        System.out.println("原jar路径：" + oldJarPath);
        System.out.println("解压临时文件路径：" + outClassPath);
        System.out.println("新jar路径：" + newJarPath);

        //解压
        Decompression.uncompress(oldJarPath, outClassPath);

        //输出class
        CrackClass.crack(oldJarPath, outClassPath);

        //压缩
        Compressor.compress(newJarPath, outClassPath);

        //删除压缩的文件夹
        if (StrongFileUtil.deleteDirPath(outClassPath)) {
            System.out.println("删除压缩临时文件夹成功");
        }
    }
}