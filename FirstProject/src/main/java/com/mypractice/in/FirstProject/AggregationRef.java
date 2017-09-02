package com.mypractice.in.FirstProject;

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

    Object newBody = newExchange.getIn().getBody();

    if (oldExchange == null) {
      MessageContentsList list = new MessageContentsList();
      list.add(newBody);
      newExchange.getIn().setBody(list);
      return newExchange;
    } else {
      MessageContentsList list = oldExchange.getIn().getBody(MessageContentsList.class);
      list.add(newBody);
      return oldExchange;
    }
  }

}
