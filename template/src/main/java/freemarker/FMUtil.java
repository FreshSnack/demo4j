package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @Author: mxding
 * @Date: 2018-12-21 14:15
 */
public class FMUtil {

    /**
     * 获取模板
     * @param templateName
     * @return
     */
    public static Template getTemplate(String templateName) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassForTemplateLoading(FMUtil.class, "/fm/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setSharedVariable("LOG", new LoggerMethod());
        return cfg.getTemplate(templateName);
    }

}
