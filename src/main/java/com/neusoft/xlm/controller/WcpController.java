package com.neusoft.xlm.controller;

import com.neusoft.xlm.service.WcpService;
import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.tokenizers.ChineseWordTokenizer;
import com.kennycason.kumo.palette.LinearGradientColorPalette;
import org.springframework.stereotype.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.Resource;

@Controller
public class WcpController {
    String FILE_PATH="D:\\电影数据可视化分析系统JAVA\\电影数据可视化分析\\data.txt";
    String BACK_GROUND_IMG_FILE_PATH="D:\\电影数据可视化分析系统JAVA\\电影数据可视化分析\\src\\main\\resources\\templates\\img\\title_c.jpg";
    String TO_SRC_IMG_FILE_PATH="D:\\电影数据可视化分析系统JAVA\\电影数据可视化分析\\target\\classes\\templates\\img";

    @Resource
    private WcpService wcpService;

    /**
     * 跳转到标题词云图页面
     * @return
     */
    @GetMapping("title_c")
    public String getTitle_c() {
        return "title_c";
    }

    /**
     * 跳转到简介词云图页面
     * @return
     */
    @GetMapping("summary_c")
    public String getSummary_c() {
        return "summary_c";
    }

    /**
     * 跳转演员词云图页面
     * @return
     */
    @GetMapping("casts_c")
    public String getCasts_c() {
        return "casts_c";
    }

    /**
     * 根据casts，数据库查询后生成词云图
     * @return
     * @throws IOException
     */
    @GetMapping("/createWCPCasts")
    public String createWCPCasts() throws IOException {
        String imgFolder=TO_SRC_IMG_FILE_PATH;
        if(!hasImage(imgFolder,"casts_c.jpg")){
            List<String> data=wcpService.queryWCPCastsData();
            String filePath = FILE_PATH;
            writeToFile(data,filePath);

            //生成词云图
            creatWCPByFile(filePath,"casts");
        }
        return "casts_c";
    }

    /**
     * 根据title，数据库查询后生成词云图
     * @return
     * @throws IOException
     */
    @GetMapping("/createWCPTitle")
    public String createWCPTitle() throws IOException {
        String imgFolder=TO_SRC_IMG_FILE_PATH;
        if(!hasImage(imgFolder,"title_c.jpg")) {
            List<String> data = wcpService.queryWCPTitlesData();
            String filePath = FILE_PATH;
            writeToFile(data, filePath);
            //生成词云图
            creatWCPByFile(filePath, "title");
        }
        return "title_c";
    }

    /**
     * 根据summary，数据库查询后生成词云图
     * @return
     * @throws IOException
     */
    @GetMapping("/createWCPSummary")
    public String createWCPSummary() throws IOException {
        String imgFolder=TO_SRC_IMG_FILE_PATH;
        if(!hasImage(imgFolder,"summary_c.jpg")) {
            List<String> data = wcpService.queryWCPSummaryData();
            String filePath = FILE_PATH;
            writeToFile(data, filePath);
            //生成词云图
            creatWCPByFile(filePath, "summary");
        }
        return "summary_c";
    }

    /**
     * 创建词云图文件方法
     * @param filePath
     * @param word
     * @throws IOException
     */
    public void  creatWCPByFile(String filePath,String word) throws IOException {
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        frequencyAnalyzer.setWordFrequenciesToReturn(600);
        frequencyAnalyzer.setMinWordLength(2);//引入中文解析器
        frequencyAnalyzer.setWordTokenizer(new ChineseWordTokenizer());//指定文本文件路径，生成词频集合
        final List<WordFrequency> wordFrequencyList = frequencyAnalyzer.load(filePath);//设置图片分辨率
        Dimension dimension = new Dimension(1920, 1080);//此处的设置采用内置常量即可，生成词云对象
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);//设置边界及字体
        wordCloud.setPadding(2);
        Font font = new Font("STSong-Light", 2, 20);//设置词云显示的三种颜色，越靠前设置表示词频越高的词语的颜色
        wordCloud.setColorPalette(new LinearGradientColorPalette(Color.RED, Color.BLUE, Color.GREEN, 30,
                30));
        wordCloud.setKumoFont(new KumoFont(font));//设置背景色
        wordCloud.setBackgroundColor(new Color(255, 255, 255));//设置背景颜色
        wordCloud.setBackground(new PixelBoundryBackground(BACK_GROUND_IMG_FILE_PATH));//设置背景图片
        wordCloud.setBackground(new CircleBackground(255));
        wordCloud.setFontScalar(new SqrtFontScalar(12, 45));//生成词云
        wordCloud.build(wordFrequencyList);

        wordCloud.writeToFile(TO_SRC_IMG_FILE_PATH + word + "_c.jpg");
    }

    /**
     * 数据写入文件
     * @param strlist
     * @param filename
     * @throws IOException
     */
    public static void writeToFile(List<String> strlist, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (String str : strlist) {
            writer.write(str);
            writer.newLine();
        }
        writer.close();
    }

    /**
     * 判断某个文件夹下是
      否有某个文件
     * @param folderPath
     * @param filename
     * @return
     */
    public static boolean hasImage(String folderPath,String filename) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            // 如果传入的路径不是一个文件夹，则直接返回false
            return false;
        }
        // 遍历文件夹下的所有文件和文件夹
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().equals(filename)) {
                // 如果找到了.jpg文件，则返回true
                return true;
            }
        }
        // 如果遍历完了所有文件和文件夹还没有找到.jpg文件，则返回false
        return false;
    }


}
