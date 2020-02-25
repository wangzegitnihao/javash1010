package com.atguigu.scw.webui.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102100730872";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDTocPEexjB4Vs0D6v2A9Hif8o+5+NB+4u9U/GZ5wlMYLedy2Wzb9TuB4pUva1L+rudU/kDIKWNkKO12hODcgKyzPLzOYPtdrxxmu2iSvVB/+WFfZQgKG3zugZxqLGqsIaDXeIqWTIduLRHgLATMUS4wU3SXSc7FcOWdas+Us7SF/gbaUaaauXUOPJ8NoVdUtWlFs9RhU1u/p+w76WEd1/eH8s3ygx6DFdLF2sdUpGulF0JKRwg9LHslJHvwu92nP29azEPrGz6omXTT4/1+jGE3+imT1zrCD7jtrUr82xqi8TyBKSQwRybE+WG1jP0UJcyWwSvs1lXm7GxReRyxt8pAgMBAAECggEAfaUTskoW97yQxkbrpCK+cEfITjKkBN+QJjZ5QRMevR7KxL8mQTMK6srFQOxfv7U7v/tD4qx2Pab62xN+9FIy8e6l6uthvRD6bkQ6A1mXZLjFjsxwrery5Hm0K7SozpsBMWsCxHuyOl0Xg0AOyUY986aoSlWN3hFczq5LnbPSIH6P5Cr8Yq7wn+kR/EaHkNANRSqenj0mPVB9EJsl6gfgn79dXpciq6PjSMyVu5rFhe6NC2Eap6d4Q3UUozVPS9ahDvt1WJVGCN1Eh/6eG/Qy8ejErXBFFtc8U18W1Q1dMEEtsb8xEKjBuvvKXgGYOiPGqPOQIs+Yr6qaF4P3eLT7UQKBgQDzKxRJR/rRyg2xp5WunyG05bbUz9c3eqGijjvz05Mvhj6I+koUhxdz0+CFrhmbQJXYY5RQqCXU1pvzTRp1r6qqbfolAOtCPEjHGJ0XBkZJ3liCdE67qbrJn5jfx7qVnSai542dJvJx9PgGNHaa4yF/Pl9Qd6R48p7+bKM8Gvw4XQKBgQDezKpjIMsulQzS7oIqnZYuZFRDDyPLrr9w91rmpClXV5jZ4JF+l60YEgsDrCG8fjE9REo0DB8I6Xw297RZKWv5QNhPOh2cXq0eBt+DwXrlAu0TANqyJNFmv3BpNF/8OOGVbN43PYdbHI4sxsVT8fOGRccMS7FmIiGh4LnZqiglPQKBgGJlYS0SAk1XvFjR3kCtxb7kYSoV/BlH9xXHBj1wtJM7eByHYuiRjYziBG0zeMsYxEmuabqFf+FzAeHepkFO1vllzR2iFJBVwoFF1XMWp+SGIzyOgblwQdQKT5rlv/uAfrzWg7g+pUY1gRNJsWCVXd2WSI1QDG6ghnOfVCIjLUJFAoGAJaaKiLPrAhU4FJEvuAnnbjGQN8DgOLRwL6PtXZqSTetErlUP4Ue6fZ3W9KjrDpJ4ubTHoWIHAJeMYTGc7ZBdacK2izTz+g/Zy4A312kj3n2PGK4FRyOHq4hgc7bhHZtzK7A5ITapoBEESbbRc+0oTSGPkJleAjd0XYm/fnQN0rUCgYB64vPxFHf2LjEWJE9iQB9c1iPo+ecTB+PD/ZRZ38Y1HZn7feK8J6kmoGfQBeLgPsPspALb5g+Lxx46Ikvw3qgC1NjYBKQYad99Di53nhG4NPCoJ+bYLvOzfnxp6DhUqQ1IDFl35cf25J02gzEpXFE7/yeb/HzwgPsqCVKYHiGfhw==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5Nnh09QPTVcYEbKhq5d8h/s3QK3KTHBsx+0LYHu7YuMwRjDlRJoK7vmFw/SZY6GnBq9JcOuovcLZmXxkpbfkXl5hiYwVsz3UKLeafXcDzULpK1QeBGEc5+yr4OE1CGeAMAHFcR1j6LP3umKQeSBjA38hk0UhO4cyc6ZXteEUvatIY7/p+ZcFgYHJ75MX5aIo/MU16etFyHV55upvvZ0uFoGLJKGpoIDCRLXZ3wxEFUwJsJqCj9D5JUboi7UmUoftus97MbAL4mc7J9qmcgKULpZP3IYcbGz3u+vZ6ywt5t9sKImixwvzn1I5IoDFZnSLrbwM0DpXFxXJDjboh2V3EwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http:/2x93c76838.wicp.vip/order/ontifyurl";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http:/2x93c76838.wicp.vip/order/returnurl";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\zb";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

