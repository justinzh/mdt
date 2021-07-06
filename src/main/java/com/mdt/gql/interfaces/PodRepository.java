package com.mdt.gql.interfaces;

import com.mdt.gql.models.Pod;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PodRepository extends JpaRepository<Pod, Long> {

}