package com.kiyan.weathereggapi.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SpringHazelcastConfiguration {
    @Bean
    public HazelcastInstance hazelcastInstance() {
        // Configurations
        Config config = new Config();
        config.setClusterName("lta-api");
        NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.getInterfaces().addInterface("127.0.0.1");

        MapConfig ltaMapConfig = new MapConfig("lta-bus-arrival");
        ltaMapConfig.setTimeToLiveSeconds(60);
        config.addMapConfig(ltaMapConfig);

        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public IMap<Pair<Integer, Integer>, String> busArrivalMapCache(HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getMap("lta-bus-arrival");
    }
}
