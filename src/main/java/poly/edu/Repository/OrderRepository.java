package poly.edu.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.edu.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    // ✅ Lấy danh sách đơn hàng theo UserID
    List<Order> findByUserUserId(Integer userId);

    // ✅ Lấy danh sách đơn hàng theo UserID và Trạng thái (không phân biệt chữ hoa/thường)
    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId AND LOWER(o.status) = LOWER(:status)")
    List<Order> findByUserUserIdAndStatus(@Param("userId") Integer userId, @Param("status") String status);

    // ✅ Tìm đơn hàng theo OrderID và UserID
    Optional<Order> findByOrderIdAndUserUserId(Integer orderId, Integer userId);

    // ✅ Kiểm tra xem User có đơn hàng 'Pending' nhưng không thanh toán khi nhận hàng không
    @Query("SELECT COUNT(o) > 0 FROM Order o WHERE o.user.userId = :userId AND LOWER(o.status) = 'pending' AND o.paymentMethod <> 'WHEN_RECEIVE'")
    boolean existsPendingOrderWithOtherPayment(@Param("userId") Integer userId);
}
