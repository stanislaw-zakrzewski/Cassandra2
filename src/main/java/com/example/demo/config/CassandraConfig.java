package com.example.demo.config;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DataCenterReplication;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.port}")
    private int port;
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keySpace;
    @Value("${spring.data.cassandra.user-name}")
    private String userName;
    @Value("${spring.data.cassandra.password}")
    private String password;
    @Value("${spring.data.cassandra.entity-base-package}")
    private String entityBasePackage;
    @Value("${replication-factor}")
    private int replicationFactor;
    @Value("${datacenter-name-dc1}")
    private String datacenterNameDC1;
    @Value("${datacenter-name-dc2}")
    private String datacenterNameDC2;

    private final DCAwareRoundRobinPolicy dcAwareRoundRobinPolicy;

    private final String contactPoint;

    @Autowired
    public CassandraConfig(@Qualifier("policy") DCAwareRoundRobinPolicy dcAwareRoundRobinPolicy,
                           @Qualifier("contactPoint") String contactPoint) {
        this.dcAwareRoundRobinPolicy = dcAwareRoundRobinPolicy;
        this.contactPoint = contactPoint;
    }

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{
                entityBasePackage
        };
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        return new CassandraMappingContext();
    }

    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification
                .createKeyspace(keySpace)
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withNetworkReplication(
                        DataCenterReplication.of(this.datacenterNameDC1, this.replicationFactor),
                        DataCenterReplication.of(this.datacenterNameDC2, this.replicationFactor)
                ));
    }

    @Bean
    @Override
    public CassandraCqlClusterFactoryBean cluster() {
        CassandraCqlClusterFactoryBean bean = new CassandraCqlClusterFactoryBean();
        bean.setJmxReportingEnabled(false);
        bean.setMetricsEnabled(false);
        bean.setKeyspaceCreations(getKeyspaceCreations());
        bean.setContactPoints(this.contactPoint);
        bean.setUsername(userName);
        bean.setPassword(password);
        bean.setLoadBalancingPolicy(
                this.dcAwareRoundRobinPolicy
        );
        return bean;
    }


    @Override
    protected QueryOptions getQueryOptions() {
        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setConsistencyLevel(ConsistencyLevel.ANY);
        return queryOptions;
    }
}