package com.shadow.deals.auth.mapper;

import com.shadow.deals.auth.dto.request.SignInRequestDTO;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * This interface describes the signatures of methods that allow to convert objects of one class into objects of
 * other classes. It is used when interacting with objects that are associated with authentication.
 *
 * @author Kirill "Tamada" Simovin
 */
@Mapper
public interface AuthMapper {
    /**
     * Instance of this interface.
     */
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    /**
     * This signature describes a method that allows to convert an object of class {@link SignInRequestDTO} to an
     * object of class {@link UsernamePasswordCredentials}. The {@link SignInRequestDTO#getEmail()} field is mapped to
     * the {@link UsernamePasswordCredentials#getUsername()} field.
     *
     * @param signInRequestDTO authentication data.
     * @return An object of class {@link UsernamePasswordCredentials} whose field values correspond to the field values
     * of the passed object.
     */
    @Mapping(target = "username", source = "email")
    UsernamePasswordCredentials signInRequestDTOToUsernamePasswordCredentials(SignInRequestDTO signInRequestDTO);
}