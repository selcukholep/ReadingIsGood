package com.holep.readingisgood.conf;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableTransactionManagement // WARNING: Transaction works only Mongo Cluster.
public class DatabaseConfig {

//    @Bean // WARNING: Transaction works only Mongo Cluster.
//    MongoTransactionManager transactionManager(MongoDatabaseFactory factory) {
//        return new MongoTransactionManager(factory);
//    }
}
