package priv.just.microservice.provider.api.domain;

import lombok.Data;

import java.util.Map;

@Data
public class ProviderResp {

    private long timestamp;

    private Map<String, Object> body;

}
