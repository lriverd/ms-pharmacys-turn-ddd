package cl.duamit.pharmacy.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RequestCount {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestCount.class);

    private Integer rpmRatio = 25;
    private Long MINUTE = 60000L;
    private Map<String, List<Long>> requestQty;

    @PostConstruct
    private void init() {
        requestQty = new HashMap<>();
    }

    public boolean canAccess(String ipRemote) {

        Long l = new Date().getTime();
        List<Long> ipRequest = requestQty.get(ipRemote);
        if (Objects.isNull(ipRequest)) {
            ipRequest = new ArrayList<>();
        }
        ipRequest.add(l);

        ipRequest = ipRequest.stream().filter(rq -> rq > (l - MINUTE))
                .collect(Collectors.toList());

        requestQty.put(ipRemote, ipRequest);

        return ipRequest.size() < rpmRatio;
    }

    @Scheduled(cron = "0 0 * * * *")
    private void cleanIpMap() {
        LOGGER.info("Cleaning reqst count register");
        if(Objects.isNull(requestQty) || requestQty.isEmpty()){
            LOGGER.info("Nothing to clean");
            return;
        }
        Long toClean = new Date().getTime() - MINUTE * 2;
        requestQty.keySet().stream().forEach(k -> {
            List<Long> list = requestQty.get(k);
            if (list.isEmpty() || list.get(list.size() - 1) < toClean){
                requestQty.remove(k);
            }
        });
    }
}
