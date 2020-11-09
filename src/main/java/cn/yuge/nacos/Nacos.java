package cn.yuge.nacos;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luojiayu
 * @date 2020/11/6
 */
@RestController
public class Nacos {

    @Value("${spring.application.name}")
    private String name;

    @Resource
    private NacosDiscoveryClient nacosDiscoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/test")
    public List<String> test() {
        List<String> slist = new ArrayList<>();
        for (String s : nacosDiscoveryClient.getServices()) {
            if (s.equals(name)) {
                continue;
            }
            ServiceInstance choose = loadBalancerClient.choose(s);
            String url = choose.getUri() + "/test/getAllURL";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            slist.add(s + "::" + result);
        }
        return slist;
    }
}
