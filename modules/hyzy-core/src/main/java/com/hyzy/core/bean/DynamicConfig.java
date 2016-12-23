/**    
 * @package com.hyzybean 
 * @description : 业务bean包
 * @Copyright: hyzy Corporation 2014    
*/

package com.hyzy.core.bean;


/**
 * @filename      : DynamicConfig.java
 * @description   : 业务bean - 动态模板配置
 * @author        : chengkunxf
 * @create        : 2013-4-10 下午2:41:34
 * @copyright     : hyzy Corporation 2014
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 * 2013-4-10 下午2:41:34
 */
public class DynamicConfig {
	
	private String name;// 配置名称
	private String description;// 描述
	private String templateFilePath;// Freemarker模板文件路径

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

}