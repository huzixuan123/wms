package com._520it.wms.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *	货品信息
 */
@Setter
@Getter
@ToString
public class Product{
	private Long id;
	private String name;
	private String sn;
	private BigDecimal costPrice;
	private BigDecimal salePrice;
	private String imagePath;
	private String intro;
	private Long brandId;
	private String brandName;
	
	//  upload/123.jpg
	public String getSmallImagePath(){
		if(imagePath==null){
			return "";
		}else{
			return imagePath.substring(0,imagePath.indexOf("."))+
					"_small"+
						imagePath.substring(imagePath.indexOf("."));
		}
	}
	
	//返回当前货品对象的JSON格式数据
	public String getJsonString(){
		Map<String, Object> map = new HashMap<>();
		map.put("id",id);
		map.put("name",name);
		map.put("brandName", brandName);
		map.put("costPrice",costPrice);
		map.put("salePrice",salePrice);
		return JSON.toJSONString(map);
	} 
}
