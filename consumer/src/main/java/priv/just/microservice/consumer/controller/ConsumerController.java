package priv.just.microservice.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import priv.just.microservice.provider.api.domain.ProviderReq;
import priv.just.microservice.provider.api.domain.ProviderResp;
import priv.just.microservice.provider.api.service.ProviderService;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Reference
    private ProviderService providerService;

    @RequestMapping("invoke")
    public ProviderResp invoke() {
        ServiceInstance provider = loadBalancerClient.choose("provider");
        ProviderReq req = new ProviderReq();
        req.setId(RandomUtils.nextLong());
        req.setLabel("rest");
        req.setParams(new HashMap<>());
        ProviderResp res = restTemplate.postForObject(String.format("http://%s:%s/invoke", provider.getHost(), provider.getPort()), req, ProviderResp.class);
        log.info("response:{}", JSONObject.toJSONString(res));
        return res;
    }

    @RequestMapping("dubbo/invoke")
    public ProviderResp dubboInvoke() {
        ProviderReq req = new ProviderReq();
        req.setId(RandomUtils.nextLong());
        req.setLabel("dubbo");
        req.setParams(new HashMap<>());
        return providerService.invoke(req);
    }

}
