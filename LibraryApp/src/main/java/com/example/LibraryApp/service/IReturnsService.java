package com.example.LibraryApp.service;

import com.example.LibraryApp.model.Returns;

import java.util.List;
import java.util.Optional;

public interface IReturnsService {

        List<Returns> findAllReturns();
        Optional<Returns> findReturnsById(int returnsId);

        Returns saveReturns(Returns newReturns);

        Returns updateReturns(int returnsId , Returns returns);

        Returns deleteReturns(int returnsId);

}
