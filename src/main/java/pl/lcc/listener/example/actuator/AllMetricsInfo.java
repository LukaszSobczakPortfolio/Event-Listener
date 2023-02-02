/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.listener.example.actuator;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * get all default metrics combined in one file. Pure curiosity from my side
 * @author Nauczyciel
 */
@Slf4j
@Component
@Profile("!test")
@WebEndpoint(id = "metriks")
public class AllMetricsInfo {

    private final MetricsEndpoint metrics;

    public AllMetricsInfo(MetricsEndpoint metrics) {
        log.info("Metriks constructor called");
        this.metrics = metrics;
    }

    @ReadOperation
    public String getMetrics() {
        return metrics.listNames().getNames().stream()
                .map(name -> metrics.metric(name, null))
                .map(metric -> metric.getName() +" : " + metric.getDescription() + "   \t\t\n" + metric.getMeasurements().toString() + metric.getBaseUnit())
                .collect(Collectors.joining("\n"));
    }
}
