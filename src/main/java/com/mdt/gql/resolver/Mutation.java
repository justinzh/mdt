package com.mdt.gql.resolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.*;
import com.mdt.gql.exceptions.MdtException;
import com.mdt.gql.interfaces.PodRepository;
import com.mdt.gql.models.Pod;

@Component
public class Mutation implements GraphQLMutationResolver {
    @Autowired
    PodRepository repo;

    // @Autowired
    // public Mutation(PodRepository repo) {
    // this.repo = repo;
    // }

    public Pod createPod(String name, String type, String description) {

        Pod pod = new Pod();
        pod.setName(name);
        pod.setType(type);
        pod.setDescription(description);

        try {
            repo.save(pod);
        } catch (Exception e) {
            throw new MdtException(e.getMessage());
        }

        return pod;
    }

    public Pod updatePod(Long id, String name, String type, String description) throws MdtException {
        Optional<Pod> opt = repo.findById(id);

        if (!opt.isPresent())
            throw new MdtException("Pod not found");

        Pod pod = opt.get();
        if (name != null)
            pod.setName(name);
        if (type != null)
            pod.setType(type);
        if (description != null)
            pod.setDescription(description);

        repo.save(pod);

        return pod;
    }

    public boolean deletePod(Long id) {
        repo.deleteById(id);
        return true;
    }

}
