package OrderProcess;

import UserAdminService.dao.UserDAO;
import UserAdminService.dto.User;
import UserAdminService.util.IDGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(IDGenerator.class)
class UserDAOTest {

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private UserDAO userDAO;

    @Mock
    private IDGenerator idGenerator;


    @Test
    void createShouldReturnAUserId() {
        mockStatic(IDGenerator.class);
        int result = userDAO.create(new User());

        System.out.println(result);
    }
}