package priv.just.microservice.provider.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import priv.just.microservice.provider.api.domain.ProviderReq;
import priv.just.microservice.provider.api.domain.ProviderResp;
import priv.just.microservice.provider.api.service.ProviderService;

import java.util.HashMap;

@Service
@Slf4j
public class DubboProviderService implements ProviderService {

    @Override
    public ProviderResp invoke(ProviderReq providerReq) {
        log.info("dubbo provider service request : {}", JSONObject.toJSONString(providerReq));
        ProviderResp providerResp = new ProviderResp();
        providerResp.setTimestamp(System.currentTimeMillis());
        providerResp.setBody(new HashMap<>(providerReq.getParams()));
        return providerResp;
    }

}
