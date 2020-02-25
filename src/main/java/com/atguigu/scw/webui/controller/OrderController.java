package com.atguigu.scw.webui.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.common.bean.AppResponse;
import com.atguigu.common.consts.ScwAppconsts;
import com.atguigu.scw.webui.config.AlipayConfig;
import com.atguigu.scw.webui.service.OrderServiceFeignClient;
import com.atguigu.scw.webui.service.UserServiceFeignClient;
import com.atguigu.scw.webui.vo.OrderCreateInfoVo;
import com.atguigu.scw.webui.vo.ProjectDetailsVo;
import com.atguigu.scw.webui.vo.TMemberAddress;
import com.atguigu.scw.webui.vo.TOrder;
import com.atguigu.scw.webui.vo.TReturn;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {
	
	@Autowired
	UserServiceFeignClient userServiceFeignClient;
	
	@Autowired
	OrderServiceFeignClient orderServiceFeignClient;
	//4、支付宝回调的异步处理方法，修改订单的状态为已支付
	@ResponseBody
	@GetMapping(value="/ontifyurl")
	public String ontifyurl(HttpServletRequest request) {
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		try {
			Map<String,String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

			//——请在这里编写您的程序（以下代码仅作参考）——
			
			/* 实际验证过程建议商户务必添加以下校验：
			1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
			2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
			3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
			4、验证app_id是否为该商户本身。
			*/
			
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
			//当前方法调用是，假设支付成功，支付宝异步调用此方法
			//更新订单的状态为已支付状态
			orderServiceFeignClient.updateOrderStatus(out_trade_no, "1");
			if(signVerified) {//验证成功
				//获取支付宝POST过来的反馈信息
				
				
				//支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
				
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
						
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				}else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
				}
				
				//out.println("success");
				
			}else {//验证失败
			//	out.println("fail");
			
				//调试用，写文本函数记录程序运行情况是否正常
				//String sWord = AlipaySignature.getSignCheckContentV1(params);
				//AlipayConfig.logResult(sWord);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	//3、支付宝回调的同步处理方法
	@GetMapping(value="/returnurl")
	public String returnurl(HttpServletRequest request) {
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		String resultstr = null; 
		try {
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
			
			//——请在这里编写您的程序（以下代码仅作参考）——
			if(signVerified) {
				//商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//付款金额
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
				
				resultstr = "trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount;
			}else {
				resultstr = "验签失败";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "user/minecrowdfunding";
	}
	
	
	//2、创建订单的方法
	@ResponseBody //表示不要然试图解析器解析响应内容
	@PostMapping(value="/createOrder",produces="text/html")//produces: 表示给响应头设置content-type,告诉浏览器如何解析响应体内容
	public String createOrder(HttpSession session,OrderCreateInfoVo vo) {
		//获取session中订单相关的数据
		TOrder order = new TOrder();
		BeanUtils.copyProperties(vo, order);
		//订单创建时间
		order.setCreatedate("1999-2-3"); 
		//订单状态status：0-未支付；1-已支付 2-订单关闭
		order.setStatus("0");
		//生成订单编号：用户id+时间戳
		String ordernum = UUID.randomUUID().toString().replace("-", "").substring(0, 6)+System.currentTimeMillis();
		order.setOrdernum(ordernum);
		//获取session域中的项目回报对象
		ProjectDetailsVo project = (ProjectDetailsVo) session.getAttribute(ScwAppconsts.PROJECT_DETAILS_KEY);
		TReturn rtn = (TReturn) session.getAttribute("rtn");
		order.setProjectid(project.getId());
		order.setReturnid(rtn.getId());
		//计算总金额：double
		int money = rtn.getSupportmoney()*vo.getRtncount()+(rtn.getFreight()==null?0:rtn.getFreight());
		order.setMoney(money);
		//远程调用user项目根据accessToen查询用户id
		Map map = (Map) session.getAttribute("user");
		String accessToken = (String) map.get("accessToken");
		AppResponse<Integer> response = userServiceFeignClient.getMemberid(accessToken);
		Integer memberid = response.getData();
		order.setMemberid(memberid);
		//通过orderFeignclient远程调用实现订单的创建保存
		AppResponse<Object> orderResponse = orderServiceFeignClient.createOrder(order);
		if ("00000".equals(orderResponse.getCode())) {
			//订单保存成功，调用支付宝的sdk的api,生成一个自动提交的带参数的表单交给浏览器，然后浏览器会自动提交请求给支付宝服务端生成支付页面
			log.warn("订单保存成功：{}",orderResponse);
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = order.getOrdernum();
			//付款金额，必填
			String total_amount = order.getMoney()+"";
			//订单名称，必填
			String subject = project.getName();
			//商品描述，可空
			String body = order.getRemark();
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
			//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
			//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
			//		+ "\"total_amount\":\""+ total_amount +"\"," 
			//		+ "\"subject\":\""+ subject +"\"," 
			//		+ "\"body\":\""+ body +"\"," 
			//		+ "\"timeout_express\":\"10m\"," 
			//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
			
			//请求
			String result = null;
			try {
				result = alipayClient.pageExecute(alipayRequest).getBody();
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//将数据加签生成了一个表单
			
			//将支付宝客户端得到的响应体内容作为响应体 输出给浏览器
			System.out.println(result);//thml代码片段
			return "result";
			//out.println(result);
		}else {
			//订单保存失败，订单重复提交
			log.warn("订单保存失败：{}",orderResponse);
			return "";
			
		}
	}
	
	
	//1、再paytep1页面中点击去结算的请求处理方法
	@GetMapping("/pay-step-2.html")
	public String toPayStrp2(@RequestParam("referer")String ref,Model model, Integer rtnCount,HttpSession session) {
		//1、验证当前用户是否登录
		Map map = (Map) session.getAttribute("user");
		if (CollectionUtils.isEmpty(map)) {
			//用户未登录或者登录超时，跳转到登录页面给用户提示
			model.addAttribute("errorMsg", "结算操作必须先登录");
			session.setAttribute("ref", ref);//未登录跳转结账页面地址
			return "user/login";
		}
		//2、根据用户的accessToken查询用户的收货地址
		String accessToken = (String) map.get("accessToken");
		//远程调用scw-user 查询用户的收获地址列表
		AppResponse<List<TMemberAddress>> response = userServiceFeignClient.getAllAddress(accessToken);
		List<TMemberAddress> addresses = response.getData();
		//3、将数据存到request域中共享
		model.addAttribute("rtnCount", rtnCount);
		model.addAttribute("addresses", addresses);
		//涉及到金额应该再java、代码中通过BigDecimel处理
		
		
		log.warn("登陆状态对象获取的token:{},接受的回报数量：{}，查询到的地址对象列表：{}", accessToken,rtnCount,addresses);
		//4、转发到paystep2页面显示
		return "order/pay-step-2";
	}
}




















