package com.shadow.deals.user.role.service;


import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.user.role.entity.UserRole;
import com.shadow.deals.user.role.enums.UserRoleName;

/**
 * This interface contains the signatures of the methods that must be implemented by the corresponding service class.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface UserRoleService extends CommonEntityService<UserRole> {
    /**
     * This method allows to find a role by its name.
     *
     * @param name role name.
     * @return {@link UserRole} entity by its name.
     */
    UserRole findUserRoleByName(UserRoleName name);
}