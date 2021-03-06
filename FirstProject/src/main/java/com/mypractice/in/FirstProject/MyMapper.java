package com.mypractice.in.FirstProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.message.MessageContentsList;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class MyMapper {
  
	private static final Logger LOG = LoggerFactory.getLogger(MyMapper.class);
	   
    public List<String> setRequestPaths(Exchange exchange){
      

      exchange.getIn().removeHeaders("org*");
      exchange.getIn().removeHeaders("Camel*");
      exchange.getIn().removeHeaders("ResponseContext");
      exchange.getIn().removeHeader(HttpHeaders.ACCEPT_ENCODING);
      
      List<String> urlss = new ArrayList<String>();
      urlss.add("123");
      urlss.add("456");
      urlss.add("789");
      urlss.add("776");
      return urlss;
    }

	public void parallelCall(Exchange exchange) {
	  
		LOG.info("Entered into parallelCall method");

		/*String id = exchange.getIn().getHeader(Exchange.HTTP_PATH).toString()
				.substring(1);*/
		
		String path = exchange.getIn().getBody(String.class);

		exchange.getIn().setHeader(Exchange.HTTP_METHOD, "GET");
		exchange.getIn().setHeader(Exchange.HTTP_PATH, path);
		exchange.getIn().setHeader(HttpHeaders.ACCEPT,
				MediaType.APPLICATION_JSON);

		exchange.getIn().setHeader(CxfConstants.CAMEL_CXF_RS_RESPONSE_CLASS,
				Persons.class);

		/*String url = "http://localhost:8181/cxf/v1/stub/";
		List<String> urlss = new ArrayList<String>();
		urlss.add("123");
		urlss.add("456");
		urlss.add("789");
		urlss.add("776");
		List<String> destns = new ArrayList<String>();
		for (String name : urlss) {
			StringBuilder urls = new StringBuilder();
			urls.append(url).append(name);
			destns.add(urls.toString());
		}
		exchange.getIn().setHeader("routes", destns);*/
	}

	public String parallelResponse(Exchange exchange) throws JsonGenerationException, JsonMappingException, IOException {
		LOG.info("Entered into parallelResponse method");
		
		Object list = exchange.getIn().getBody();
		LOG.debug("Exchange Body received :: "+ list);
		
		ObjectMapper mapper = new ObjectMapper();
		String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		
		LOG.info("Finished parallelResponse method");
		return response;
		
	}
}
