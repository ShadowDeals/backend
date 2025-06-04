package com.shadow.deals.band.request.service;

import com.shadow.deals.band.request.dto.response.RequestResponseDTO;
import com.shadow.deals.band.request.entity.Request;
import com.shadow.deals.base.service.CommonEntityService;
import com.shadow.deals.user.main.entity.User;
import com.shadow.deals.region.enums.RegionName;
import io.micronaut.http.HttpRequest;

import java.util.TreeSet;
import java.util.UUID;

/**
 * @author Kirill "Tamada" Simovin
 */
public interface RequestService extends CommonEntityService<Request> {
    void deleteRequest(UUID id);

    void acceptRequest(UUID id);

    void createRequests(HttpRequest<?> request, RegionName region);

    void createRequests(User user, RegionName region);

    TreeSet<RequestResponseDTO> getRequests(HttpRequest<?> request);
}
