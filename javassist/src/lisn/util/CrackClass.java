package lisn.util;

import java.io.IOException;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class CrackClass {

    public static void crack(String oldJarPath, String outClassPath) throws NotFoundException, CannotCompileException, IOException {
        // ����ǵõ�������ĳ�
        ClassPool pool = ClassPool.getDefault();

        // ȡ����Ҫ�������jar�ļ����趨·��
        pool.insertClassPath(oldJarPath);

        // ȡ����Ҫ�������޸ĵ��ļ���ע��������·��
        CtClass cc1 = pool.get("com.xk72.charles.kKPk");

        try {
        	// ȡ����Ҫ�޸ĵķ���
            CtMethod ctMethod = cc1.getDeclaredMethod("lcJx", null);
            // �޸ķ�����ֱ��return true;
            ctMethod.setBody("{return true;}");
            ctMethod = cc1.getDeclaredMethod("JZlU", null);
            ctMethod.setBody("{return \"Regisered ɺ��\";}");
            
            cc1.writeFile(outClassPath);
            
            System.out.println("������class�޸ĳɹ�");
        } catch (NotFoundException e) {
            System.out.println("������class�쳣:" + e);
        }
    }

}
