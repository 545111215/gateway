/**
 * @author yugenhai
 * @Copyright © 2019 yugenhai. All rights reserved.
 * private final Object shutdownMonitor = new Object();
 */
package org.yugh.coral.gateway.security;

import com.netflix.discovery.EurekaClient;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yugh.coral.auth.annotation.PreSkipAuth;
import org.yugh.coral.auth.common.enums.ResultEnum;
import org.yugh.coral.auth.config.AuthConfig;
import org.yugh.coral.auth.util.ResultJson;
import org.yugh.coral.auth.util.StringPool;

import java.net.InetAddress;

/**
 * Close this client
 * <p>
 * Test class
 *
 * @author yugenhai
 */
@Slf4j
@PreSkipAuth
@RestController
@RequestMapping("switch")
public class OfflineController {

    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private AuthConfig authConfig;


    /**
     * @return
     */
    @Synchronized
    @GetMapping("offline")
    public ResultJson offline() {
        try {
            String flag = authConfig.getShutdownClient();
            Assert.hasText(flag, "AuthConfig's ShutdownClient '" + flag + "' is empty");
            Boolean shutdown = Boolean.valueOf(flag);
            InetAddress addr = InetAddress.getLocalHost();
            if (StringPool.INSTANCE_IP.equals(addr.toString().split(StringPool.SLASH)[1]) || shutdown) {
                eurekaClient.shutdown();
                log.info("Shutdown Gateway Client is success !!!");
                return ResultJson.ok(null);
            }
        } catch (Exception e) {
            log.error("Shutdown Gateway Client Exception : {}", e);
        }
        return ResultJson.failure(ResultEnum.GATEWAY_SERVER_ERROR);
    }
}
