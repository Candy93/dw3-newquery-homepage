package com.bonc.dw3.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    /**
     * 搜索接口-全部
     * @Parameter searchType 查询类型：-1.全部; 1.专题; 2.报告; 3.专题
     * @Parameter txt 查询输入的文本
     * @Parameter startNum 分页起始
     * @Parameter num 分页每一页的条数
     *
     * @Author gp
     * @Date 2017/5/16
     */
    @ApiOperation("搜索接口")
    @PostMapping("/search")
    public String search(@ApiParam("查询类型")@RequestParam("searchType")String searchType,
                         @ApiParam("查询关键词")@RequestParam("str")String str,
                         @ApiParam("分页起始")@RequestParam("startNum")String startNum,
                         @ApiParam("每一页记录条数")@RequestParam("num")String num,
                         Model model){
        //es查询参数处理
        String paramStr = searchType + "," + str + "," + startNum + "," + num;
        System.out.println("查询es的参数--------->" + paramStr);

        //查询es
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.postForObject("http://192.168.110.57:7070/es/explore", paramStr, Object.class);
        List<Map<String, Object>>  esList = (List<Map<String, Object>>) res;
        System.out.println("查询es的结果-------->" + esList);

        //searchType:-1.全部; 1.指标; 2.报告; 3.专题
        if (searchType.equals("-1")){
            List<Map<String, Object>> topicList = new ArrayList<>();
            List<Map<String, Object>> reportList = new ArrayList<>();
            List<Map<String, Object>> kpiList = new ArrayList<>();
            //查询类型是全部，需要遍历所有的数据，查看它们的type是什么，送到相应的服务
            for (Map<String, Object> esMap : esList){
                String type = esMap.get("Type").toString();
                if (type.equals("KPI_Name")){
                    kpiList.add(esMap);
                }else if (type.equals("Report_Name")){
                    reportList.add(esMap);
                }else if (type.equals("Topic_Name")){
                    topicList.add(esMap);
                }
            }
            System.out.println("专题有-------->" + topicList);
            System.out.println("报告有-------->" + reportList);
            System.out.println("kpi有-------->" + kpiList);
            //是专题的查询相应的图片


            //是报告的送到报告服务查询4个缩略图

            //是指标的送到指标服务查询同比环比等
                //如果指标数据条数超过20，需要把它们拆分，分配到几台机器查询同比环比数据
                //if

                //指标数据条数不超过20，不需要拆分，分配到第一台机器查询同比环比数据
                //else

            //master汇总结果


        }else if (searchType.equals("KPI_Name")){
            //查询类型是指标，需要把它们拆分，分配到几台机器查询同比环比数据
            //同样判断数据条数是否超过20，超过就拆分，不超过就分配到第一台机器查询同比环比数据
            for (Map<String, Object> esMap : esList){

            }
            //master汇总结果

        }else if (searchType.equals("Report_Name")){
            //查询类型是报告，送到报告服务查询4个缩略图

            //master汇总结果

        }else if (searchType.equals("Topic_Name")){
            //查询类型是专题，送到专题服务查询相应的图片

            //master汇总结果
        }
        return "search";
    }

/*




    
	@Autowired
	MonthReportByKylinService monthReportService;
	
	*//**
	 * 筛选条件接口
	 * @param model
	 * @return
	 *//*
	@ApiOperation("筛选条件接口")
	@PostMapping("/select")
	public String select(Model model){
		List<Map<String,Object>> resList = monthReportService.select();
		model.addAttribute("resList", resList);
		return "select";
	}

	*//**
	 * 指标数据接口
	 * @param area
	 * @param prov
	 * @param city
	 * @param client
	 * @param channel
	 * @param contract
	 * @param date
	 * @param model
	 * @return
	 * @throws ParseException 
	 *//*
	@ApiOperation("指标数据接口")
	@PostMapping("/data")
	public String kpiData(@ApiParam("地市编码") @RequestParam("area") String area,
			@ApiParam("省份编码") @RequestParam("prov") String prov,
			@ApiParam("city编码") @RequestParam("city") String city,
			@ApiParam("客户编码") @RequestParam("client") List<String> client,
			@ApiParam("渠道编码") @RequestParam("channel")  List<String> channel,
			@ApiParam("合约编码") @RequestParam("contract") List<String> contract,
			@ApiParam("账期") @RequestParam("date") String date,
			Model model) throws ParseException{
		Map<String,Object> kpiMap = monthReportService.kpiData(area, prov,city, client, channel, contract,date);
		model.addAttribute("kpiMap", kpiMap);
		return "kpi";
	}

	*//**
	 * 指标右键下钻接口
	 * @param area
	 * @param prov
	 * @param city
	 * @param client
	 * @param channel
	 * @param contract
	 * @param date
	 * @param kid
	 * @param rightclick
	 * @param model
	 * @return
	 * @throws ParseException 
	 *//*
	@ApiOperation("指标右键下钻")
	@PostMapping("/kpiData")
	public String rightKpi(@ApiParam("地市编码") @RequestParam("area") String area,
			@ApiParam("省份编码") @RequestParam("prov") String prov,
			@ApiParam("city编码") @RequestParam("city") String city,
			@ApiParam("客户编码") @RequestParam("client") List<String> client,
			@ApiParam("渠道编码") @RequestParam("channel")  List<String> channel,
			@ApiParam("合约编码") @RequestParam("contract") List<String> contract,
			@ApiParam("账期") @RequestParam("date") String date,
			@ApiParam("指标id") @RequestParam("kid") String kid,
			@ApiParam("右击选择类型") @RequestParam("rightclick") String rightclick,
			Model model) throws ParseException{
		List<Map<String,Object>> kpiDataList = monthReportService.rightKpi(area, prov, city, client, channel, contract, date, kid, rightclick);
		model.addAttribute("kpiDataList", kpiDataList);
		return "rightClick";
	}
	
	*//**
	 * 地势下钻
	 * @param area
	 * @param prov
	 * @param city
	 * @param client
	 * @param channel
	 * @param contract
	 * @param date
	 * @param kid
	 * @param proid
	 * @param model
	 * @return
	 * @throws ParseException 
	 *//*
	@ApiOperation("省份下钻地势接口")
	@PostMapping("/cityData")
	public String cityData(@ApiParam("地市编码") @RequestParam("area") String area,
			@ApiParam("省份编码") @RequestParam("prov") String prov,
			@ApiParam("city编码") @RequestParam("city") String city,
			@ApiParam("客户编码") @RequestParam("client") List<String> client,
			@ApiParam("渠道编码") @RequestParam("channel")  List<String> channel,
			@ApiParam("合约编码") @RequestParam("contract") List<String> contract,
			@ApiParam("账期") @RequestParam("date") String date,
			@ApiParam("指标id") @RequestParam("kid") String kid,
			@ApiParam("省份id") @RequestParam("proid") String proid,
			Model model) throws ParseException{
		List<Map<String,Object>> kpiDataList = monthReportService.cityData(area, prov, city, client, channel, contract, date, kid,proid);
		model.addAttribute("kpiDataList", kpiDataList);
		return "cityData";
	}

	*//**
	 * 趋势图
	 * @param area
	 * @param prov
	 * @param city
	 * @param client
	 * @param channel
	 * @param contract
	 * @param kid
	 * @param date
	 * @param model
	 * @return
	 * @throws ParseException 
	 *//*
	@ApiOperation("趋势图")
	@PostMapping("/trend")
	public String trend(@ApiParam("地市编码") @RequestParam("area") String area,
			@ApiParam("省份编码") @RequestParam("prov") String prov,
			@ApiParam("city编码") @RequestParam("city") String city,
			@ApiParam("客户编码") @RequestParam("client") List<String> client,
			@ApiParam("渠道编码") @RequestParam("channel")  List<String> channel,
			@ApiParam("合约编码") @RequestParam("contract") List<String> contract,
			@ApiParam("指标id") @RequestParam("kid") String kid,
			@ApiParam("账期") @RequestParam("date") String date,
			Model model) throws ParseException{
		 Map<String,Object> trendMap = monthReportService.trend(area, prov, city, client, channel, contract, kid,date);
		 model.addAttribute("trendMap", trendMap);
	     return "trend";
	}

	*//**
	 * 合约情况占比
	 * @param area
	 * @param prov
	 * @param city
	 * @param client
	 * @param channel
	 * @param contract
	 * @param kid
	 * @param date
	 * @param model
	 * @return
	 *//*
	@ApiOperation("合约情况占比")
	@PostMapping("/contract")
	public String contract(@ApiParam("地市编码") @RequestParam("area") String area,
			@ApiParam("省份编码") @RequestParam("prov") String prov,
			@ApiParam("city编码") @RequestParam("city") String city,
			@ApiParam("客户编码") @RequestParam("client") List<String> client,
			@ApiParam("渠道编码") @RequestParam("channel")  List<String> channel,
			@ApiParam("合约编码") @RequestParam("contract") List<String> contract,
			@ApiParam("指标id") @RequestParam("kid") String kid,
			@ApiParam("账期") @RequestParam("date") String date,Model model){
		List<Map<String, Object>> contractList = monthReportService.contract(area, prov,city, client, channel, contract, kid,date);
		model.addAttribute("contractList", contractList);
		return "contract";
	}
	
	*//**
	 * 公众集客占比
	 * @param area
	 * @param prov
	 * @param city
	 * @param client
	 * @param channel
	 * @param contract
	 * @param kid
	 * @param date
	 * @param model
	 * @return
	 *//*
	@ApiOperation("公众集客占比")
	@PostMapping("/client")
	public String client(@ApiParam("地市编码") @RequestParam("area") String area,
			@ApiParam("省份编码") @RequestParam("prov") String prov,
			@ApiParam("city编码") @RequestParam("city") String city,
			@ApiParam("客户编码") @RequestParam("client") List<String> client,
			@ApiParam("渠道编码") @RequestParam("channel")  List<String> channel,
			@ApiParam("合约编码") @RequestParam("contract") List<String> contract,
			@ApiParam("指标id") @RequestParam("kid") String kid,
			@ApiParam("账期") @RequestParam("date") String date,Model model){
		List<Map<String, Object>> clientList = monthReportService.client(area, prov, city, client, channel, contract, kid, date);
	    model.addAttribute("clientList", clientList);
		return "client";
	}
	
	*//**
	 * 渠道分类占比
	 * @param area
	 * @param prov
	 * @param city
	 * @param client
	 * @param channel
	 * @param contract
	 * @param kid
	 * @param date
	 * @param model
	 * @return
	 *//*
	@ApiOperation("渠道分类占比")
	@PostMapping("/channel")
	public String channel(@ApiParam("地市编码") @RequestParam("area") String area,
			@ApiParam("省份编码") @RequestParam("prov") String prov,
			@ApiParam("city编码") @RequestParam("city") String city,
			@ApiParam("客户编码") @RequestParam("client") List<String> client,
			@ApiParam("渠道编码") @RequestParam("channel")  List<String> channel,
			@ApiParam("合约编码") @RequestParam("contract") List<String> contract,
			@ApiParam("指标id") @RequestParam("kid") String kid,
			@ApiParam("账期") @RequestParam("date") String date,Model model){
		List<Map<String, Object>> channelList = monthReportService.channel(area,prov, city, client, channel, contract, kid, date);
		model.addAttribute("channelList", channelList);
	    return "channel";
	}
	

	*//**
	 * 默认图标Kid接口
	 * @param model
	 * @return
	 *//*
	@ApiOperation(" 默认图标Kid接口")
	@PostMapping("/kid")
	public String kid(Model model){
		Map<String,String> kidMap = monthReportService.kid();
		model.addAttribute("kidMap", kidMap);
		return "kid";
	}
	*/
	
}