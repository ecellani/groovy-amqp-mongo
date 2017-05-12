package com.cellani.sample.application.controller

import com.cellani.sample.application.domain.repository.InscricaoRepository
import com.cellani.sample.application.service.InscricaoServices
import com.cellani.sample.infrastructure.config.ApplicationConfig
import com.cellani.sample.infrastructure.config.Mapper
import groovyx.gpars.GParsPool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static groovyx.gpars.actor.Actors.actor

/**
 * Created by ecellani on 11/05/17.
 */
@RestController
@RequestMapping('/inscricao')
public class InscricaoController {

    @Autowired
    private InscricaoRepository inscricaoRepository

    @Autowired
    private InscricaoServices inscricaoServices

    @Autowired
    private ApplicationConfig config

    @GetMapping('/process')
    def process() {
        inscricaoRepository.findAllInscricao { error, result ->
            try {
                if (error) throw error
                GParsPool.withPool(config.threadPool) {
                    result.eachParallel { row ->
                        actor {
                            Mapper.mapperActor << row
                            react { mapped ->
                                inscricaoServices.mongoActor << mapped
                            }
                        }
                    }
                }
                'OK'
            } catch (Exception e) {
                throw e
            }
        }
    }
}
