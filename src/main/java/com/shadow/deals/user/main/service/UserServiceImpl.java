package com.shadow.deals.user.main.service;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.base.service.CommonUpdateService;
import com.shadow.deals.exception.APIException;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.user.main.repository.UserRepository;
import com.shadow.deals.user.role.entity.UserRole;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpStatus;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * This class contains methods that perform business logic related to the {@link User} entity.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, CommonUpdateService<User> {
    /**
     * Instance of the repository interface that contains methods for interacting with table associated with
     * {@link User} entity.
     */
    private final UserRepository userRepository;

    /**
     * This method allows to find an {@link User} entity by its Id.
     *
     * @param id entity's {@link UUID} Id.
     * @return The {@link User} entity corresponding to the given Id.
     * @throws APIException in case the requested instance of the entity does not exist. It throws with the
     *                      {@link HttpStatus#NOT_FOUND} code.
     */
    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new APIException(
                "Пользователь с id = %s не найден".formatted(id),
                HttpStatus.NOT_FOUND)
        );
    }

    /**
     * This method allows to get all the elements from the table corresponding to the {@link User}
     * entity described by the Generic class.
     *
     * @return All the elements from the table corresponding to the {@link User} entity described
     * by the Generic class.
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * This method allows to save an entity to the appropriate table.
     *
     * @param entity entity to save.
     * @return Saved entity with set ID and other auto generated values.
     */
    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    /**
     * This method allows to find an entity by the email value.
     *
     * @param email user's email.
     * @return The entity corresponding to the given email.
     * @throws APIException in case the requested instance of the entity does not exist. It throws with the
     *                      {@link HttpStatus#NOT_FOUND} code.
     */
    @Transactional
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new APIException(
                "Пользователя с почтой %s не существует".formatted(email),
                HttpStatus.NOT_FOUND));
    }

    /**
     * This method allows to find an entity by the refresh token UUID-key value.
     *
     * @param refreshTokenKey refresh token UUID-key.
     * @return The entity corresponding to the given refresh token UUID-key.
     * @throws APIException in case the requested instance of the entity does not exist. It throws with the
     *                      {@link HttpStatus#NOT_FOUND} code.
     */
    @Override
    public User findByRefreshTokenKey(String refreshTokenKey) {
        return userRepository.findByRefreshTokenKey(refreshTokenKey).orElseThrow(() -> new APIException(
                "Токен обновления не соответствует ни одному пользователю.",
                HttpStatus.NOT_FOUND));
    }

    /**
     * This method allows to get set of user role names.
     *
     * @param user a user who needs to be assigned set of roles.
     * @return Set of user role names.
     */
    @Transactional
    @Override
    public String getUserRole(User user) {
        if (user == null) {
            return null;
        }

        UserRole role = user.getRole();
        if (role == null) {
            return null;
        }
        return user.getRole().getRoleName().getTitle();
    }

    /**
     * This method allows to determine whether an entity exists with the given email.
     *
     * @param email user's email.
     * @return If user with given email exists - returns {@code true}, otherwise - {@code false}.
     */
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Map<String, Object> getUserClaims(User user) {
        if (user == null) {
            return null;
        }

        Band userBand = user.getBand();
        Band ownBand = user.getOwnBand();
        if (userBand == null && ownBand == null) {
            return null;
        }

        return Map.of(
                "bandId", getUserBand(user),
                "name", getUserName(user)
        );
    }

    private UUID getUserBand(User user) {
        if (user == null) {
            return null;
        }

        Band userBand = user.getBand();
        Band ownBand = user.getOwnBand();
        if (userBand == null && ownBand == null) {
            return null;
        }

        return Objects.requireNonNullElse(userBand, ownBand).getId();
    }

    @NotNull
    public String getUserName(User user) {
        if (user == null) {
            return "";
        }

        String nickname = user.getNickname();
        if (StringUtils.isEmpty(nickname)) {
            return user.getLastName() + " " + user.getFirstName();
        }

        return nickname;
    }

    /**
     * This method allows updating the fields of the transferred entity in the database.
     *
     * @param entity the entity that needs to be updated.
     * @return Updated entity.
     */
    @Override
    public User update(User entity) {
        return userRepository.update(entity);
    }
}