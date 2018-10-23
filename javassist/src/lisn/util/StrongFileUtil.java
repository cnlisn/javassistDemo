package lisn.util;


import java.io.File;

public class StrongFileUtil {

    public static boolean deleteDirPath(String filepath) {
        File file = new File(filepath);
        if (!file.exists()){
            throw new RuntimeException(filepath + "�����ڣ�");
        }else{
            return deleteDir(file);
        }
    }

    //�ݹ�ɾ��Ŀ¼
    public static boolean deleteDir(File file) {
        if (!file.exists()) {
            System.out.println("[deleteDir]File " + file.getAbsolutePath()
                    + " does not exist.");
            return false;
        }
        if (file.isDirectory()) {// Ŀ¼
            File[] files = file.listFiles();
            for (File subFile : files) {
                boolean isSuccess = deleteDir(subFile);
                if (!isSuccess) {
                    return isSuccess;
                }
            }
        } else {// �ļ� 
            boolean isSuccess = file.delete();
            if (!isSuccess) {
                return isSuccess;
            }
        }
        if (file.isDirectory()) {
            return file.delete();
        } else {
            return true;
        }
    }
}
