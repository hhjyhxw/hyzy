/**    
 * @package com.haojie.admin.directive 
 * @description : TODO(用一句话描述该包做什么) 
 * @Copyright: Bmcok Corporation 2012    
*/
package com.hyzy.core.utils.freemarker.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hyzy.core.utils.freemarker.TemplateUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**   
 * <@checkbox id="checkId" name="checkname"></@checkbox>
 * 
 * 
 * @filename ：CheckboxDirective.java   
 * @description : TODO(用一句话描述该类做什么) 
 * @version ：   V 1.0
 * @author : chengkunxfei
 * @create : 2012-4-13 上午11:31:37  
 * @Copyright: Bmcok Corporation 2012    
 * 
 * Modification History:
 * 	Date			Author			Version			Description
 *--------------------------------------------------------------
 *2012-4-13 上午11:31:37
 */
public class GoodsCheckboxDirective implements TemplateDirectiveModel {

	
	public static final String CHECKBOX = "checkbox";
	private static final String NAME = "name";
	private static final String VALUE = "value";
	private static final String FIELDVALUE = "fieldValue";
	private static final String CSSCLASS = "cssClass";
	private static final String CSSSTYLE = "cssStyle";
	private static final String DISABLED = "disabled";
	private static final String READONLY = "readonly";
	private static final String TABINDEX = "tabindex";
	private static final String ID = "id";
	private static final String TITLE = "title";
	
	@Override
	public void execute(Environment env, Map map, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String s = TemplateUtil.parseToString("id", map);
		String s1 = TemplateUtil.parseToString("name", map);
		String s2 = TemplateUtil.parseToString("fieldValue", map);
		String s3 = TemplateUtil.parseToString("cssClass", map);
		String s4 = TemplateUtil.parseToString("cssStyle", map);
		String s5 = TemplateUtil.parseToString("title", map);
		String s6 = TemplateUtil.parseToString("tabindex", map);
		Boolean boolean1 = TemplateUtil.parseToBoolean("value", map);
		Boolean boolean2 = TemplateUtil.parseToBoolean("disabled", map);
		Boolean boolean3 = TemplateUtil.parseToBoolean("readonly", map);
		if (s == null)
			s = s1.replace(".", "_");
		if (s1 == null)
			s1 = "";
		if (s2 == null)
			s2 = "true";
		if (boolean1 == null)
			boolean1 = Boolean.valueOf(false);
		if (boolean2 == null)
			boolean2 = Boolean.valueOf(false);
		if (boolean3 == null)
			boolean3 = Boolean.valueOf(false);
		Writer writer = env.getOut();
		StringBuffer stringbuffer = new StringBuffer((new StringBuilder("<input type=\"checkbox\" name=\"")).append(s1).append("\" value=\"").append(s2).append("\"").toString());
		if (StringUtils.isNotEmpty(s))
			stringbuffer.append((new StringBuilder(" id=\"")).append(s).append("\"").toString());
		if (StringUtils.isNotEmpty(s3))
			stringbuffer.append((new StringBuilder(" class=\"")).append(s3).append("\"").toString());
		if (StringUtils.isNotEmpty(s4))
			stringbuffer.append((new StringBuilder(" style=\"")).append(s4).append("\"").toString());
		if (StringUtils.isNotEmpty(s5))
			stringbuffer.append((new StringBuilder(" title=\"")).append(s5).append("\"").toString());
		if (StringUtils.isNotEmpty(s6))
			stringbuffer.append((new StringBuilder(" tabindex=\"")).append(s5).append("\"").toString());
		if (boolean1.booleanValue())
			stringbuffer.append(" checked=\"checked\"");
		if (boolean2.booleanValue())
			stringbuffer.append(" disabled=\"disabled\"");
		if (boolean3.booleanValue())
			stringbuffer.append(" readonly=\"readonly\"");
		stringbuffer.append((new StringBuilder(" /><input type=\"hidden\" id=\"__checkbox_")).append(s).append("\" name=\"__checkbox_").append(s1).append("\" value=\"").append(s2).append("\"").toString());
		if (boolean2.booleanValue())
			stringbuffer.append(" disabled=\"disabled\"");
		stringbuffer.append(" />");
		writer.write(stringbuffer.toString());

	}

}
