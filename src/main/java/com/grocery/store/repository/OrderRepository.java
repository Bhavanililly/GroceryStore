package com.grocery.store.repository;

import org.springframework.stereotype.Repository;

import com.grocery.store.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
