package com.bonc.dw3.controller;

import com.bonc.dw3.service.HomepageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Api(value = "首页查询-1", description ="测试")
@CrossOrigin(origins ="*")
@Controller
@RequestMapping("/HomePage")
public class HomepageController {

    @Autowired
    HomepageService homepageService;

    /**
     * 1.头部栏组件接口
     * @Parameter userId 用户Id
     * @Parameter token 登录令牌
     *
     * @Author gp
     * @Date 2017/5/27
     */
    @ApiOperation("1.头部栏组件接口")
    @PostMapping("/headerSelect")
    public String headerSelect(/*@ApiParam("用户id")@RequestParam("userId")String userId,
                               @ApiParam("登陆令牌")@RequestParam("token")String token,*/
                               @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                               Model model){
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        Map<String, Object> resMap = homepageService.headerSelect();
        model.addAttribute("resMap", resMap);
        return "headerSelect";
    }


    /**
     * 2.菜单树组件接口
     *
     * @Author gp
     * @Date 2017/5/29
     */
    @ApiOperation("2.菜单树接口")
    @PostMapping("/nav")
    public String nav(/*@ApiParam("用户id")@RequestParam("userId")String userId,
                      @ApiParam("登陆令牌")@RequestParam("token")String token,*/
                      @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                      Model model){
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        List<Map<String,String>> list = homepageService.getAllMenu(userId);

        return "nav";
    }


    /**
     * 3.模块选项卡接口
     * @Parameter markType 模块类型
     *
     * @Author gp
     * @Date 2017/5/27
     */
    @ApiOperation("3.模块选项卡接口")
    @PostMapping("/moduleTab")
    public String moduleTab(/*@ApiParam("用户id")@RequestParam("userId")String userId,
                            @ApiParam("登陆令牌")@RequestParam("token")String token,
                            @ApiParam("类型id")@RequestParam("markType")String markType,*/
                            @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                            Model model){
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        String markType = paramMap.get("markType").toString();
        List<Map<String, String>> resList = homepageService.moduleTab(markType);
        model.addAttribute("resList", resList);
        return "moduleTab";
    }


    /**
     * 4-1.近期访问组件接口：筛选列表接口
     *
     * @Author gp
     * @Date 2017/5/29
     */
    @ApiOperation("4-1.近期访问组件接口：筛选列表接口")
    @PostMapping("/recentVisit")
    public String recentVisit(/*@ApiParam("用户id")@RequestParam("userId")String userId,
                              @ApiParam("登陆令牌")@RequestParam("token")String token,*/
                              @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                              Model model){
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        Map<String, Object> resMap = homepageService.recentVisit();
        model.addAttribute("resMap", resMap);
        return "recentVisit";
    }

    /**
     * 4-2.近期访问组件接口：近期访问列表接口
     *
     * @Author gp
     * @Date 2017/5/25
     */
    @ApiOperation("4-2.近期访问组件接口：近期访问列表接口")
    @PostMapping("/recentVisitList")
    public String recentVisitList(/*@ApiParam("用户ID")@RequestParam("userId")String userId,
                                  @ApiParam("登陆令牌")@RequestParam("token")String token,
                                  @ApiParam("查询类型指标专题报告")@RequestParam("selectId")String selectId,*/
                                  @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                                  Model model){
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        String selectId = paramMap.get("selectId").toString();
        String paramStr = userId + "," + selectId;
        Map<String, Object> recentVisitMap = homepageService.recentVisitList(paramStr);
        model.addAttribute("resMap", recentVisitMap);
        return "recentVisitList";
    }


    /**
     * 6-1.搜索接口:全部搜索
     * @Parameter userId 用户Id
     * @Parameter token 登陆令牌
     * @Parameter searchType 查询类型：999-全部
     * @Parameter search 查询输入的文本
     * @Parameter numStart 分页起始
     * @Parameter num 分页每一页的条数
     *
     * @Author gp
     * @Date 2017/5/16
     */
    @ApiOperation("6-1.搜索-全部搜索接口")
    @PostMapping("/allSearch")
    public String allSearch(/*@ApiParam("用户Id")@RequestParam("userId")String userId,
                            @ApiParam("登陆令牌")@RequestParam("token")String token,
                            @ApiParam("查询类型")@RequestParam("searchType")String searchType,
                            @ApiParam("查询关键词")@RequestParam("search")String search,
                            @ApiParam("全部日月标识")@RequestParam("tabId")String tabId,
                            @ApiParam("分页起始")@RequestParam("numStart")String numStart,
                            @ApiParam("每一页记录条数")@RequestParam("num")String num,*/
                            @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                            Model model){
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        String searchType = paramMap.get("searchType").toString();
        String search = paramMap.get("search").toString();
        String tabId = paramMap.get("tabId").toString();
        String numStart = paramMap.get("numStart").toString();
        String num = paramMap.get("num").toString();
        //es查询参数处理
        String paramStr = userId + "," + searchType + "," + search + "," + tabId +"," +numStart + "," + num;
        //查询es并拼接结果
        Map<String, Object> resMap = homepageService.allSearch(paramStr, numStart, num);

        model.addAttribute("resMap", resMap);
        return "allSearch";
    }


    /**
     * 6-2.搜索：指标接口
     *
     * @Author gp
     * @Date 2017/5/31
     */
    @ApiOperation("6-2.搜索-指标搜索接口")
    @PostMapping("/indexSearch")
    public String indexSearch(/*@ApiParam("用户Id")@RequestParam("userId")String userId,
                              @ApiParam("登陆令牌")@RequestParam("token")String token,
                              @ApiParam("查询类型")@RequestParam("searchType")String searchType,
                              @ApiParam("查询关键词")@RequestParam("search")String search,
                              @ApiParam("全部日月标识")@RequestParam("dayOrmonth")String dayOrmonth,
                              @ApiParam("分页起始")@RequestParam("numStart")String numStart,
                              @ApiParam("每一页记录条数")@RequestParam("num")String num,
                              @ApiParam("地域")@RequestParam("area")String area,
                              @ApiParam("日期")@RequestParam("date")String date,*/
                              @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                              Model model){
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        String searchType = paramMap.get("searchType").toString();
        String search = paramMap.get("search").toString();
        String dayOrmonth = paramMap.get("dayOrmonth").toString();
        String numStart = paramMap.get("numStart").toString();
        String num = paramMap.get("num").toString();
        String area = paramMap.get("area").toString();
        String date = paramMap.get("date").toString();
        //es查询参数处理
        String paramStr = userId + "," + searchType + "," + search + "," + dayOrmonth +"," +numStart + "," + num;
        //查询es并拼接结果
        Map<String, Object> resMap = homepageService.indexSearch(paramStr, numStart, num, area, date);

        model.addAttribute("resMap", resMap);
        return "indexSearch";
    }


    /**
     * 6-3.搜索：专题接口
     *
     * @Author gp
     * @Date 2017/5/31
     */
    @ApiOperation("6-3.搜索-专题搜索接口")
    @PostMapping("/specialSearch")
    public String specialSearch(/*@ApiParam("用户id")@RequestParam("userId")String userId,
                                @ApiParam("登陆令牌")@RequestParam("token")String token,
                                @ApiParam("搜索类型")@RequestParam("searchType")String searchType,
                                @ApiParam("搜索内容")@RequestParam("search")String search,
                                @ApiParam("分类：全部日月标识")@RequestParam("tabId")String tabId,
                                @ApiParam("分页起始")@RequestParam("numStart")String numStart,
                                @ApiParam("每一页记录条数")@RequestParam("num")String num,*/
                                @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                                Model model){
        Map<String, Object> resMap = new HashMap<>();
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        String searchType = paramMap.get("searchType").toString();
        String search = paramMap.get("search").toString();
        String tabId = paramMap.get("tabId").toString();
        String numStart = paramMap.get("numStart").toString();
        String num = paramMap.get("num").toString();

        //es查询参数处理
        String paramStr = userId + "," + searchType + "," + search + "," + tabId +"," +numStart + "," + num;
        //查询es获得数据
        resMap = homepageService.specialSearch(paramStr, numStart, num);
        model.addAttribute("resMap", resMap);
        return "specialSearch";
    }


    @ApiOperation("6-4.搜索-报告搜索接口")
    @PostMapping("/reportSearch")
    public String reportSearch(/*@ApiParam("用户id")@RequestParam("userId")String userId,
                               @ApiParam("登陆令牌")@RequestParam("token")String token,
                               @ApiParam("搜索类型")@RequestParam("searchType")String searchType,
                               @ApiParam("搜索内容")@RequestParam("search")String search,
                               @ApiParam("分页起始")@RequestParam("numStart")String numStart,
                               @ApiParam("每一页记录条数")@RequestParam("num")String num,*/
                               @ApiParam("请求参数json串")@RequestBody Map<String, Object> paramMap,
                               Model model){
        String userId = paramMap.get("userId").toString();
        String token = paramMap.get("token").toString();
        String searchType = paramMap.get("searchType").toString();
        String search = paramMap.get("search").toString();
        String numStart = paramMap.get("numStart").toString();
        String num = paramMap.get("num").toString();
        Map<String, Object> resMap = new HashMap<>();

        //es查询参数处理
        //String paramStr = userId + "," + searchType + "," + search + "," + tabId +"," +numStart + "," + num;
        //tabId用-1占位
        String paramStr = userId + "," + searchType + "," + search + "," + "-1," +numStart + "," + num;
        //查询es获得数据
        resMap = homepageService.reportPPTSearch(paramStr, numStart, num);
        model.addAttribute("resMap", resMap);
        return "reportSearch";
    }


}