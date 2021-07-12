package com.mdt.gql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;

import com.mdt.gql.models.Pod;
import com.mdt.gql.interfaces.PodRepository;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import com.mdt.gql.exceptions.MdtException;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    PodRepository repo;

    // @Autowired
    // public Query(PodRepository repo) {
    // this.repo = repo;
    // }

    public Iterable<Pod> searchPod(String name, String type, String description, String sortkey, Boolean descending) {

        ExampleMatcher pattern = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Pod> exa = Example.of(new Pod(name, type, description), pattern);

        if (descending == null)
            descending = false;

        if (descending)
            return repo.findAll(exa, Sort.by(sortkey == null ? "name" : sortkey).descending());

        return repo.findAll(exa, Sort.by(sortkey == null ? "name" : sortkey).ascending());
    }

    public Optional<Pod> getPodById(Long id) {
        Optional<Pod> pod;

        try {
            pod = repo.findById(id);
        } catch (Exception e) {
            throw new MdtException(e.getMessage());
        }
        return pod;
    }

}
