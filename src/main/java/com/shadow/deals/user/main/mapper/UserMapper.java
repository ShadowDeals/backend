package com.shadow.deals.user.main.mapper;

import com.shadow.deals.auth.dto.request.SignUpRequestDTO;
import com.shadow.deals.user.main.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * This interface describes the signatures of methods that allow to convert objects of one class into objects of
 * other classes. It is used when interacting with objects that are associated with {@link User} entities.
 *
 * @author Kirill "Tamada" Simovin
 */
@Mapper
public interface UserMapper {
    /**
     * Instance of this interface.
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * The signature describes a method that converts an object of the {@link SignUpRequestDTO} class into an object of
     * the {@link User} class.
     *
     * @param signUpRequestDTO data for registering a new user.
     * @return An object of the {@link User} class, the corresponding fields of which are identical to the fields of the
     * passed object.
     */
    User toUserFromSignUpRequestDTO(SignUpRequestDTO signUpRequestDTO);
}