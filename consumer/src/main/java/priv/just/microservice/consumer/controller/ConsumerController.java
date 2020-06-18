package priv.just.microservice.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import priv.just.microservice.provider.api.domain.ProviderReq;
import priv.just.microservice.provider.api.domain.ProviderResp;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("invoke")
    public ProviderResp invoke() {
        ServiceInstance provider = loadBalancerClient.choose("provider");
        ProviderReq req = new ProviderReq();
        req.setId(RandomUtils.nextLong());
        req.setLabel("consumer");
        req.setParams(new HashMap<>());
        ProviderResp res = restTemplate.postForObject(String.format("http://%s:%s/invoke", provider.getHost(), provider.getPort()), req, ProviderResp.class);
        log.info("response:{}", JSONObject.toJSONString(res));
        return res;
    }

}
