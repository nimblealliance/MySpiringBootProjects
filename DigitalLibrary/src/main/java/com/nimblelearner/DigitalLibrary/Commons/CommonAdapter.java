package com.nimblelearner.DigitalLibrary.Commons;

import java.util.List;

public interface CommonAdapter <E, M, I ,O> {

    M save(E e);

    M getById(I i);

    boolean deleteById(I i);

    List<O> findAll();

}

