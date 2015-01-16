/**
 * 
 */
package com.idrene.emefana.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

/**
 * @author iddymagohe
 *
 */
@Configuration
@EnableMongoRepositories("com.idrene.emefana.repositories")
public class MongoRepositoryConfig extends AbstractMongoConfiguration {
	@Value("${db.password}")
	private String password;
	@Value("${db.host.port}")
	private int port;
	@Value("${db.user}")
	private String dbuser;
	@Value("${db.host.name}")
	private String host;
	@Value("${db.name}")
	private String dbname;

	@Resource
	private Environment environment;

	@Override
	public Mongo mongo() throws Exception {
		ServerAddress serverAdress = new ServerAddress(host,port);
		Mongo mongo = new MongoClient(serverAdress);
		mongo.setWriteConcern(WriteConcern.ACKNOWLEDGED);
		return mongo;
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}

	@Override
	protected String getDatabaseName() {
		return dbname;
	}

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

	// void replicaLSet() throws UnknownHostException {
	// MongoCredential credential =
	// MongoCredential.createMongoCRCredential("moez", "tuto",
	// "secret".toCharArray());
	// MongoClient mongoClient = new MongoClient(Arrays.asList(
	// new ServerAddress("localhost", 27017), new ServerAddress(
	// "localhost", 27018), new ServerAddress("localhost",
	// 27019)),credential);
	// }
}
