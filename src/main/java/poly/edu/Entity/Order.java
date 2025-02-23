package poly.edu.Entity;

import java.sql.Timestamp;

import java.time.Instant;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer orderId;


    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false, referencedColumnName = "UserID") // FK to Users
    private User user;

    @Column(name = "OrderDate", nullable = false, columnDefinition = "datetime default GETDATE()")
    private Timestamp orderDate=Timestamp.from(Instant.now());;

    @Column(name = "status", length = 50, nullable = false)
    private String status = "Pending"; // Mặc định Pending

    
    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "shipping_address", length = 255)
    private String shippingAddress;
    
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Order(User user, Timestamp orderDate, double totalAmount, String status, String shippingAddress) {
	    this.user = user;
	    this.orderDate = orderDate;
	    this.totalAmount = totalAmount;
	    this.status = status;
	    this.shippingAddress = shippingAddress;
	}
    
}