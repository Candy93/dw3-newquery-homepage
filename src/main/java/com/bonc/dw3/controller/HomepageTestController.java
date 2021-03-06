package com.bonc.dw3.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author gp
 */
@Api(value = "首页查询-2", description ="示例数据")
@CrossOrigin(origins ="*")
@Controller
@RequestMapping("/test/HomePage")
public class HomepageTestController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 1.头部栏组件接口
     *
     * @Author gp
     * @Date 2017/5/22
     */
    @ApiOperation("1.头部栏组件接口")
    @PostMapping("/headerSelect")
    public String headerSelect(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                               Model model){
        String[] a = {"999,综合", "1,指标", "2,专题", "3,报告"};
        List<Map<String, Object>> selectList = new ArrayList<>();
        Map<String, Object> resMap = new HashMap<>(10);
        for (int i = 0; i < a.length; i ++){
            String[] b = a[i].split(",");
            Map<String, Object> map = new HashMap<>(10);
            map.put("id", b[0]);
            map.put("name", b[1]);
            selectList.add(map);
        }
        resMap.put("default", selectList.get(0));
        resMap.put("selectList", selectList);
        model.addAttribute("resMap", resMap);
        return "headerSelect";
    }


    /**
     * 2.菜单树组件接口
     *
     * @Author gp
     * @Date 2017/5/22
     */
    @ApiOperation("2.菜单树组件接口")
    @PostMapping("/nav")
    public String nav(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                      Model model){
        String[] title1 = {"CKP_08626,移动业务计费收入,/indexDetails,-1", "CKP_08546,4G业务计费收入,/indexDetails,-1"};
        String[] a001 = {"100101,移动业务用户类,/homePage,-1", "100102,移动业务使用类,/homePage,-1"};
        String[] a002 = {"100201,移动业务,/homePage,-1", "100202,宽带业务,/homePage,-1"};
        String[] title2 = {"20011101,运营总览,/special,-1", "20011102,三大战役,/special,-1"};
        String[] b001 = {"01,运营概况,/special,-1", "02,移动业务,/special,-1"};
        String[] b002 = {"01,212C业务专题,/special,-1", "02,冰淇淋业务专题,/special,-1"};
        Map<String, Object> resMap = new HashMap<>(10);
        List<Map<String, Object>> svgList = new ArrayList<>();
        List<Map<String, Object>> titleList1 = titleList(title1);
        List<Map<String, Object>> nodeList1 = nodeList(a001);
        List<Map<String, Object>> nodeList2 = nodeList(a002);
        List<Map<String, Object>> titleList2 = titleList(title2);
        List<Map<String, Object>> nodeList21 = nodeList(b001);
        List<Map<String, Object>> nodeList22 = nodeList(b002);
        Map<String, Object> map1 = new HashMap<>(20);
        map1.put("id", "1");
        map1.put("name", "指标");
        map1.put("url", "/index");
        map1.put("imgName", "index");
        Map<String, Object> titleMap1 = new HashMap<>(20);
        titleMap1.put("titleClassId", "1001");
        titleMap1.put("titleClassName", "基础指标");
        titleMap1.put("list", titleList1);
        map1.put("titleList", titleMap1);
        Map<String, Object> nodeMap1 = new HashMap<>(20);
        nodeMap1.put("classId", "1001");
        nodeMap1.put("className", "基础指标");
        nodeMap1.put("nodes", nodeList1);
        Map<String, Object> nodeMap2 = new HashMap<>(20);
        nodeMap2.put("classId", "1002");
        nodeMap2.put("className", "分析指标");
        nodeMap2.put("nodes", nodeList2);
        List<Map<String, Object>> treeList1 = new ArrayList<>();
        treeList1.add(nodeMap1);
        treeList1.add(nodeMap2);
        map1.put("treeList", treeList1);

        Map<String, Object> map2 = new HashMap<>(20);
        map2.put("id", "2");
        map2.put("name", "专题");
        map2.put("url", "/special");
        map2.put("imgName", "special");
        Map<String, Object> titleMap2 = new HashMap<>(20);
        titleMap2.put("titleClassId", "2001");
        titleMap2.put("titleClassName", "基础业务");
        titleMap2.put("list", titleList2);
        map2.put("titleList", titleMap2);
        Map<String, Object> nodeMap21 = new HashMap<>(20);
        nodeMap21.put("classId", "2001");
        nodeMap21.put("className", "基础业务");
        nodeMap21.put("nodes", nodeList21);
        Map<String, Object> nodeMap22 = new HashMap<>(10);
        nodeMap22.put("classId", "2002");
        nodeMap22.put("className", "创新业务");
        nodeMap22.put("nodes", nodeList22);
        List<Map<String, Object>> treeList2 = new ArrayList<>();
        treeList2.add(nodeMap21);
        treeList2.add(nodeMap22);
        map2.put("treeList", treeList2);

        svgList.add(map1);
        svgList.add(map2);
        resMap.put("svgList", svgList);

        model.addAttribute("resMap", resMap);
        return "nav";
    }


    /**
     * 3.模块选项卡接口
     *
     * @Author gp
     * @Date 2017/5/22
     */
    @ApiOperation("3.模块选项卡接口")
    @PostMapping("/moduleTab")
    public String moduleTab(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                            Model model){
        List<Map<String, Object>> resList = new ArrayList<>();
        String[] aaa = {"0101,全部", "0102,日","0103,月"};
        for (int i = 0; i < aaa.length; i ++){
            String[] a = aaa[i].split(",");
            Map<String, Object> map = new HashMap<>(5);
            map.put("tabId", a[0]);
            map.put("tabName", a[1]);
            resList.add(map);
        }
        //return resList;
        model.addAttribute("resList", resList);
        return "moduleTab";
    }



    /**
     * 4-1.近期访问-筛选分类接口
     *
     * @Author gp
     * @Date 2017/5/23
     */
    @ApiOperation("4-1.近期访问-筛选分类接口")
    @PostMapping("/recentVisit")
    public String recentVisit(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                              Model model){
        Map<String, Object> resMap = new HashMap<>(5);
        String[] a = {"01,综合,/homePage", "02,指标,/index", "03,专题,/special", "04,报告,/report"};
        List<Map<String, Object>> selectList = new ArrayList<>();
        for (int i = 0; i < a.length; i ++){
            String[] b = a[i].split(",");
            Map<String, Object> map = new HashMap<>(10);
            map.put("id", b[0]);
            map.put("name", b[1]);
            map.put("url", b[2]);
            selectList.add(map);
        }
        resMap.put("default", selectList.get(0));
        resMap.put("selectList", selectList);

        //return resMap;
        model.addAttribute("resMap", resMap);
        return "recentVisit";
    }


    /**
     * 4-2.近期访问-访问内容接口
     *
     * @Author gp
     * @Date 2017/5/23
     */
    @ApiOperation("4-2.近期访问-访问内容接口")
    @PostMapping("/recentVisitList")
    public String recentVisitList(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                                  Model model){
        Map<String, Object> resMap = new HashMap<>(5);
        String[] a = {"专题,301,线下实体渠道发展用户,/special,0101",
                      "指标,201,20M及以上速率发展用户数,/index,0101",
                      "报告,401,移动业务发展用户,/report,0101"};
        List<Map<String, Object>> recentVisitList = new ArrayList<>();
        for (int i = 0; i < a.length; i ++){
            String[] b = a[i].split(",");
            Map<String, Object> map = new HashMap<>(10);
            map.put("class", b[0]);
            map.put("detailId", b[1]);
            map.put("detailName", b[2]);
            map.put("detailUrl", b[3]);
            map.put("detailFlag", b[4]);
            recentVisitList.add(map);
        }
        resMap.put("recentVisitList", recentVisitList);
        //return resMap;
        model.addAttribute("resMap", resMap);
        return "recentVisitList";
    }


    /**
     * 6-1.搜索-全部搜索接口
     *
     * @Author gp
     * @Date 2017/5/23 
     */
    @ApiOperation("6-1.搜索-全部搜索接口")
    @PostMapping("/allSearch")
    public String allSearch(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                            Model model){
        Map<String, Object> resMap = new HashMap<>(5);
        List<Map<String, Object>> resList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>(20);

        map1.put("markType", "1");
        map1.put("ord", "1");
        map1.put("id", "2001");
        map1.put("url", "http://ip:port/indexDetails/1001");
        Map<String, Object> dataMap = new HashMap<>(20);
        dataMap.put("markName", "指标");
        dataMap.put("title ", "移动业务计费收入");
        dataMap.put("dayOrMonth", "日");
        dataMap.put("area", "全国");
        dataMap.put("date", "2017年5月10日");
        List<String> dataNameList = new ArrayList<>();
        String[] a = {"当日值", "本月累计", "同比", "环比"};
        for (String b : a){
            dataNameList.add(b);
        }
        dataMap.put("dataName", dataNameList);
        List<String> dataValueList = new ArrayList<>();
        String[] a1 = {"1234","1234","56%","12%"};
        for (String b1 : a1){
            dataValueList.add(b1);
        }
        dataMap.put("dataValue", dataValueList);
        dataMap.put("chartType", "line");
        dataMap.put("unit", "万");
        List<Map<String, Object>> chartList = new ArrayList<>();
        Map<String, Object> chartMap = new HashMap<>(20);
        List<Integer> chartDataList = new ArrayList<>();
        int num = 7;
        for (int i = 1; i <= num; i ++){
            chartDataList.add(i);
        }
        chartMap.put("data", chartDataList);
        String[] a2 = {"4月30日", "5月1日", "5月2日", "5月3日", "5月4日", "5月5日", "5月6日"};
        List<String> chartXList = new ArrayList<>();
        for (String b2 : a2){
            chartXList.add(b2);
        }
        chartMap.put("chartX", chartXList);
        chartList.add(chartMap);
        dataMap.put("chart", chartList);
        map1.put("data", dataMap);
        resList.add(map1);

        Map<String, Object> map2 = new HashMap<>(20);
        map2.put("markType", "2");
        map2.put("ord", "2");
        map2.put("id", "3001");
        map2.put("url", "http://ip:port/indexDetails/2001");
        Map<String, Object> dataMap2 = new HashMap<>(10);
        dataMap2.put("src", "u97.png");
        dataMap2.put("title", "4G用户专题");
        dataMap2.put("content", "包括全国整体业务发展状况、用户获取、用户迁转、流量价值释放业务的发展情况及宽带业务运营的主要月指标展示");
        dataMap2.put("type", "专题");
        dataMap2.put("tabName", "全部");
        map2.put("data", dataMap2);
        resList.add(map2);

        Map<String, Object> map3 = new HashMap<>(20);
        map3.put("markType", "3");
        map3.put("ord", "3");
        map3.put("id", "4001");
        map3.put("url", "http://ip:port/indexDetails/4001");
        Map<String, Object> dataMap3 = new HashMap<>(15);
        dataMap3.put("title", "2G终端入网质态分析");
        dataMap3.put("type", "报告");
        dataMap3.put("tabName", "月");
        dataMap3.put("issue", "张三");
        dataMap3.put("issueTime", "2017年5月10日");
        String paramStr = "R_004";
        RestTemplate restTemplateTmp = new RestTemplate();
        List<Map<String, Object>> data = restTemplateTmp.postForObject("http://192.168.31.7:7333/pptReportForHomepage/info", paramStr, List.class);
        List<String> imgList = (List<String>) data.get(0).get("img");

        dataMap3.put("img", imgList);
        map3.put("data", dataMap3);
        resList.add(map3);
        resMap.put("data", resList);
        resMap.put("nextFlag", "0");
        //return resList;
        System.out.println(resMap);
        model.addAttribute("resMap", resMap);
        return "allSearch";
    }


    /**
     * 6-2.搜索-指标搜索接口
     *
     * @Author gp
     * @Date 2017/5/23
     */
    @ApiOperation("6-2.搜索-指标搜索接口")
    @PostMapping("/indexSearch")
    public String indexSearch(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                              Model model){
        List<Map<String, Object>> resList = new ArrayList<>();
        Map<String, Object> resMap = new HashMap<>(20);
        Map<String, Object> map1 = new HashMap<>(10);
        map1.put("area", "全国");
        map1.put("date", "2017年12月23日");
        map1.put("dayOrMonth", "日报");
        map1.put("id", "CKP_23323");
        map1.put("isMinus", "0");
        map1.put("isPercentage", "0");
        map1.put("markName", "指标");
        map1.put("markType", "1");
        map1.put("ord", "1");
        map1.put("title", "移动业务计费收入");
        map1.put("unit", "万元");
        map1.put("url", "/indexDetails");
        List<String> dataName = new ArrayList<>();
        dataName.add("当日值");
        dataName.add("本月累计");
        dataName.add("同比");
        dataName.add("环比");
        List<String> dataValue = new ArrayList<>();
        dataValue.add("1234");
        dataValue.add("123");
        dataValue.add("56%");
        dataValue.add("78%");
        map1.put("dataName", dataName);
        map1.put("dataValue", dataValue);
        List<Map<String, Object>> chartDataList = new ArrayList<>();
        Map<String, Object> chartDataMap1 = new HashMap<>(5);
        chartDataMap1.put("chartType", "line");
        chartDataMap1.put("unit", "万");

        List<Map<String, Object>> chartList = new ArrayList<>();
        Map<String, Object> chartMap = new HashMap<>(15);
        List<Integer> dataList = new ArrayList<>();
        int num = 8;
        for (int i = 1; i < num; i ++){
            dataList.add(i);
        }
        chartMap.put("data", dataList);
        String[] a = {"20171217", "20171218", "20171219", "20171220", "20171221", "20171222", "20171223"};
        List<String> chartXList = new ArrayList<>();
        for (String b : a){
            chartXList.add(b);
        }
        chartMap.put("chartX", chartXList);
        chartList.add(chartMap);
        chartDataMap1.put("chart", chartList);
        chartDataList.add(chartDataMap1);

        Map<String, Object> chartDataMap2 = new HashMap<>(15);
        chartDataMap2.put("chartType", "monthBar");
        chartDataMap2.put("unit", "万");
        List<Map<String, Object>> chartList2 = new ArrayList<>();
        Map<String, Object> chartMap2 = new HashMap<>(15);

        List<String> yoyData = new ArrayList<>();
        String[] bb = {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
        for (String b : bb){
            yoyData.add(b);
        }
        chartMap2.put("yoyData", yoyData);
        List<String> chartXList1 = new ArrayList<>();
        String[] a2 = {"201701", "201702", "201703", "201704", "201705", "201706", "201707", "201708", "201709", "201710","201711","201712"};
        for (String b : a2){
            chartXList1.add(b);
        }
        chartMap2.put("chartX", chartXList1);
        List<String> example = new ArrayList<>();
        example.add("本月累计");
        example.add("累计环比");
        example.add("累计同比");
        chartMap2.put("example", example);
        List<Integer> sequentialDataList = new ArrayList<>();
        int numFor = 13;
        for (int i = 1; i < numFor; i ++){
            sequentialDataList.add(i);
        }
        chartMap2.put("sequentialData", sequentialDataList);
        chartMap2.put("totalData", sequentialDataList);
        chartList2.add(chartMap2);
        chartDataMap2.put("chart", chartList2);
        chartDataList.add(chartDataMap2);

        Map<String, Object> chartDataMap3 = new HashMap<>(15);
        chartDataMap3.put("chartType", "cityBar");
        chartDataMap3.put("unit", "万");
        List<Map<String, Object>> chartList3 = new ArrayList<>();
        Map<String, Object> chartMap3 = new HashMap<>(15);
        chartMap3.put("sequentialData", sequentialDataList);
        chartMap3.put("totalData", sequentialDataList);
        chartMap3.put("chartX", chartXList1);
        chartMap3.put("example", example);
        chartList3.add(chartMap3);
        chartDataMap3.put("chart", chartList3);
        chartDataList.add(chartDataMap3);

        Map<String, Object> chartDataMap4 = new HashMap<>(10);
        chartDataMap4.put("chartType", "product");
        chartDataMap4.put("unit", "万");

        List<Map<String, Object>> dataList1 = new ArrayList<>();
        String[] a3 = {"流量王A套餐发展用户,182", "日租卡套餐发展用户,34",
                "其他套餐发展用户,260", "2I2C业务发展用户,102"};
        for (String b : a3){
            String[] s = b.split(",");
            Map<String, Object> map = new HashMap<>(5);
            map.put("name", s[0]);
            map.put("value", s[1]);
            dataList1.add(map);
        }
        chartDataMap4.put("chart", dataList1);
        chartDataList.add(chartDataMap4);

        Map<String, Object> chartDataMap5 = new HashMap<>(15);
        chartDataMap5.put("chartType", "cityRank");
        chartDataMap5.put("unit", "万");
        List<Map<String, Object>> chartList5 = new ArrayList<>();
        Map<String, Object> chartMap5 = new HashMap<>(15);
        List<String> tableTitleList = new ArrayList<>();
        String[] a4 = {"排名","城市","月累","环比"};
        for (String b : a4){
            tableTitleList.add(b);
        }
        chartMap5.put("tableTitle", tableTitleList);
        List<Map<String, Object>> tableValueList = new ArrayList<>();
        List<String> value1 = new ArrayList<>();
        value1.add("123456789");
        value1.add("12%");
        String[] a5 = {"1,北京", "2,上海"};
        for (String b : a5){
            String[] s = b.split(",");
            Map<String, Object> map = new HashMap<>(5);
            map.put("rank", s[0]);
            map.put("cityName", s[1]);
            map.put("value", value1);
            tableValueList.add(map);
        }
        chartMap5.put("tableValue", tableValueList);
        chartList5.add(chartMap5);
        chartDataMap5.put("chart", chartList5);
        chartDataList.add(chartDataMap5);
        map1.put("chartData", chartDataList);
        List<Map<String, Object>> selectTypeDisplayList = new ArrayList<>();
        Map<String, Object> selectTypeDisplayMap1 = new HashMap<>(10);
        selectTypeDisplayMap1.put("1", "电子渠道&社会渠道");
        selectTypeDisplayMap1.put("2", "");
        selectTypeDisplayList.add(selectTypeDisplayMap1);
        map1.put("selectTypeDisplay", selectTypeDisplayList);
        List<Map<String, Object>> deleteDisplayList = new ArrayList<>();
        Map<String, Object> deleteDisplayMap = new HashMap<>(10);
        deleteDisplayMap.put("3", "4G业务&3G业务&2G业务");
        deleteDisplayList.add(deleteDisplayMap);
        map1.put("deleteDisplay", deleteDisplayList);
        List<Map<String, Object>> dimensionList = new ArrayList<>();
        Map<String, Object> dimensionMap = new HashMap<>(10);
        List<Map<String, Object>> selectTypeList = new ArrayList<>();
        Map<String, Object> selectTypeMap1 = new HashMap<>(10);
        selectTypeMap1.put("1", new ArrayList<>());
        Map<String, Object> selectTypeMap2 = new HashMap<>(10);
        selectTypeMap2.put("2", new ArrayList<>());
        Map<String, Object> selectTypeMap3 = new HashMap<>(10);
        selectTypeMap3.put("3", new ArrayList<>());
        selectTypeList.add(selectTypeMap1);
        selectTypeList.add(selectTypeMap2);
        selectTypeList.add(selectTypeMap3);
        dimensionMap.put("selectType", selectTypeList);
        dimensionMap.put("date", "2017-09");
        dimensionMap.put("cityId", "-1");
        dimensionMap.put("provId", "018");
        dimensionList.add(dimensionMap);
        map1.put("dimension", dimensionList);

        Map<String, Object> map2 = new HashMap<>(20);
        map2.put("area", "全国");
        map2.put("chartType", "cityRank");
        map2.put("date", "2017年5月");
        map2.put("dayOrMonth", "月报");
        map2.put("id", "M_CKP_01");
        map2.put("isMinus", "0");
        map2.put("isPercentage", "0");
        map2.put("markName", "指标");
        map2.put("markType", "1");
        map2.put("ord", "2");
        map2.put("title", "主营业务收入");
        map2.put("unit", "万元");
        map2.put("url", "/indexDetails");
        String[] a6 = {"当日值","本月累计","同比","环比"};
        List<String> dataNameList = new ArrayList<>();
        for (String b : a6){
            dataNameList.add(b);
        }
        map2.put("dataName", dataNameList);
        String[] a7 = {"1234","1234","56%","12%"};
        List<String> dataValueList = new ArrayList<>();
        for(String b : a7){
            dataValueList.add(b);
        }
        map2.put("dataValue", dataValueList);
        map2.put("chart", chartList5);
        map2.put("selectTypeDisplay", selectTypeDisplayList);
        map2.put("deleteDisplay", deleteDisplayList);
        map2.put("dimension", dimensionList);

        Map<String, Object> map3 = new HashMap<>(15);
        map3.put("area", "全国");
        map3.put("chartType", "line");
        map3.put("date", "2017年5月12日");
        map3.put("dayOrMonth", "日报");
        map3.put("id", "CKP_23333");
        map3.put("isMinus", "0");
        map3.put("isPercentage", "0");
        map3.put("markName", "指标");
        map3.put("markType", "1");
        map3.put("ord", "3");
        map3.put("title", "4g业务计费收入");
        map3.put("unit", "万元");
        map3.put("url", "/indexDetails");
        map3.put("dataName", dataNameList);
        map3.put("dataValue", dataValueList);
        map3.put("chart", chartList);
        map3.put("selectTypeDisplay", selectTypeDisplayList);
        map3.put("deleteDisplay", deleteDisplayList);
        map3.put("dimension", dimensionList);

        resList.add(map1);
        resList.add(map2);
        resList.add(map3);

        resMap.put("nextFlag", "0");
        resMap.put("data", resList);

        List<Map<String, Object>> keywordList = new ArrayList<>();
        Map<String, Object> keywordMap = new HashMap<>(10);
        keywordMap.put("flag", "true");
        List<Map<String, Object>> keyDataList = new ArrayList<>();
        Map<String, Object> keyDataMap = new HashMap<>(10);
        keyDataMap.put("kpiName", "移动业务计费收入");
        keyDataMap.put("date", "2017年10月25日");
        keyDataMap.put("prov", "山西");
        keyDataMap.put("city", "");
        keyDataMap.put("1", "实体渠道&电子渠道");
        keyDataMap.put("2", "存费送机");
        keyDataMap.put("3", "3G业务&4G业务");
        keyDataList.add(keyDataMap);
        keywordMap.put("keyData", keyDataMap);
        List<Map<String, Object>> recommendDataList = new ArrayList<>();
        Map<String, Object> recommendDataMap = new HashMap<>(10);
        recommendDataMap.put("kpiName", "移动业务计费收入");
        recommendDataMap.put("date", "2017年10月25日");
        recommendDataMap.put("prov", "山西");
        recommendDataMap.put("city", "");
        recommendDataMap.put("1", "实体渠道&电子渠道");
        recommendDataMap.put("2", "存费送机");
        recommendDataMap.put("3", "3G业务&4G业务");
        recommendDataList.add(recommendDataMap);
        keywordMap.put("recommendData", recommendDataMap);
        keywordList.add(keywordMap);
        resMap.put("keyword", keywordList);
        //return resList;
        System.out.println(resMap);
        model.addAttribute("resMap", resMap);
        return "indexSearch";
    }


    /**
     * 6-3.搜索-专题搜索接口
     *
     * @Author gp
     * @Date 2017/5/23
     */
    @ApiOperation("6-3.搜索-专题搜索接口")
    @PostMapping("/specialSearch")
    public String specialSearch(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                                Model model){
        List<Map<String, Object>> resList = new ArrayList<>();
        Map<String, Object> resMap = new HashMap<>(5);
        String[] a = {"u977.png|重点产品攻坚行动日报表|1|3001|http://ip:port/specialReport/3001|包括全国整体业务发展状况、用户获取、用户迁转、流量价值释放业务的发展情况及宽带业务运营的主要日指标展示。|专题|全部",
                      "u97.png|4G用户专题|2|3002|http://ip:port/specialReport/3002|包括全国整体业务发展状况、用户获取、用户迁转、流量价值释放业务的发展情况及宽带业务运营的主要月指标展示|专题|全部",
                      "u999.png|重点产品攻坚行动月考核|3|3003|http://ip:port/specialReport/3003|重点产品有效发展行动计划通报内容，包括各省考核指标的评分结果及完成情况。|专题|全部",
                      "u1010.png|重点产品攻坚行动月报表|4|3004|http://ip:port/specialReport/3004|包括全国整体业务发展状况、用户获取、用户迁转、流量价值释放业务的发展情况及宽带业务运营的主要月指标展示。|专题|全部",
                      "u9778.png|重点产品攻坚行动日报表|5|3005|http://ip:port/specialReport/3005|包括全国整体业务发展状况、用户获取、用户迁转、流量价值释放业务的发展情况及宽带业务运营的主要日指标展示。|专题|全部"};
        for (int i = 0; i < a.length; i ++){
            String[] s = a[i].split("\\|");
            Map<String, Object> map = new HashMap<>(15);
            map.put("src", s[0]);
            map.put("title", s[1]);
            map.put("ord", s[2]);
            map.put("id", s[3]);
            map.put("url", s[4]);
            map.put("content", s[5]);
            map.put("type", s[6]);
            map.put("tabName", s[7]);
            resList.add(map);
        }
        resMap.put("nextFlag", "0");
        resMap.put("data", resList);
        //return resList;
        System.out.println(resMap);
        model.addAttribute("resMap", resMap);
        return "specialSearch";
    }


    /**
     * 6-4.搜索-报告搜索接口
     *
     * @Author gp
     * @Date 2017/5/23
     */
    @ApiOperation("6-4.搜索-报告搜索接口")
    @PostMapping("/reportSearch")
    public String reportSearch(@ApiParam("json")@RequestBody Map<String, Object> paramMap,
                               Model model){
        List<Map<String, Object>> resList = new ArrayList<>();
        Map<String, Object> resMap = new HashMap<>(5);
        String[] a = {"2G终端入网质态分析|1|4001|http://ip:port/Report/4001|报告|月|张三|2017年5月10日",
                      "渠道成本效益分析|2|4002|http://ip:port/Report/4002|报告|月|李四|2017年5月10日",
                      "移动用户离网分析|3|4003|http://ip:port/Report/4003|报告|月|王五|2017年5月10日",
                      "融合业务发展因素分析|4|4004|http://ip:port/Report/4004|报告|月|赵六|2017年5月10日"};
        String paramStr = "R_004";
        RestTemplate restTemplateTmp = new RestTemplate();
        List<Map<String, Object>> data = restTemplateTmp.postForObject("http://10.249.216.52:8033/pptReportForHomepage/info", paramStr, List.class);
        List<String> imgList = (List<String>) data.get(0).get("img");
        for (String b : a){
            String[] s = b.split("\\|");
            Map<String, Object> map = new HashMap<>(15);
            map.put("title", s[0]);
            map.put("ord", s[1]);
            map.put("id", s[2]);
            map.put("url", s[3]);
            map.put("img", imgList);
            map.put("type", s[4]);
            map.put("tabName", s[5]);
            map.put("issue", s[6]);
            map.put("issueTime", s[7]);
            resList.add(map);
        }
        resMap.put("nextFlag", "0");
        resMap.put("data", resList);
        System.out.println(resMap);
        //return resList;
        model.addAttribute("resMap", resMap);
        return "reportSearch";
    }



    /**
     * 循环放titleList的方法
     *
     * @Author gp
     * @Date 2017/5/22
     */
    public List<Map<String, Object>> titleList(String[] title){
        List<Map<String, Object>> titleList = new ArrayList<>();
        for (int i = 0; i < title.length; i ++){
            String[] a = title[i].split(",");
            Map<String, Object> map = new HashMap<>(5);
            map.put("titleId", a[0]);
            map.put("titleName", a[1]);
            map.put("titleUrl", a[2]);
            map.put("flag", a[3]);
            titleList.add(map);
        }
        return titleList;
    }


    /**
     * 循环放nodeList的方法
     *
     * @Author gp
     * @Date 2017/5/22
     */
    public List<Map<String, Object>> nodeList(String[] aaa){
        List<Map<String, Object>> nodeList = new ArrayList<>();
        for (int i = 0; i < aaa.length; i ++){
            String[] a = aaa[i].split(",");
            Map<String, Object> map = new HashMap<>(5);
            map.put("nodeId", a[0]);
            map.put("nodeName", a[1]);
            map.put("nodeUrl", a[2]);
            map.put("flag", a[3]);
            nodeList.add(map);
        }
        return nodeList;
    }


}