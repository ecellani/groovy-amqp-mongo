package com.cellani.sample.application.service

import com.cellani.sample.infrastructure.config.ApplicationConfig
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

import static groovy.json.JsonOutput.toJson
import static groovyx.gpars.actor.Actors.actor

/**
 * Created by ecellani on 11/05/17.
 */
@Service
class InscricaoServices {

    @Autowired
    private RabbitTemplate rabbitTemplate

    @Autowired
    private StringRedisTemplate redisTemplate

    @Autowired
    private MongoTemplate mongoTemplate

    @Autowired
    private ApplicationConfig config

    @Async
    void sendToQueue(queue, msg) {
         rabbitTemplate.convertAndSend(queue, msg)
    }

    @Async
    void save(msg) {
        mongoTemplate.save(toJson(msg), config.inscricao.collection)
    }

    def mongoActor = actor {
        loop {
            react { msg -> save(msg) }
        }
    }

    @Async
    void saveRedis(key, msg) {
        redisTemplate.opsForValue().set(key, msg)
    }

    def rabbitActor = actor {
        loop {
            react { msg -> sendToQueue(config.inscricao.fila, msg) }
        }
    }
}
