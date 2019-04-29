package BoatAndELepClass;

public class BoatStyle {
    /**
     * 定义船的属性
     * 同样的重量对应不同的船下降的比例；
     */
    public static final double MIN_PRO = 1;
    public static final double MID_PRO = 0.8;
    public static final double BIG_PRO = 0.5;
    public static final double MAX_PRO = 0.1;

    public static double getDownSign(int weight,String boatStyle) {
            double sign=0;
            switch (boatStyle){
                case "小船":
                    sign = weight*MIN_PRO;break;
                case "中船":
                    sign = weight*MID_PRO;break;
                case "大船":
                    sign = weight*BIG_PRO;break;
                case "巨船":
                    sign = weight*MAX_PRO;break;
            }
        return sign;
    }
}
