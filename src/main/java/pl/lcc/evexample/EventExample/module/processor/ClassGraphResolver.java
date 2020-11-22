/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.evexample.EventExample.module.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author piko
 */
@Slf4j
public class ClassGraphResolver implements IClassGraphResolver {

    Set<Class<?>> result = new HashSet<>();
    List<Class<?>> superclasses = new ArrayList<>();
    Set<Class<?>> directInterfaces = new HashSet<>();

    @Override
    public Class<?>[] resolve(Object subject) {
        if (Objects.isNull(subject)) {
            return new Class<?>[0];
        }

        getAllSuperclasses(subject.getClass());
        superclasses.forEach(this::getAllInterfaces);
        return result.toArray(new Class<?>[1]);
    }
    
    @Override
    public Stream<Class<?>> resolveToStream (Object subject){
        return Arrays.stream(resolve(subject));
    }

    private void getAllInterfaces(Class<?> subject) {
        result.add(subject);
        var results = subject.getInterfaces();
        if (results.length > 0) {
            Arrays.asList(results).forEach(this::getAllInterfaces);
        }
    }

    private void getAllSuperclasses(Class<?> subjectKlazz) {
        superclasses.add(subjectKlazz);
        var superclass = subjectKlazz.getSuperclass();
        if (superclass != null) {
            getAllSuperclasses(superclass);
        }
    }
}
