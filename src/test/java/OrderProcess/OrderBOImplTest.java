package OrderProcess;

import OrderProcess.bo.OrderBOImpl;
import OrderProcess.bo.exception.BOException;
import OrderProcess.dao.OrderDAO;
import OrderProcess.dto.Order;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class OrderBOImplTest {

    private static final int ORDER_ID = 123;
    @Mock
    OrderDAO dao;

    @InjectMocks
    private OrderBOImpl bo;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
        Order order = new Order();
        when(dao.create(any(Order.class))).thenReturn(1);
        boolean result = bo.placeOrder(order);

        assertTrue(result);
        verify(dao, times(1)).create(order);
    }

    @Test
    void placeOrder_Should_not_Create_An_Order() throws SQLException, BOException {
        Order order = new Order();
        when(dao.create(order)).thenReturn(0);
        boolean result = bo.placeOrder(order);

        assertFalse(result);
        verify(dao).create(order);
    }

    @Test
    void placeOrder_Should_Throw_BOException() throws SQLException {
        Order order = new Order();
        when(dao.create(any(Order.class))).thenThrow(SQLException.class);
        Assertions.assertThrows(BOException.class, () -> bo.placeOrder(order));
    }

    @Test
    void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(anyInt())).thenReturn(order);
        when(dao.update(order)).thenReturn(1);
        boolean result = bo.cancelOrder(123);
        assertTrue(result);

        verify(dao).read(anyInt());
        verify(dao).update(order);
    }

    @Test
    void cancelOrder_Should_NOT_Cancel_The_Order() throws SQLException, BOException {
        Order order = new Order();
        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenReturn(0);
        boolean result = bo.cancelOrder(123);

        assertFalse(result);

        verify(dao).read(ORDER_ID);
        verify(dao).update(order);
    }

    @Test
    void cancelOrder_ShouldThrowABOExceptionOnRead() throws SQLException {
        when(dao.read(anyInt())).thenThrow(SQLException.class);
        Assertions.assertThrows(BOException.class, () -> bo.cancelOrder(ORDER_ID));
    }

    @Test
    void cancelOrder_Should_Throw_A_BOExceptionOnUpdate() throws SQLException {
        Order order = new Order();
        when(dao.read(ORDER_ID)).thenReturn(order);
        when(dao.update(order)).thenThrow(SQLException.class);
        Assertions.assertThrows(BOException.class, () -> bo.cancelOrder(ORDER_ID));
    }

    @Test
    void deleteOrder_Deletes_The_Order() throws SQLException, BOException {
        when(dao.delete(ORDER_ID)).thenReturn(1);
        boolean result = bo.deleteOrder(ORDER_ID);
        assertTrue(result);
        verify(dao).delete(ORDER_ID);
    }
}