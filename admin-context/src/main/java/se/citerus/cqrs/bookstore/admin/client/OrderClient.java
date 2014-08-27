package se.citerus.cqrs.bookstore.admin.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import org.joda.time.LocalDate;
import se.citerus.cqrs.bookstore.admin.web.transport.OrderDto;
import se.citerus.cqrs.bookstore.admin.web.transport.OrderActivationRequest;
import se.citerus.cqrs.bookstore.admin.web.transport.RegisterPublisherRequest;

import java.util.List;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

public class OrderClient {

  public static final GenericType<List<OrderDto>> ORDER_LIST_TYPE = new GenericType<List<OrderDto>>() {
  };
  private final Client client;

  private OrderClient(Client client) {
    this.client = client;
  }

  public static OrderClient create(Client client) {
    return new OrderClient(client);
  }

  public void activate(OrderActivationRequest activationRequest) {
    client.resource("http://localhost:8080/order-requests/activations")
        .entity(activationRequest, APPLICATION_JSON_TYPE).post();
  }

  public void registerPublisher(RegisterPublisherRequest registerPublisherRequest) {
    client.resource("http://localhost:8080/order-requests/activations")
        .entity(registerPublisherRequest, APPLICATION_JSON_TYPE).post();
  }

  public Map<LocalDate, Integer> getOrdersPerDay() {
    return null;
  }

  public List<OrderDto> listOrders() {
    return client.resource("http://localhost:8080/orders").accept(APPLICATION_JSON_TYPE).get(ORDER_LIST_TYPE);
  }

  public List<Map<String, Object>> getAllEvents() {
    return null;
  }
}