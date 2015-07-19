package rabbitmqdemo

class NonTxnService {

    static transactional = false

    static rabbitQueue = "fooNonTxn"

    void handleMessage(String msg) {
        println "[NonTxnService] Message received: $msg"
        if (msg == "throw exception") {
            throw new RuntimeException()
        }
        else {
            // As the service is non-transactional, we'll have to save our status message
            // in a new Hibernate session.
            try {
                MessageStatus.withTransaction {
                    new MessageStatus(message: msg).save(failOnError: true, flush: true)
                }
            }
            catch (Exception ex) {
                ex.printStackTrace()
            }
        }
    }
}
