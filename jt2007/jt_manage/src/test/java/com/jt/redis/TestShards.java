package com.jt.redis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;

public class TestShards {


    @Test
    void testShards(){
        List<JedisShardInfo> shards=new ArrayList<>();

        ShardedJedis shardedJedis =new ShardedJedis(shards);
    }
}
