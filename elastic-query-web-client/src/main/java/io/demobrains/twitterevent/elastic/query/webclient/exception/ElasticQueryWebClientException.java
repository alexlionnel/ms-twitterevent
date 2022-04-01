package io.demobrains.twitterevent.elastic.query.webclient.exception;

public class ElasticQueryWebClientException extends RuntimeException {

    public ElasticQueryWebClientException() {
    }

    public ElasticQueryWebClientException(String message) {
        super(message);
    }

    public ElasticQueryWebClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
