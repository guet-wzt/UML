package myGame;

import BoatAndELepClass.BoatStyle;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class Window {
    public static Optional<String> optionalS; //获取选象信息
    public static Optional<String> optionalS1;//获取选船信息
    public static TextField textField = new TextField("0");  //刻度线
    public static TextField currentTickMark = new TextField("0"); //当前刻度
    public static TextField clickTimes = new TextField("0"); //点击次数
    public static TextField remainTime = new TextField("20s"); //剩余时间
    public static HBox hBox = new HBox(); //Scene下主要的HBox
    public static HBox hBox1 = new HBox();//hBox1包含vBox1和vBox2
    public static VBox vBox = new VBox(); //vBox包含右边控件
    public static VBox vBox1 = new VBox();
    public static VBox vBox2 = new VBox();
    public static int bestN; //最少次数
    public static int sum;  //统计重量
    public static int score;//用户得分
    public static int flag; //标记

    public Optional<String> getOption1(){
        return optionalS;
    }
    public Optional<String> getOption2(){
        return optionalS1;
    }


    public void theFirstWindow() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("曹冲称象");
        alert.setHeaderText("欢迎来到曹冲称象！");
        Optional<ButtonType> res = alert.showAndWait();
        if (!res.isPresent()) {
            System.exit(0);
        }
        /**
         * 弹出窗口选择不同类型的象
         */
        ChoiceDialog<String> elphentChoice = new ChoiceDialog<>();
        String[] elp = {"小型象", "中型象", "大型象", "巨型象"};
        elphentChoice.getItems().addAll(elp);
        elphentChoice.setHeaderText("选象");
        optionalS = elphentChoice.showAndWait();
        //如果没有选择就继续弹出窗口
        while (!optionalS.isPresent()) {
            optionalS = elphentChoice.showAndWait();
        }
        System.out.println(optionalS.get());
//        elphentChoice.getItems().clear();

        /**
         * 弹出窗口选择不同类型的船
         */
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        ChoiceDialog<String> boatChoice = new ChoiceDialog<>();
        String[] strings = {"小船", "中船", "大船", "巨船"};
        boatChoice.getItems().addAll(strings);
        boatChoice.setHeaderText("选船");
        optionalS1 = boatChoice.showAndWait();
        while (!optionalS1.isPresent()) {
            optionalS1 = boatChoice.showAndWait();
        }
        System.out.println(optionalS1.get());
    }

    public HBox mainWindow(int best,Alert alert2){
        /**
         * 设置主窗口
         *在主窗口选择不同类型的石头
         */
        //将vBox1和vBox2翻入左边的hBox1中
        bestN = best;
        Button[] button = new Button[16];
        String strop = optionalS1.get();
        for(int i = 0;i<16;i++){
            button[i] = new Button("2^"+i);
           // button[i].setStyle("-fx-background-color:gray");
            button[i].setPrefSize(80,20);
            if(i<8){
                vBox1.getChildren().add(button[i]);
            }else{
                vBox2.getChildren().add(button[i]);
            }
            final int s = i;
            button[i].setOnAction(event -> {
                String string = clickTimes.getText();
                clickTimes.setText(""+(Integer.parseInt(string)+1));
                sum+=Math.pow(2,s);
                double temp = BoatStyle.getDownSign(sum,strop);
                currentTickMark.setText(String.valueOf(temp));

                //判断是否结束游戏，结束弹出窗口
                double d1 = Double.valueOf(textField.getText());
                double d2 = Double.valueOf(currentTickMark.getText());
                if(flag==1||Math.abs(d1-d2)<0.5||d2>d1){
                    final double flagLine = Double.valueOf(textField.getText().trim());
                    final double curLine  = Double.valueOf(currentTickMark.getText().trim());
                    final int tN = Integer.parseInt(clickTimes.getText().trim());
                    score = Optimum.getGrade(bestN,tN,flagLine,curLine);
                    alert2.setContentText("最佳次数:"+bestN+"次"+"   你的得分:"+score);
                    alert2.showAndWait();
                }
            });
        }
        hBox1.setSpacing(40);
        hBox1.setAlignment(Pos.BOTTOM_LEFT);
        vBox.setAlignment(Pos.BOTTOM_LEFT);
        vBox.setSpacing(20);
        vBox1.setSpacing(20);
        vBox1.setAlignment(Pos.CENTER);
        vBox2.setSpacing(20);
        vBox2.setAlignment(Pos.CENTER);

        vBox.setAlignment(Pos.CENTER);
        //将vBox1和vBox2翻入左边的hBox1中
        hBox1.getChildren().addAll(vBox1,vBox2);
        //右边的窗体设置vBox.setSpacing(25);vBox
        Label label = new Label("刻度线:");
        textField.setDisable(true);
        vBox.getChildren().addAll(label,textField);

        Label label1 = new Label("当前刻度:");
        currentTickMark.setDisable(true);
        vBox.getChildren().addAll(label1,currentTickMark);

        Label label2 = new Label("点击次数:");
        clickTimes.setDisable(true);
        vBox.getChildren().addAll(label2,clickTimes);

        Label label3 = new Label("剩余时间：");
        remainTime.setDisable(true);
        vBox.getChildren().addAll(label3,remainTime);
        //将hBox1和vBox放入hBox中
        hBox.getChildren().add(hBox1);
        hBox.getChildren().add(vBox);
        return hBox;
    }

    public void setFild(String string){
        textField.setText(string);
    }

    public TextField getReaminTime(){
        return remainTime;
    }
    public TextField getTextField(){
        return textField;
    }

    /**
     * 创建最后的弹出窗口，游戏继续或者结束
     */
    public Alert  getLastWindow(){
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText("游戏结束");
        return alert2;
    }
}

