package com.soft1841.sm.controller;
/**
 * 会员信息管理模块中的会员信息展示
 * @auther
 * 2018.12.25
 */
import com.soft1841.sm.entity.Vip;
import com.soft1841.sm.service.VipService;
import com.soft1841.sm.utils.ServiceFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class VipController implements Initializable {
    @FXML
    private FlowPane vipPane;
    private VipService vipService = ServiceFactory.getVipServiceInstance();
    private List<Vip> vipList= new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vipList = vipService.getAllVips();

        //读取数据库信息
        showVip(vipList);
    }
    private void showVip(List<Vip> vipList) {
        //清除之前内容
        vipPane.getChildren().clear();
        //移除之前的记录
        ObservableList<Node> observableList = vipPane.getChildren();
        vipPane.getChildren().remove(observableList);

        for (Vip vip:vipList) {
            HBox hBox = new HBox();
            //设置个人水平盒子
            hBox.setPrefSize(340, 260);
            //设置内边距
            hBox.setSpacing(25);
            hBox.setPadding(new Insets(20, 20, 20, 20));
            //美化水平盒子  调用css中的box
            hBox.getStyleClass().add("box");
            //创建左侧的垂直布局
            VBox leftBox = new VBox();
            leftBox.setSpacing(10);
            //对齐方式
            leftBox.setAlignment(Pos.TOP_CENTER);
            //头像图片  如果本地文件则url:"/img?
            Image image = new Image(vip.getPicture());
            ImageView pictureImg = new ImageView(image);
            pictureImg.setFitWidth(100);
            pictureImg.setFitHeight(100);
            //给头像设置圆形效果
            Circle circle = new Circle();
            circle.setCenterX(50);
            circle.setCenterY(50);
            circle.setRadius(40);
            pictureImg.setClip(circle);
            Label vipnamelabel = new Label("VIP");
            //头像加入左边盒子
            leftBox.getChildren().addAll(pictureImg,vipnamelabel);
            hBox.getChildren().add(leftBox);
            //创建中间垂直盒子
            VBox centerBox = new VBox();
            centerBox.setSpacing(10);
            centerBox.setAlignment(Pos.TOP_LEFT);
            Label mingzilabel = new Label("姓名：");
            Label nianfenlabel = new Label("年份:");
            Label jflabel    = new Label("积分:");
            Label dianhualabel  = new Label("电话:");
            Label dizhilabel  = new Label("地址：");
            centerBox.getChildren().addAll(mingzilabel,nianfenlabel,jflabel,dianhualabel,dizhilabel);
            hBox.getChildren().add(centerBox);
            //创建右边垂直布局盒子
            VBox rightBox = new VBox();
            rightBox.setSpacing(10);
            rightBox.setAlignment(Pos.TOP_LEFT);
            Label namelabel = new Label(vip.getName());//会员名字
            Label yearlabel = new Label(vip.getYear());//会员年份
            Label jifenlabel = new Label(vip.getJifen());//会员积分
            Label mobilelabel = new Label(vip.getMobile());//会员电话
            Label addresslabel = new Label(vip.getAddress());//会员地址
            Button button = new Button("删除");
            button.getStyleClass().add("menu1-btn");
            //点击删除按钮要做的事情
            button.setOnAction(event -> {
                //1弹出一个确认的对话框
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("确认对话框");
                alert.setContentText("确定删除该会员信息吗？");
                //2点击了“确认”按钮
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //得到个人的id
                    long id = vip.getId();
                    //调用readerDAO的删除方法
                    vipService.deleteVip(id);
                    //从流式面板里面删除掉
                    vipPane.getChildren().remove(hBox);
                }
            });
            rightBox.getChildren().addAll(namelabel,yearlabel,jifenlabel,mobilelabel,addresslabel,button);
            hBox.getChildren().add(rightBox);
            vipPane.getChildren().add(hBox);
        }
    }
    public void addVip(){
        Vip vip = new Vip();
        //新建一个舞台
        Stage stage = new Stage();
        stage.setTitle("新增会员界面");
        //创建一个垂直布局，用来放新增用户的各个组件
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20, 10, 10, 10));
        //创建一个日期选择器对象，并初始化值为当前日期
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        TextField nameField = new TextField("请输入姓名");
        TextField pictureField = new TextField("请输入头像地址");
        TextField addressField = new TextField("请输入会员地址");
        TextField jifenField = new TextField("新增会员的积分");
        TextField yearField = new TextField("新增会员的年限");
        TextField mobileField = new TextField("请输入手机号码");
        Button addBtn = new Button("确认新增" );
        addBtn.getStyleClass().add("button-blue");
        vBox.getChildren().addAll(nameField,pictureField,datePicker,jifenField,yearField,addressField,mobileField,addBtn);
        Scene scene = new Scene(vBox, 540, 450);
        stage.setScene(scene);
        stage.show();
        //点击新增按钮，将界面数据封装成一个Reader对象，写入数据库
        addBtn.setOnAction(event -> {
            String nameString = nameField.getText().trim();
            String pictureString = pictureField.getText().trim();
            String addressString = addressField.getText().trim();
            String jifenString = jifenField.getText().trim();
            String yearString = yearField.getText().trim();
            String mobileString = mobileField.getText().trim();
            vip.setAddress(addressString);
            vip.setMobile(mobileString);
            vip.setName(nameString);
            vip.setPicture(pictureString);
            vip.setJifen(jifenString);
            vip.setYear(yearString);
            System.out.println(vip.getName() + vip.getAddress() + vip.getPicture()+vip.getJifen()+vip.getYear());
            vipService.addVip(vip);
            stage.close();
            vipList = vipService.getAllVips();
            showVip(vipList);
        });
    }
}