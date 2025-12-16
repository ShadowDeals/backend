package com.shadow.deals.base;

import com.shadow.deals.auth.dto.request.SignUpRequestDTO;
import com.shadow.deals.auth.service.AuthService;
import com.shadow.deals.region.enums.RegionName;
import com.shadow.deals.user.role.enums.UserRoleName;
import com.shadow.deals.util.CommonUtils;
import jakarta.inject.Inject;

/**
 * @author Kirill "Tamada" Simovin
 */
public abstract class BaseAuthTestContainerTest extends BaseTestContainerTest {

    @Inject
    private AuthService authService;

    protected String createUser() {
        return createUser("volce.chat@mail.ru", UserRoleName.DON, RegionName.MOSCOW_REGION);
    }

    protected String createUser(String email, UserRoleName role) {
        return createUser(email, role, RegionName.MOSCOW_REGION);
    }

    protected String createUser(String email, UserRoleName role, RegionName regionName) {
        authService.signup(
            new SignUpRequestDTO(
                "TEST",
                "TEST",
                "TEST",
                "TEST",
                email,
                role,
                regionName
            )
        );

        return authService.confirmEmail(CommonUtils.generateUUIDFromString(email)).getAccessToken();
    }
}
