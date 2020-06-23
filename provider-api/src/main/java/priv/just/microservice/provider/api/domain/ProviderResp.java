package priv.just.microservice.provider.api.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ProviderResp implements Serializable {

    private long timestamp;

    private Map<String, Object> body;

}
