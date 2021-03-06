import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Integer count = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("输入目标文件夹：");
        String destDir = scan.next();
        File destFile = new File(destDir);
        if (!destFile.isDirectory()) {
            System.err.println("没有找到文件夹：" + destDir);
            System.exit(0);
        }
        System.out.println("输入需要去除的文件名：");
        String destName = scan.next();
        long starTime = System.currentTimeMillis();

        listDir(destDir, destName);

        long endTime = System.currentTimeMillis();
        long time = endTime - starTime;
        System.out.println("共处理" + count + "个文件或文件夹，耗时: " + time + " 毫秒");
    }

    private static void listDir(String destDir, String destName) {
        File destFile = new File(destDir);
        if (destFile.exists() && destFile.isDirectory()) {
            File[] files = destFile.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            List<File> fileList = Arrays.asList(files);
            for (File file : fileList) {
                String name = file.getName();
                if (name.contains(destName)) {
                    count++;
                    String newName = name.replace(destName, "");
                    file.renameTo(new File(file.getParent(), newName));
                }
                if (file.isDirectory()) {
                    listDir(file.getPath(), destName);
                }
            }
        }
    }
}
