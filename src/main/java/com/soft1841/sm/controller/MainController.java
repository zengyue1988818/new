package com.soft1841.sm.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private StackPane mainContainer;

    //显示商品类别数据
    public void listType() throws Exception {
        switchView("type.fxml");
    }
    //显示商品数据
    public void listGoods() throws Exception {
        switchView("good.fxml");
    }
    //显示Vip的数据
    public  void listVip() throws Exception {
        switchView("Vip.fxml");
    }
    //显示管理员的信息
    public void listGuanLi() throws Exception{
        switchView("guanli.fxml");
    }
    //点击标题回到后台主界面
    public void listDefault() throws Exception {
        switchView("default.fxml");
    }

    //封装一个切换视图的方法：用来根据fxml文件切换视图内容
    public  void listTypeAnalysis()throws Exception{
        switchView("type_analysis.fxml");
    }
    public  void listGoodsAnalysis()throws Exception{
        switchView("goods_analysis.fxml");
    }
        //封装一个切换视图的方法：用来根据fxml文件切换视图内容
    private void switchView(String fileName) throws Exception {
        //清除主面板之前内容
        ObservableList<Node> list = mainContainer.getChildren();
        mainContainer.getChildren().removeAll(list);
        //读取新的布局文件加入主面板
        AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/fxml/" + fileName)).load();
        mainContainer.getChildren().add(anchorPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
