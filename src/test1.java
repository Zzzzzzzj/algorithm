import java.io.*;
import java.util.Scanner;

/**
 * 这个方法时间复杂度为 2的n次方  当n为100时 会超时
 */


public class test1 {

    private static int[][] math;
    private static int n;

    //初始化math 成为金字塔结构
    private  static void init(int n){
        for (int i=0;i<n;i++){
            for (int y=0;y<=i;y++){
                math[i][y]=(int)(9*Math.random()+1); //生成1到10之间的随机数
            }
        }
    }

    //从外面文件读取金字塔结构
    private static void init(){
        math=new int[5][5];
        File file=new File("resource/math.properties");
        try {
            BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line=in.readLine();
            int i=0;
            while (line!=null){
                String[] strs=line.split(",");
                int j=0;
                for (String str:strs){
                    int m=Integer.valueOf(str);
                    math[i][j]=m;
                    j++;
                }
                i++;
                line=in.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int MaxSum(int i,int j){
        if (i==n-1){
            return math[i][j];
        }
        int x=MaxSum(i+1,j);
        int y=MaxSum(i+1,j+1);
        return Math.max(x,y)+math[i][j];
    }
    public static void main(String[] args){
//        Scanner scanner=new Scanner(System.in);
//        n= scanner.nextInt();
//        math=new int[n][n];
//        init(n);
        init();
        int max= MaxSum(0,0);
        System.out.println(max);

    }



}
