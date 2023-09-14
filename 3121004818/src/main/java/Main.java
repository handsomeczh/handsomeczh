import com.job.utils.FilesIOUtil;
import com.job.utils.HammingUtil;
import com.job.utils.SimHashUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @author czh
 * @version 1.0.0
 * 2023/9/14 10:24
 */
public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入论文原文的文件的绝对路径：");
        String fileName01 = sc.nextLine();
        System.out.println("请输入抄袭版论文的文件的绝对路径：");
        String fileName02 = sc.nextLine();
        System.out.println("请输入输出答案文件的绝对路径：");
        String answer = sc.nextLine();
        //计算相似度
        double similarity = HammingUtil.similarity(SimHashUtil.getSimHash(fileName01), SimHashUtil.getSimHash(fileName02));
        //将相似度写入文件
        System.out.println(similarity);
        System.out.println("两篇文章相识度为："+String.format("%4.2f",similarity)+"%");
        FilesIOUtil.writeFile(answer, String.valueOf(similarity));

        /*
        请输入论文原文的文件的绝对路径：
        E:\AllJavaTools\softwarejob\txt\orig.txt
        请输入抄袭版论文的文件的绝对路径：
        E:\AllJavaTools\softwarejob\txt\orig_0.8_add.txt
        请输入输出答案文件的绝对路径：
        E:\AllJavaTools\softwarejob\txt\answer.txt
         */

    }
}
