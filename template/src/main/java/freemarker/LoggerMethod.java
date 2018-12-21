package freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * @Author: mxding
 * @Date: 2018-12-21 15:25
 */
public class LoggerMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        System.out.println(arguments.get(0));
        return "";
    }
}
