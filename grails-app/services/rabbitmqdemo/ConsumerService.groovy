package rabbitmqdemo

class ConsumerService {

    static rabbitQueue = "personTestQueue"

    static transactional = true

    void handleMessage(PersonMessage msg) {
        new Person(name: msg.name, age: msg.age).save(failOnError: true, flush: true)
    }
}
