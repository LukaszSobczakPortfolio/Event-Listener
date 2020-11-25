/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.listener.example.module.processor;

import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 *
 * @author piko
 */
public class WeakArrayList<E> extends AbstractList<E>{

    ArrayList<WeakReference<E>> container = new ArrayList<>();
    
    @Override
    public boolean add (E element){
        container.add(new WeakReference<>(element));
        return true;
    }
    
    @Override
    public E get(int index) {
      var item = container.get(index);
      return item.get();
    }

    @Override
    public int size() {
        purge();
        return container.size();
    }

    @Override
    public Stream<E> stream() {
        purge();
        return super.stream(); 
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        purge();
        super.forEach(action); 
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    private void purge(){
        container.removeIf(reference -> reference.get()==null);
    }

    
    
}
