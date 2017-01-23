package com.qf.testmemcached;

import com.qf.testmemcached.testmemcached.Application;
import net.rubyeye.xmemcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = Application.class)
public class Tests {
	@Autowired
	private Environment env;
	@Autowired
	private MemcachedClient memcachedClient;

	@Test
	public void tests() throws Exception {
		/*memcachedClient.set("name",0,"czg");
		System.out.println(memcachedClient.get("name"));*/
	}
	@Test
	public void testCache(){

	}
	@Test
	public void  getAll(){

	}
}
