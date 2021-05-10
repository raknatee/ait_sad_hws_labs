package com.example.finalexam.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.finalexam.Dao.ProductDAO;
import com.example.finalexam.Service.ProductService;
import com.example.finalexam.model.Product;

import lombok.Data;


@Controller
public class HomeController {
	
	@Autowired
	private ProductDAO pDao;
	
	@Autowired
	private ProductService ProductService;
	
	@RequestMapping(path = "/p",method=RequestMethod.GET)
	public ModelAndView homeP() {
		ModelAndView mv =  new ModelAndView("/home.jsp");
		List<Product> products = pDao.findAll();
		mv.addObject("products",products);
		mv.addObject("locking_type","pessimistic");
		return mv;
	}
	@RequestMapping(path = "/o",method=RequestMethod.GET)
	public ModelAndView homeO() {
		ModelAndView mv =  new ModelAndView("/home.jsp");
		List<Product> products = pDao.findAll();
		mv.addObject("products",products);
		mv.addObject("locking_type","optimistic");
		return mv;
	}
	
	@Data
	class ObjectBuy{
		private int productID;
		private int n_buy;
	}
	
	@RequestMapping(path= "/pessimistic",method=RequestMethod.POST)
	public String buyP(ObjectBuy obj) {
		ProductService.buyPessimistic(obj.productID, obj.n_buy);
		return "redirect:/p";
	}
	@RequestMapping(path= "/optimistic",method=RequestMethod.POST)
	public String buyO(ObjectBuy obj) {
		ProductService.buyOptimistic(obj.productID, obj.n_buy);
		return "redirect:/o";
	}
}
