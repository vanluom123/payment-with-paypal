DELIMITER //

CREATE PROCEDURE AddProductToCart (
    IN p_CustomerID binary(16),
    IN p_ProductID binary(16),
    IN p_Quantity INT
)
BEGIN
    -- Kiểm tra xem sản phẩm và người dùng có tồn tại không
    DECLARE productExists INT;
    DECLARE customerExists INT;
	DECLARE cartItemCount INT;
    
    SELECT COUNT(*) INTO productExists FROM product WHERE id = p_ProductID;
    SELECT COUNT(*) INTO customerExists FROM customer WHERE id = p_CustomerID;

    -- Nếu sản phẩm hoặc người dùng không tồn tại, không thực hiện thêm vào giỏ hàng
    IF productExists = 0 OR customerExists = 0 THEN
        SIGNAL SQLSTATE VALUE '45000'
		SET MESSAGE_TEXT = 'Product or user does not exist';
    ELSE
        -- Kiểm tra xem sản phẩm đã có trong giỏ hàng hay chưa
        -- DECLARE cartItemCount INT;
        SELECT COUNT(*) INTO cartItemCount
        FROM cart
        WHERE customer_id = p_CustomerID AND product_id = p_ProductID;

        -- Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
        IF cartItemCount = 0 THEN
            INSERT INTO cart (quantity, customer_id, product_id)
            VALUES (p_Quantity, p_CustomerID, p_ProductID);
        ELSE
            -- Nếu sản phẩm đã có trong giỏ hàng, cập nhật số lượng và trạng thái chọn
            UPDATE cart
            SET quantity = quantity + p_Quantity
            WHERE customer_id = p_CustomerID AND product_id = p_ProductID;
        END IF;
    END IF;
END //

DELIMITER ;
