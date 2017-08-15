package com.rgukt.in.FirstProject;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.cxf.message.MessageContentsList;
import org.codehaus.jackson.JsonNode;

public class AggregationRef implements AggregationStrategy {
	
	List<Object> persons = new ArrayList<Object>();
	
	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		// TODO Auto-generated method stub
		if (oldExchange == null) {
			return newExchange;
		}
		else
		return oldExchange;
		/*Object newBody =  newExchange.getIn().getBody();
		
		if (oldExchange == null) {
			persons.add(newBody);
			newExchange.setProperty("PersonList", persons);
			return newExchange;
		} else {
			Persons oldBody = oldExchange.getIn().getBody(Persons.class);
			persons= (List<Object>) oldExchange.getProperty("PersonList");
			persons.add(oldBody);
			oldExchange.setProperty("PersonList", persons);
			return oldExchange;
		}*/
		
		/*if (oldExchange == null) {
			MessageContentsList list = new MessageContentsList();
			list.add(newBody);
			newExchange.getIn().setBody(list);
			return newExchange;
		} else {
			MessageContentsList list = oldExchange.getIn().getBody(MessageContentsList.class);
			list.add(newBody);
			return oldExchange;
		}*/
	}

}
