package com.shadow.deals.user.main.repository;

import com.shadow.deals.band.main.entity.Band;
import com.shadow.deals.band.task.main.entity.Task;
import com.shadow.deals.base.repository.CommonRepository;
import com.shadow.deals.user.main.entity.User;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This repository contains methods for interacting with table associated with entity {@link User}.
 *
 * @author Kirill "Tamada" Simovin
 */
@Repository
public interface UserRepository extends CommonRepository<User> {

    /**
     * This signature describes a method that allows to find an entity by the email value.
     *
     * @param email user's email.
     * @return If an instance exists, it is {@link Optional}, containing an instance. Otherwise, it's an empty {@link Optional}.
     */
    Optional<User> findByEmail(String email);

    /**
     * This signature describes a method that allows to find an entity by the refresh token UUID-key value.
     *
     * @param refreshTokenKey refresh token UUID-key.
     * @return If an instance exists, it is {@link Optional}, containing an instance. Otherwise, it's an empty {@link Optional}.
     */
    @Query(value = """
        SELECT * FROM sd_user WHERE id = (SELECT user_id FROM sd_refresh_token WHERE refresh_token = :refreshTokenKey)
        """,
        nativeQuery = true)
    Optional<User> findByRefreshTokenKey(String refreshTokenKey);

    /**
     * This signature describes a method that allows to determine whether an entity exists with the given email.
     *
     * @param email user's email.
     * @return If user with given email exists - returns {@code true}, otherwise - {@code false}.
     */
    boolean existsByEmail(String email);

    List<User> findAllByBand(Band band);

    @Query("SELECT u.id FROM User u WHERE u.ownBand.id = :bandId")
    UUID findDonByBandId(UUID bandId);

    List<User> findAllByTask(Task task);
}