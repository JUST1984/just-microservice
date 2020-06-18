package priv.just.microservice.provider.api.service;

import priv.just.microservice.provider.api.domain.ProviderReq;
import priv.just.microservice.provider.api.domain.ProviderResp;

public interface ProviderService {

    ProviderResp invoke(ProviderReq providerReq);

}
