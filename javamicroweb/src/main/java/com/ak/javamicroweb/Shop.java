package com.ak.javamicroweb;
import static spark.Spark.get;
import static spark.SparkBase.port;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ak.javamicroweb.helper.AccessLogGen;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.Spark;
  
public class Shop {
	
	private static Configuration configuration = new Configuration(new Version(2, 3, 0));
	private static String id;
  
    public static void main(String[] args) throws Exception{
    	
    	
    	FileTemplateLoader ftl1 = null;
    	ftl1 = new FileTemplateLoader(new File("/opt/customwebapp/templates"));
    	MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[] { ftl1 });
        configuration.setTemplateLoader(mtl);
        port(11223);
        get("/",(request,response) -> {
        	id = new Random().ints(97, 123)
                    .limit(5)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString()+System.currentTimeMillis();
        	StringWriter writer = new StringWriter();
        	configuration.getTemplate("form.ftl").process(null, writer);
        	
        	try{
        		PrintWriter pw = new PrintWriter(new File("/opt/customwebapp/logs/access-"+id+".log"));
        		for(String log : AccessLogGen.getLogs("yaek", 30)) {
        			pw.println(log);
        		}
        		pw.flush();
        		pw.close();
        	}
        	catch (Exception e) {
				e.printStackTrace();
			}
        	
        	
        	return writer;
        });
        

        Spark.post("/thankyou", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                String name = request.queryParams("name") != null ? request.queryParams("name") : "";
                if(name==null||name.trim().length()==0) {
                	name="Mr. John More Doe";
                }
                Template resultTemplate = configuration.getTemplate("result.ftl");
                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                
                map.put("orderid", id);
                resultTemplate.process(map, writer);
            } catch (Exception e) {
                Spark.halt(500);
            }
            return writer;
        });
    }
    
}