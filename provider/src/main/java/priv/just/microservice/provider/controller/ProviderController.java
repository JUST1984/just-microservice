package priv.just.microservice.provider.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.just.microservice.provider.api.domain.ProviderReq;
import priv.just.microservice.provider.api.domain.ProviderResp;
import priv.just.microservice.provider.api.service.ProviderService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ProviderController implements ProviderService {

    @Resource
    private Environment environment;

    @Override
    @RequestMapping("invoke")
    public ProviderResp invoke(@RequestBody ProviderReq providerReq) {
        log.info("request:{}", JSONObject.toJSONString(providerReq));
        ProviderResp res = new ProviderResp();
        res.setTimestamp(System.currentTimeMillis());
        Map<String, Object> body = new HashMap<>();
        res.setBody(body);
        return res;
    }

    @RequestMapping("getProperty")
    public String getProperty(String name) {
        return environment.getProperty(name);
    }

}
