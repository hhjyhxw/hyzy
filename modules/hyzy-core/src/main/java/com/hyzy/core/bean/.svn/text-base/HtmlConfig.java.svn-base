/**    
 * @package com.hyzybean 
 * @description : 业务bean包
 * @Copyright: hyzy Corporation 2014    
*/
package com.hyzy.core.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hyzy.core.utils.CommonUtil;

/**
 * @filename      : HtmlConfig.java
 * @description   : 业务bean - 静态模板配置
 * @author        : chengkunxf
 * @create        : 2013-4-26 上午10:40:47
 * @copyright     : hyzy Corporation 2014
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 * 2013-4-26 上午10:40:47
 */

public class HtmlConfig {
	
	public static final String REPLACE_UUID = "{uuid}";// 随机UUID字符串替换
	public static final String REPLACE_DATE_YY = "{date_yyyy}";// 当前日期字符串替换(年)
	public static final String REPLACE_DATE_MM = "{date_MM}";// 当前日期字符串替换(月)
	public static final String REPLACE_DATE_DD = "{date_dd}";// 当前日期字符串替换(日)
	public static final String REPLACE_DATE_HH = "{date_HH}";// 当前日期字符串替换(时)
	
	public static final String WEB_PUBLIC_JS = "webPublicJs";// webPublicJs
	public static final String ADMIN_BASE_JS = "adminBaseJs";// adminBaseJs
	public static final String ADMIN_JS = "adminJs";//后台JS
	public static final String SHOP_JS = "shopJs";//前台JS
	
	public static final String INDEX = "index";// 首页
	public static final String LOGIN = "login";// 登录
	public static final String REGISTER = "register";//注册
	public static final String REGISTER_AGREEMENT = "registerAgreement";//注册协议
	public static final String ACTIVATE = "activate";//激活
	public static final String CONFIRM="confirm";//邮箱激活确认
	public static final String PUBLIC_MEMBER_INFO = "publicMemberInfo";//第三方资料
	public static final String ARTICLE_CONTENT = "articleContent";// 文章内容
	public static final String HELP_CENTER = "helpCenter";//帮助中心
	public static final String HELP_CONTENT = "helpContent";//帮助中心内容
	public static final String GOODS_CONTENT = "goodsContent";// 商品内容
	public static final String ERROR_PAGE = "errorPage";// 错误页
	public static final String ERROR_PAGE_ACCESS_DENIED = "errorPageAccessDenied";// 权限错误页
	public static final String ERROR_PAGE_500 = "errorPage500";// 错误页500
	public static final String ERROR_PAGE_404 = "errorPage404";// 错误页404
	public static final String ERROR_PAGE_403 = "errorPage403";// 错误页403
	
	public static final String GOODSCATEGORYTREE = "goodsCategoryTree";//商品树形结构
	public static final String HEADER = "header";//头部
	public static final String NAVIGATION = "navigation";//头部导航
	public static final String FOOTER = "footer";//底部
	public static final String FRIENDLINK = "friendLink";//友情链接
	public static final String MEMBER_REGISTER_CENTER = "mRegCenter";//会员注册
	
	private String name;// 配置名称
	private String description;// 描述
	private String templateFilePath;// Freemarker模板文件路径
	private String htmlFilePath;// 生成HTML静态文件存放路径

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTemplateFilePath() {
		return templateFilePath;
	}

	public void setTemplateFilePath(String templateFilePath) {
		this.templateFilePath = templateFilePath;
	}

	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}
	
	// 获取生成HTML静态文件存放路径
	public String getHtmlFilePath() {
		htmlFilePath = htmlFilePath.replace(REPLACE_UUID, CommonUtil.getUUID());
		SimpleDateFormat yyDateFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat mmDateFormat = new SimpleDateFormat("MM");
		SimpleDateFormat ddDateFormat = new SimpleDateFormat("dd");
		SimpleDateFormat hhDateFormat = new SimpleDateFormat("HH");
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_YY, yyDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_MM, mmDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_DD, ddDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_HH, hhDateFormat.format(new Date()));
		return htmlFilePath;
	}
	
	
	/**
	 * 获取生成HTML静态文件存放路径 
	 * @author:chengkunxf
	 * @param htmlId 文件名字符串
	 * @return
	 */
	public String getHtmlFilePath(String htmlId) {
		htmlFilePath = htmlFilePath.replace(REPLACE_UUID, htmlId);
		SimpleDateFormat yyDateFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat mmDateFormat = new SimpleDateFormat("MM");
		SimpleDateFormat ddDateFormat = new SimpleDateFormat("dd");
		SimpleDateFormat hhDateFormat = new SimpleDateFormat("HH");
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_YY, yyDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_MM, mmDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_DD, ddDateFormat.format(new Date()));
		htmlFilePath = htmlFilePath.replace(REPLACE_DATE_HH, hhDateFormat.format(new Date()));
		return htmlFilePath;
	}

}