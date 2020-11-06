package cn.yuge.nacos;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luojiayu
 * @date 2020/11/6
 */
@RestController
public class Nacos {

    @Resource
    private NacosDiscoveryClient nacosDiscoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/test")
    public List<String> test() {
        for(String s :nacosDiscoveryClient.getServices()){
            ServiceInstance choose = loadBalancerClient.choose(s);
            System.out.println(choose.getUri());

        }
        return nacosDiscoveryClient.getServices();
    }
}
