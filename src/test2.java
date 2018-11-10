import java.io.*;
import java.util.Scanner;

/**
 * 优化复杂度
 * 如果每算出一个MaxSum(r,j)就保存起来，下次用
 *到其值的时候直接取用，则可免去重复计算。那么
 *可以用O(n2
 *)时间完成计算。因为三角形的数字总
 *数是 n(n+1)/2
 */


public class test2 {
    private static int[][] math;
    private static int n;
    private static int[][] maxSum;

    //初始化math 成为金字塔结构
    private  static void init(int n){
        for (int i=0;i<n;i++){
            for (int y=0;y<=i;y++){
                math[i][y]=(int)(9*Math.random()+1); //生成1到10之间的随机数
                maxSum[i][y]=-1;      //将maxSum 写入-1 方便之后写入 在MaxSum 方法中调用
            }
        }
    }

    //从外面文件读取金字塔结构
    private static void init(){
        math=new int[5][5];

        //初始化 maxSum
        maxSum=new int[5][5];

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
                    //将maxSum 写入-1 方便之后写入 在MaxSum 方法中调用
                    maxSum[i][j]=-1;
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
        if (maxSum[i][j]!=-1){
            return maxSum[i][j];   //节约资源
        }

        if (i==n-1){
            maxSum[i][j]=math[i][j];     //写入maxSum
        }
        else {
            int x = MaxSum(i + 1, j);
            int y = MaxSum(i + 1, j + 1);
            maxSum[i][j]=Math.max(x,y)+math[i][j]; //写入maxSum
        }
        return maxSum[i][j];
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        n= scanner.nextInt();
        math=new int[n][n];
        maxSum=new int[n][n];
        init(n);
        int max= MaxSum(0,0);
        System.out.println(max);

    }



}
