package com.mypractice.in.FirstProject;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfConstants;
import org.apache.cxf.message.MessageContentsList;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class DataMapper {
	private static final Logger LOG = LoggerFactory.getLogger(DataMapper.class);

	public void Validate(Exchange exchange, @Body Request req) throws Exception {
		LOG.info("Entered into Validate method");
		exchange.getIn().removeHeaders("org*");
		exchange.getIn().removeHeaders("Camel*");
		exchange.getIn().removeHeaders("ResponseContext");
		exchange.getIn().removeHeader(HttpHeaders.ACCEPT_ENCODING);

		String action = req.getAction();

		// exchange.getIn().setBody("Success",String.class);
		if (action == null || action.isEmpty()) {
			throw new Exception(
					"Action Element required and should not be empty");
		} else {
			switch (action) {
			case "GET":
				if (req.getPayload() == null) {

					exchange.getIn().setHeader(Exchange.HTTP_METHOD, "GET");
				} else {
					throw new Exception("GET should not have payload");
				}
				break;
			case "POST":
				if (req.getPayload() != null
						&& req.getPayload().getElements().hasNext()) {
					exchange.getIn().setHeader(Exchange.HTTP_METHOD, "POST");
					exchange.getIn().setBody(req.getPayload());

				} else {
					throw new Exception(
							"POST should have payload and should not be Empty");
				}
				break;
			default:
				throw new Exception("Method name is invalid");
			}
			if (req.getEndpoint() != null && !req.getEndpoint().isEmpty()) {
				exchange.setProperty("endpoint", req.getEndpoint());
			} else
				throw new Exception("endpoint should not be empty");
		}
		LOG.info("Validation completed");
	}

	public void mapRequest(Exchange exchange) throws Exception {
		LOG.info("Entered into mapRequest method");
		exchange.getIn().setHeader(HttpHeaders.ACCEPT,
				MediaType.APPLICATION_JSON);
		exchange.getIn().setHeader(Exchange.DESTINATION_OVERRIDE_URL,
				exchange.getProperty("endpoint"));
		exchange.getIn().setHeader(CxfConstants.CAMEL_CXF_RS_RESPONSE_CLASS,
				ResponseFromStub.class);
		LOG.info("request mapping completed");
	}

	public void mapResponse(Exchange exchange) throws Exception {
		LOG.info("Entered into mapResponse method");
		LOG.info("Response Code received: "
				+ exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));

		ResponseFromStub rfs = exchange.getIn().getBody(ResponseFromStub.class);
		if (rfs != null)
			exchange.getIn().setBody(rfs);
		else
			throw new Exception("Response from stub null mapResponse");
		LOG.info("response mapping completed");
	}

	public void throwError(Exchange exchange) throws Exception {
		LOG.info("Entered into throwError method");
		LOG.info("Response Code received: "
				+ exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
		WebApplicationException exception = exchange.getProperty(
				Exchange.EXCEPTION_CAUGHT, WebApplicationException.class);
		LOG.info("caught exception : "+exception.toString());
		ResponseFromStub rfs = exception.getResponse().readEntity(
				ResponseFromStub.class);
		if (rfs != null)
			exchange.getIn().setBody(rfs);
		else
			throw new Exception("Response from stub null");
		
		LOG.info("throwError mapping completed");
	}
	public void throwErrorService(Exchange exchange) throws Exception{
		throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Service Unavailabe").build());
	}

}
