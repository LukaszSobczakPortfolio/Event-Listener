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
import org.springframework.stereotype.Component;

/**
 *
 * @author Nauczyciel
 */
@Slf4j
@Component
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
