package com.jdy.collections;  
      
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.StringWriter;  

import org.codehaus.jackson.JsonFactory;  
import org.codehaus.jackson.JsonGenerationException;  
import org.codehaus.jackson.JsonGenerator;  
import org.codehaus.jackson.JsonNode;  
import org.codehaus.jackson.JsonParseException;  
import org.codehaus.jackson.map.JsonMappingException;  
import org.codehaus.jackson.map.ObjectMapper;  
import org.codehaus.jackson.map.SerializationConfig;  
import org.codehaus.jackson.map.annotate.JsonSerialize;  

import net.sf.json.JSONObject;

      
/** 
* 工具类，实现JSON与Java Bean的互相转换 
* User: 
* 
*/
public class JsonUtils {  
      
        private static ObjectMapper objectMapper = new ObjectMapper();  
        private static JsonFactory jsonFactory = new JsonFactory();  
      
        static {  
            objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);  
            objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);  
        }
        
        public static boolean isJsonString(String jsonString){
	        try{
	            JSONObject jsonObject = JSONObject.fromObject( jsonString ); 
	           if(jsonObject.containsKey("action")){
	        	   
	           }
	            return true;
	        }catch(Exception e){
	            return false;
	        }
    	}
        /** 
         * 泛型返回，字符串转对象 
         * @param jsonAsString 
         * @param pojoClass 
         * @return 
         * @throws JsonMappingException 
         * @throws JsonParseException 
         * @throws IOException 
         */  
        public static <T> T fromJson(String jsonAsString, Class<T> pojoClass) throws JsonMappingException,  
                JsonParseException, IOException {  
            return objectMapper.readValue(jsonAsString, pojoClass);  
        }  
      
        public static <T> T fromJson(FileReader fr, Class<T> pojoClass) throws JsonParseException, IOException {  
            return objectMapper.readValue(fr, pojoClass);  
        }  
      
        /** 
         * Object对象转
         * @param pojo 
         * @return 
         * @throws JsonMappingException 
         * @throws JsonGenerationException 
         * @throws IOException 
         */  
        public static String toJson(Object pojo) throws JsonMappingException, JsonGenerationException, IOException {  
            return toJson(pojo, false);  
        }  
      
        public static String toJson(Object pojo, boolean prettyPrint) throws JsonMappingException, JsonGenerationException,  
                IOException {
            StringWriter sw = new StringWriter();  
            JsonGenerator jg = jsonFactory.createJsonGenerator(sw);  
            if (prettyPrint) {
                jg.useDefaultPrettyPrinter();  
            }  
            objectMapper.writeValue(jg, pojo);  
            return sw.toString();  
        }  
      
        public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint) throws JsonMappingException,  
                JsonGenerationException, IOException {  
            JsonGenerator jg = jsonFactory.createJsonGenerator(fw);  
            if (prettyPrint) {  
                jg.useDefaultPrettyPrinter();  
            }  
            objectMapper.writeValue(jg, pojo);  
        }
        
      
        public static JsonNode parse(String jsonStr) throws IOException {  
            JsonNode node = null;  
            node = objectMapper.readTree(jsonStr);  
            return node;  
        }  
      
        public static ObjectMapper getObjectMapper() {  
            return objectMapper;  
        }  
}  