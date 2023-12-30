DELIMITER //

CREATE PROCEDURE Proc_PlaceOrder(
    IN p_CustomerID binary(16)
)
BEGIN
    DECLARE orderID binary(16);

    -- Tạo đơn đặt hàng mới
    INSERT INTO order_product (status, total_amount, order_date, customer_id)
    SELECT 'PENDING',                 -- Trạng thái đơn hàng mới
           SUM(c.quantity * p.price), -- Tổng giá trị đơn hàng
           NOW(),                     -- Ngày hiện tại
           p_CustomerID
    FROM cart c
             JOIN product p ON c.product_id = p.id
    WHERE c.customer_id = p_CustomerID;

    -- Lấy mã đơn đặt hàng vừa tạo
#     SET @OrderID = LAST_INSERT_ID();
    SELECT op.id
    INTO orderID
    FROM order_product op
    WHERE op.customer_id = p_CustomerID;

    -- Chuyển dữ liệu từ giỏ hàng vào chi tiết đơn đặt hàng
    INSERT INTO order_product_detail (quantity, product_id, order_product_id)
    SELECT c.quantity,
           c.product_id,
           orderID
    FROM cart c
             JOIN product p ON c.product_id = p.id
    WHERE c.customer_id = p_CustomerID;

    -- Xóa dữ liệu trong giỏ hàng sau khi đã đặt hàng
    DELETE FROM cart c WHERE c.customer_id = p_CustomerID;
END //

DELIMITER ;