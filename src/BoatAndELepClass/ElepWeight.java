package BoatAndELepClass;

import java.util.Random;

public class ElepWeight {
    public static final int MINELP_WEIGHT = 1000;
    public static final int MIDELP_WEIGHT = 7000;
    public static final int BIGELP_WEIGHT = 12000;
    public static final int MAXELP_WEIGHT = 40000;
    public static Random random = new Random();
    public static int elp_weight;
    /**
     * 获取象的重量
     * 小型象：1-1000
     * 中型象：1000-8000
     * 大型象：8000-20000
     * 巨型象：20000-60000
     * @param string
     * @return
     */
    public ElepWeight(){}

    public static int getWeight(String string){
        switch (string){
            case "小型象":
                elp_weight = 1+random.nextInt(MINELP_WEIGHT);break;
            case "中型象":
                elp_weight = 1000+random.nextInt(MIDELP_WEIGHT);break;
            case "大型象":
                elp_weight = 8000+random.nextInt(BIGELP_WEIGHT);break;
            case "巨型象":
                elp_weight = 20000+random.nextInt(MAXELP_WEIGHT);break;
        }
        return elp_weight;
    }
}
