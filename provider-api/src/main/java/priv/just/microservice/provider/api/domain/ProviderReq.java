package priv.just.microservice.provider.api.domain;

import lombok.Data;

import java.util.Map;

@Data
public class ProviderReq {

    private long id;

    private String label;

    private Map<String, String> params;

}
