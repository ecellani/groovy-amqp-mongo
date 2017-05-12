package com.cellani.sample.infrastructure.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Created by ecellani on 11/05/17.
 */
@Component
@ConfigurationProperties('sample')
class ApplicationConfig {

    int threadPool
    Inscricao inscricao = new Inscricao()

    class Inscricao {
        String fila
        String collection
    }
}
