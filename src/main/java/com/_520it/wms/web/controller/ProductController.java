package com._520it.wms.web.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com._520it.wms.domain.Product;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.query.QueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.util.JSONResult;
import com._520it.wms.util.UploadUtil;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	@Autowired
	private IBrandService brandService;
	@Autowired
	private ServletContext context;

	@RequiredPermission("货品列表")
	@RequestMapping("/list")
	public String list(Model model,@ModelAttribute("qo")ProductQueryObject qo) {
		model.addAttribute("pageResult", productService.query(qo));
		model.addAttribute("brands", brandService.listAll());
		return "product/list";
	}

	@RequiredPermission("货品添加或者编辑")
	@RequestMapping("/input")
	public String input(Model model, Long id) {
		if (id != null) {
			model.addAttribute("product", productService.get(id));
		}
		model.addAttribute("brands", brandService.listAll());
		return "product/input";
	}

	@RequestMapping("/saveOrUpdate")
	@ResponseBody
	public JSONResult saveOrUpdate(Product product, MultipartFile pic) {
		String realPath = context.getRealPath("/upload");
		System.out.println(product);
		//如果选择了图片,且之前有图片,就删除原来的图片
		if (pic != null && product.getImagePath()!=null) {
			UploadUtil.deleteFile(context, product.getImagePath());
		}
		if (pic != null) {
			//上传图片到真实路径,返回图片的相对路径 return "/upload/" + fileName;
			String imagePath = UploadUtil.upload(pic, realPath);
			product.setImagePath(imagePath);
		}
		productService.saveOrUpdate(product);
		return new JSONResult();
	}

	@RequiredPermission("货品删除")
	@RequestMapping("/delete")
	@ResponseBody
	public JSONResult delete(Long id) {
		//删除项目中存放的图片
		if(productService.get(id).getImagePath()!=null){
			UploadUtil.deleteFile(context, productService.get(id).getImagePath());
		}
		if (id != null) {
			productService.delete(id);
		}
		return new JSONResult();
	}
}
