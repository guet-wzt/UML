package myGame;

import BoatAndELepClass.BoatStyle;
import BoatAndELepClass.ElepWeight;

import java.util.Optional;

public class Optimum {
    /**
     * Optimum类的属性
     * Optimum 最佳次数
     * a[]数组是统计所有的2^n次方
     */
    public static final int N = 15;
    public static final int D = 2;
    public static int bestLine;

    //计算a[]数组
    public static int[] getArray() {
        int[] a = new int[16];
        a[0] = 1;
        for (int i = 1; i < 16; i++) {
            a[i] = a[i - 1] * 2;
        }
        return a;
    }

    //计算最佳解
    public static int getOptimum(int num, int[] a) {
        int optimum = 0;
        for (int k = N; k >= 0; k--) {
            if (a[k] > num)
                continue;
            optimum++;
            num = num - a[k];
        }
        return optimum;
    }

    //计算用户得分
    public static int getGrade(int bestN, int tn, double flagLine, double curLine) {
        int su = Math.abs((tn - bestN) * D);
        int dis = (int) (Math.abs(flagLine - curLine)*0.5);
        int grade = 100-su-dis;
        if(grade<=0){
            return 0;
        }else{
            return 100 - su - dis;
        }
    }
    //计算刻度
    public String getBest(Optional<String> optionalS,Optional<String> optionalS1){
        int weight = ElepWeight.getWeight(optionalS.get());
        int[] arr = Optimum.getArray();
        //获取最佳的点击次数
        int bestN = Optimum.getOptimum(weight,arr);
        String strBoat = optionalS1.get();
        double d = BoatStyle.getDownSign(weight,strBoat);
        String strLine = String.valueOf(d);
        bestLine = bestN;
        return strLine;
    }
    public int getBestN(){
        return bestLine;
    }
}