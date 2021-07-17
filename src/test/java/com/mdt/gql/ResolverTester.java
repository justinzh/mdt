package com.mdt.gql;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Optional;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.mdt.gql.interfaces.PodRepository;
import com.mdt.gql.models.Pod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@GraphQLTest
public class ResolverTester {
    @Autowired
    private GraphQLTestTemplate templ;

    @MockBean
    PodRepository repoMock;

    @Test
    public void getByIdTest() throws Exception {
        Pod pod = new Pod();
        pod.setId(54L);
        pod.setName("getbyid test");
        pod.setType("TEST");
        pod.setDescription("unit test case for getbyid");
        pod.setCreation_time(new Timestamp(System.currentTimeMillis()));

        Optional<Pod> opt = Optional.of(pod);

        doReturn(opt).when(repoMock).findById(54L);

        GraphQLResponse resp = templ.postForResource("graphqls/getbyid.graphql");
        assertThat(resp.isOk()).isTrue();
        assertThat(resp.get("$.data.getPodById.id")).isEqualTo("54");
    }

    @Test
    public void createPodTest() throws Exception {
        when(repoMock.save(Mockito.any(Pod.class))).thenAnswer(i -> {
            Pod p = (Pod) (i.getArguments()[0]);
            p.setId(99L);
            p.setCreation_time(new Timestamp(System.currentTimeMillis()));
            return p;
        });

        GraphQLResponse resp = templ.postForResource("graphqls/createpod.graphql");
        assertThat(resp.isOk()).isTrue();
        assertThat(resp.get("$.data.createPod.name")).isEqualTo("LeTV");
    }
}
