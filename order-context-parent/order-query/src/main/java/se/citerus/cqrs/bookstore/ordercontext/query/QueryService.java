package se.citerus.cqrs.bookstore.ordercontext.query;

import org.joda.time.LocalDate;
import se.citerus.cqrs.bookstore.ordercontext.client.bookcatalog.BookCatalogClient;
import se.citerus.cqrs.bookstore.ordercontext.client.bookcatalog.BookDto;
import se.citerus.cqrs.bookstore.ordercontext.order.BookId;
import se.citerus.cqrs.bookstore.ordercontext.order.OrderId;
import se.citerus.cqrs.bookstore.ordercontext.publishercontract.PublisherContractId;
import se.citerus.cqrs.bookstore.ordercontext.query.orderlist.OrderListDenormalizer;
import se.citerus.cqrs.bookstore.ordercontext.query.orderlist.OrderProjection;
import se.citerus.cqrs.bookstore.ordercontext.query.sales.OrdersPerDayAggregator;

import java.util.List;
import java.util.Map;

public class QueryService {

  private final OrderListDenormalizer orderListDenormalizer;
  private final OrdersPerDayAggregator ordersPerDayAggregator;
  private final BookCatalogClient bookCatalogClient;

  public QueryService(OrderListDenormalizer orderListDenormalizer,
                      OrdersPerDayAggregator ordersPerDayAggregator,
                      BookCatalogClient bookCatalogClient) {
    this.orderListDenormalizer = orderListDenormalizer;
    this.ordersPerDayAggregator = ordersPerDayAggregator;
    this.bookCatalogClient = bookCatalogClient;
  }

  public OrderProjection getOrder(OrderId orderId) {
    return orderListDenormalizer.get(orderId);
  }

  public List<OrderProjection> listOrders() {
    return orderListDenormalizer.listOrders();
  }

  public PublisherContractId findPublisher(BookId bookId) {
    BookDto book = bookCatalogClient.getBook(bookId.id);
    return new PublisherContractId(book.publisherContractId);
  }

  public Map<LocalDate, Integer> getOrdersPerDay() {
    return ordersPerDayAggregator.getOrdersPerDay();
  }

}
