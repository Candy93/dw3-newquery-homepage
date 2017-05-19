package com.bonc.dw3.controller;

import com.bonc.dw3.service.HomepageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "首页查询-1", description ="测试")
@CrossOrigin(origins ="*")
@Controller
@RequestMapping("/homepage")
public class HomepageController {

    @Autowired
    HomepageService homepageService;


    /**
     * 搜索接口-全部
     * @Parameter searchType 查询类型：-1.全部
     * @Parameter txt 查询输入的文本
     * @Parameter startNum 分页起始
     * @Parameter num 分页每一页的条数
     *
     * @Author gp
     * @Date 2017/5/16
     */
    @ApiOperation("搜索接口")
    @PostMapping("/search/all")
    public String search(@ApiParam("查询类型")@RequestParam("searchType")String searchType,
                         @ApiParam("查询关键词")@RequestParam("str")String str,
                         @ApiParam("分页起始")@RequestParam("startNum")String startNum,
                         @ApiParam("每一页记录条数")@RequestParam("num")String num,
                         Model model){
        //es查询参数处理
        String paramStr = searchType + "," + str + "," + startNum + "," + num;
        //查询es并拼接结果
        List<Map<String, Object>> allSearchList = homepageService.allSearch(paramStr);

        model.addAttribute("allSearchList", allSearchList);
        return "allSearch";
    }






}