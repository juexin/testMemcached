package com.qf.testmemcached.testmemcached;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration{
    @Value("${spring.data.mongodb.host}")
    private  String host;
    @Value("${spring.data.mongodb.port}")
    private  String port;
    @Override
    protected String getDatabaseName()
    {
        return "testMemcachedMongo";
    }

    @Override
    public Mongo mongo() throws Exception
    {
         return new MongoClient(host, Integer.parseInt(port));
    }

    @Override
    protected String getMappingBasePackage()
    {
        return "com.qf.testmemcached";
    }
}
